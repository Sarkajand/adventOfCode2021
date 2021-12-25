package main.day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day22 {

    public void firstPart(String filePath) throws IOException {
        List<int[][]> input = readInput(filePath);

        input = input.stream()
                .filter(row -> row[1][0] > -51 && row[1][0] < 51 &&
                        row[1][1] > -51 && row[1][1] < 51 &&
                        row[2][0] > -51 && row[2][0] < 51 &&
                        row[2][1] > -51 && row[2][1] < 51 &&
                        row[3][0] > -51 && row[3][0] < 51 &&
                        row[3][1] > -51 && row[3][1] < 51
                ).collect(Collectors.toList());

        int[][][] reactor = startReboot();

        for (int[][] instruction : input) {
            int switchTo = instruction[0][0];
            for (int i = 50 + Math.min(instruction[1][0], instruction[1][1]); i <= 50 + Math.max(instruction[1][0], instruction[1][1]); i++) {
                for (int j = 50 + Math.min(instruction[2][0], instruction[2][1]); j <= 50 + Math.max(instruction[2][0], instruction[2][1]); j++) {
                    for (int k = 50 + Math.min(instruction[3][0], instruction[3][1]); k <= 50 + Math.max(instruction[3][0], instruction[3][1]); k++) {
                        reactor[i][j][k] = switchTo;
                    }
                }
            }
            long count = 0;
            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    for (int k = 0; k < 101; k++) {
                        count += reactor[i][j][k];
                    }
                }
            }

            System.out.println(count);

        }

        long count = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                for (int k = 0; k < 101; k++) {
                    count += reactor[i][j][k];
                }
            }
        }

        System.out.println(count);
    }

    public void secondPart(String filePath) throws IOException {
        List<int[][]> input = readInput(filePath);

//        input = input.stream()
//                .filter(row -> row[1][0] > -51 && row[1][0] < 51 &&
//                        row[1][1] > -51 && row[1][1] < 51 &&
//                        row[2][0] > -51 && row[2][0] < 51 &&
//                        row[2][1] > -51 && row[2][1] < 51 &&
//                        row[3][0] > -51 && row[3][0] < 51 &&
//                        row[3][1] > -51 && row[3][1] < 51
//                ).collect(Collectors.toList());

        List<Cuboid> lightsOn = new ArrayList<>();

        int counter = 0;
        for (int[][] instruction : input) {
            counter++;
            System.out.println(counter);
            List<Cuboid> overlapCuboids = findOverlapCuboids(instruction, lightsOn);

            if (overlapCuboids.size() != 0) {
                for (Cuboid cuboid : overlapCuboids) {
                    List<Cuboid> dividedCuboid = divideCuboid(cuboid, instruction);
                    List<Cuboid> remainingCuboids = removeOverlap(dividedCuboid, instruction);
                    lightsOn.addAll(remainingCuboids);
                    lightsOn.remove(cuboid);
                }
            }

            if (instruction[0][0] == 1) {
                lightsOn.add(new Cuboid(instruction[1][0], instruction[1][1], instruction[2][0], instruction[2][1], instruction[3][0], instruction[3][1]));
            }
        }

        long count = 0;
        for (Cuboid cuboid : lightsOn) {
            count += cuboid.getLightsCount();
        }

        System.out.println(count);
    }

    private List<Cuboid> removeOverlap(List<Cuboid> dividedCuboid, int[][] instruction) {
        return dividedCuboid.stream()
                .filter(cuboid ->
                        !((cuboid.x1 == instruction[1][0] || cuboid.x2 == instruction[1][1] || (cuboid.x1 > instruction[1][0] && cuboid.x2 < instruction[1][1])) &&
                                (cuboid.y1 == instruction[2][0] || cuboid.y2 == instruction[2][1] || (cuboid.y1 > instruction[2][0] && cuboid.y2 < instruction[2][1])) &&
                                (cuboid.z1 == instruction[3][0] || cuboid.z2 == instruction[3][1] || (cuboid.z1 > instruction[3][0] && cuboid.z2 < instruction[3][1])))
                )
                .collect(Collectors.toList());
    }

    private List<Cuboid> divideCuboid(Cuboid cuboid, int[][] instruction) {
        List<Cuboid> dividedCuboid = new ArrayList<>();

        List<Integer> xs = new ArrayList<>();
        xs.add(cuboid.x1);
        xs.add(cuboid.x2);
        if (instruction[1][0] > cuboid.x1 && instruction[1][0] <= cuboid.x2) {
            xs.add(instruction[1][0]);
        }
        if (instruction[1][1] >= cuboid.x1 && instruction[1][1] < cuboid.x2) {
            xs.add(instruction[1][1]);
        }
        Collections.sort(xs);

        List<Integer> ys = new ArrayList<>();
        ys.add(cuboid.y1);
        ys.add(cuboid.y2);
        if (instruction[2][0] > cuboid.y1 && instruction[2][0] <= cuboid.y2) {
            ys.add(instruction[2][0]);
        }
        if (instruction[2][1] >= cuboid.y1 && instruction[2][1] < cuboid.y2) {
            ys.add(instruction[2][1]);
        }
        Collections.sort(ys);

        List<Integer> zs = new ArrayList<>();
        zs.add(cuboid.z1);
        zs.add(cuboid.z2);
        if (instruction[3][0] > cuboid.z1 && instruction[3][0] <= cuboid.z2) {
            zs.add(instruction[3][0]);
        }
        if (instruction[3][1] >= cuboid.z1 && instruction[3][1] < cuboid.z2) {
            zs.add(instruction[3][1]);
        }
        Collections.sort(zs);

        List<int[]> x2 = new ArrayList<>();
        for (int i = 0; i < xs.size() - 1; i++) {
            int[] x = new int[2];
            x[0] = xs.get(i);
            if (xs.get(i) == instruction[1][1] && !x2.isEmpty()) {
                x[0]++;
            }

            x[1] = xs.get(i + 1);
            if (xs.get(i + 1) == instruction[1][0] && xs.size() != i + 2) {
                x[1]--;
            }

            x2.add(x);
        }

        List<int[]> y2 = new ArrayList<>();
        for (int i = 0; i < ys.size() - 1; i++) {
            int[] y = new int[2];
            y[0] = ys.get(i);
            if (ys.get(i) == instruction[2][1] && !y2.isEmpty()) {
                y[0]++;
            }

            y[1] = ys.get(i + 1);
            if (ys.get(i + 1) == instruction[2][0] && ys.size() != i + 2) {
                y[1]--;
            }

            y2.add(y);
        }

        List<int[]> z2 = new ArrayList<>();
        for (int i = 0; i < zs.size() - 1; i++) {
            int[] z = new int[2];
            z[0] = zs.get(i);
            if (zs.get(i) == instruction[3][1]  && !z2.isEmpty()) {
                z[0]++;
            }

            z[1] = zs.get(i + 1);
            if (zs.get(i + 1) == instruction[3][0] && zs.size() != i + 2) {
                z[1]--;
            }

            z2.add(z);
        }

        for (int[] x : x2) {
            for (int[] y : y2) {
                for (int[] z : z2) {
                    Cuboid newCuboid = new Cuboid();
                    newCuboid.x1 = x[0];
                    newCuboid.x2 = x[1];

                    newCuboid.y1 = y[0];
                    newCuboid.y2 = y[1];

                    newCuboid.z1 = z[0];
                    newCuboid.z2 = z[1];
                    dividedCuboid.add(newCuboid);
                }
            }
        }

        return dividedCuboid;
    }

    private List<Cuboid> findOverlapCuboids(int[][] instruction, List<Cuboid> lightsOn) {
        int ix1 = instruction[1][0];
        int ix2 = instruction[1][1];
        int iy1 = instruction[2][0];
        int iy2 = instruction[2][1];
        int iz1 = instruction[3][0];
        int iz2 = instruction[3][1];

        return lightsOn.stream()
                .filter(cuboid -> {
                    int x1 = cuboid.x1;
                    int x2 = cuboid.x2;
                    int y1 = cuboid.y1;
                    int y2 = cuboid.y2;
                    int z1 = cuboid.z1;
                    int z2 = cuboid.z2;

                    return (x1 >= ix1 && x1 <= ix2) || (x2 >= ix1 && x2 <= ix2) || (ix1 >= x1 && ix1 <= x2) || (ix2 >= x1 && ix2 <= x2) &&
                            (y1 >= iy1 && y1 <= iy2) || (y2 >= iy1 && y2 <= iy2) || (iy1 >= y1 && iy1 <= y2) || (iy2 >= y1 && iy2 <= y2) &&
                            (z1 >= iz1 && z1 <= iz2) || (z2 >= iz1 && z2 <= iz2) || (iz1 >= z1 && iz1 <= z2) || (iz2 >= z1 && iz2 <= z2);

                }).collect(Collectors.toList());
    }

    private int[][][] startReboot() {
        int[][][] reactor = new int[101][101][101];

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                Arrays.fill(reactor[i][j], 0);
            }
        }

        return reactor;
    }

    private List<int[][]> readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;
        Pattern pattern = Pattern.compile("^(on|off) x=(-?\\d+)[.][.](-?\\d+),y=(-?\\d+)[.][.](-?\\d+),z=(-?\\d+)[.][.](-?\\d+)$");
        List<int[][]> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                Matcher matcher = pattern.matcher(currentLine);

                if (matcher.find()) {
                    int[][] row = new int[4][2];
                    row[0][0] = matcher.group(1).equals("on") ? 1 : 0;
                    row[1][0] = Integer.parseInt(matcher.group(2));
                    row[1][1] = Integer.parseInt(matcher.group(3));
                    row[2][0] = Integer.parseInt(matcher.group(4));
                    row[2][1] = Integer.parseInt(matcher.group(5));
                    row[3][0] = Integer.parseInt(matcher.group(6));
                    row[3][1] = Integer.parseInt(matcher.group(7));
                    input.add(row);
                }

                currentLine = reader.readLine();
            }
        }
        return input;
    }

    private static class Cuboid {
        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;
        int z1 = 0;
        int z2 = 0;

        public Cuboid() {
        }

        public Cuboid(int x1, int x2, int y1, int y2, int z1, int z2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.z1 = z1;
            this.z2 = z2;
        }

        public long getLightsCount() {
            return (long) ((Math.max(x1, x2) - Math.min(x1, x2)) + 1) * ((Math.max(y1, y2) - Math.min(y1, y2)) + 1) * ((Math.max(z1, z2) - Math.min(z1, z2)) + 1);
        }
    }
}
