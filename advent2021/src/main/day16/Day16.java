package main.day16;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Day16 {

    public void firstPart(String filePath) throws IOException {
        String input = readInput(filePath);
        List<Packet> packets = new ArrayList<>();
        getPackets(input, null, packets);

        System.out.println(packets.stream().mapToInt(p -> p.version).sum());
    }

    public void secondPart(String filePath) throws IOException {
        String input = readInput(filePath);

        List<Packet> packets = new ArrayList<>();

        while (input.length() > 10) {
            Packet packet = new Packet();
            packets.add(packet);
            packet.version = Integer.parseInt(input.substring(0, 3), 2);
            packet.type = Integer.parseInt(input.substring(3, 6), 2);

            if (packet.type == 4) {
                int start = 6;
                int end = 11;
                String fiveBits = input.substring(start, end);
                StringBuilder result = new StringBuilder(fiveBits.substring(1, 5));
                while (fiveBits.charAt(0) == '1') {
                    start = start + 5;
                    end = end + 5;
                    fiveBits = input.substring(start, end);
                    result.append(fiveBits, 1, 5);
                }
                packet.literalValue = Long.parseLong(result.toString(), 2);
                packet.value = packet.literalValue;
                packet.lengthInBits = end;
                input = input.substring(end);
            } else {
                packet.lengthType = Integer.parseInt(input.substring(6, 7), 2);

                if (packet.lengthType == 0) {
                    packet.lengthValue = Integer.parseInt(input.substring(7, 22), 2);
                    packet.lengthInBits = 22;
                    input = input.substring(22);
                } else {
                    packet.lengthValue = Integer.parseInt(input.substring(7, 18), 2);
                    packet.lengthInBits = 18;
                    input = input.substring(18);
                }
            }
        }


        while (packets.size() > 1) {
            int index = packets.size() - 1;
            Packet packet = packets.get(index);
            while (packet.value != null && index > 0) {
                index--;
                packet = packets.get(index);
            }
            List<Long> values = new ArrayList<>();
            List<Packet> usedPackets = new ArrayList<>();

            if (packet.lengthType == 1) {
                for (int i = index + 1; i <= index + packet.lengthValue; i++) {
                    values.add(packets.get(i).value);
                    usedPackets.add(packets.get(i));
                }
            } else if (packet.lengthType == 0) {
                int i = index + 1;
                int lengthSum = 0;
                while (packet.lengthValue != lengthSum) {
                    values.add(packets.get(i).value);
                    usedPackets.add(packets.get(i));
                    lengthSum = lengthSum + packets.get(i).lengthInBits;
                    i++;
                }
            }

            packet.value = countValue(values, packet.type);
            int usedPacketLength = usedPackets.stream().mapToInt(p -> p.lengthInBits).sum();
            packet.lengthInBits = packet.lengthInBits + usedPacketLength;

            packets.removeAll(usedPackets);
        }

        System.out.println(packets.get(0).value);
    }

    private long countValue(List<Long> values, int type) {
        switch (type) {
            case 0:
                return values.stream().reduce(0L, Long::sum);
            case 1:
                return values.stream().reduce(1L, (a, b) -> a * b);
            case 2:
                return Collections.min(values);
            case 3:
                return Collections.max(values);
            case 5:
                return values.get(0) > values.get(1) ? 1 : 0;
            case 6:
                return values.get(0) < values.get(1) ? 1 : 0;
            case 7:
                return values.get(0).equals(values.get(1)) ? 1 : 0;
            default:
                return 0;
        }
    }


    private void getPackets(String input, Packet parent, List<Packet> packets) {
        if (input.length() > 10) {

            Packet packet = new Packet();
            packets.add(packet);
            packet.version = Integer.parseInt(input.substring(0, 3), 2);
            packet.type = Integer.parseInt(input.substring(3, 6), 2);

            int packetLength = 0;
            if (packet.type == 4) {
                int start = 6;
                int end = 11;
                String fiveBits = input.substring(start, end);
                StringBuilder result = new StringBuilder(fiveBits.substring(1, 5));
                while (fiveBits.charAt(0) == '1') {
                    start = start + 5;
                    end = end + 5;
                    fiveBits = input.substring(start, end);
                    result.append(fiveBits, 1, 5);
                }
                packetLength = end;

                Packet packetParent = parent;
                while (packetParent != null) {
                    packetParent.subPacketsLength = packetParent.subPacketsLength + packetLength;
                    packetParent = packetParent.parentPacket;
                }
                packet.literalValue = Long.parseLong(result.toString(), 2);
                packet.value = packet.literalValue;
            } else {
                packet.lengthType = Integer.parseInt(input.substring(6, 7), 2);
                if (packet.lengthType == 0) {
                    packet.lengthValue = Integer.parseInt(input.substring(7, 22), 2);
                    packet.remainingInput = input.substring(22);
                } else {
                    packet.lengthValue = Integer.parseInt(input.substring(7, 18), 2);
                    packet.remainingInput = input.substring(18);
                }
            }

            packet.parentPacket = parent;
            if (parent != null) {
                parent.subPackets.add(packet);
            }


            if (packet.remainingInput != null) {
                getPackets(packet.remainingInput, packet, packets);
            } else {

                while (parent != null) {
                    if (parent.lengthType == 1) {
                        if (parent.lengthValue > parent.subPackets.size()) {
                            getPackets(input.substring(packetLength), parent, packets);
                            break;
                        } else if (parent.lengthValue == parent.subPackets.size()) {
                            parent.remainingInput = null;

                            Packet packetParent = parent.parentPacket;
                            while (packetParent != null) {
                                packetParent.subPacketsLength = packetParent.subPacketsLength + 18;
                                packetParent = packetParent.parentPacket;
                            }

                            parent = parent.parentPacket;
                        }
                    } else if (parent.lengthType == 0) {
                        if (parent.lengthValue > parent.subPacketsLength) {
                            getPackets(input.substring(packetLength), parent, packets);
                            break;
                        } else if (parent.lengthValue == parent.subPacketsLength) {
                            parent.remainingInput = null;

                            Packet packetParent = parent.parentPacket;
                            while (packetParent != null) {
                                packetParent.subPacketsLength = packetParent.subPacketsLength + 22;
                                packetParent = packetParent.parentPacket;
                            }

                            parent = parent.parentPacket;
                        }
                    }
                }
            }
        }
    }

    private String readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        Map<Character, String> converterMap = new HashMap<>();
        converterMap.put('0', "0000");
        converterMap.put('1', "0001");
        converterMap.put('2', "0010");
        converterMap.put('3', "0011");
        converterMap.put('4', "0100");
        converterMap.put('5', "0101");
        converterMap.put('6', "0110");
        converterMap.put('7', "0111");
        converterMap.put('8', "1000");
        converterMap.put('9', "1001");
        converterMap.put('A', "1010");
        converterMap.put('B', "1011");
        converterMap.put('C', "1100");
        converterMap.put('D', "1101");
        converterMap.put('E', "1110");
        converterMap.put('F', "1111");

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            currentLine.chars().forEach(ch -> sb.append(converterMap.get((char) ch)));
        }

        return sb.toString();
    }

    private static class Packet {
        int version;
        int type;
        int lengthType;
        int lengthValue;
        String remainingInput;

        Packet parentPacket;
        List<Packet> subPackets = new ArrayList<>();
        long subPacketsLength;
        long literalValue;

        Long value;
        int lengthInBits;
    }
}
