package main.day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day14 {

    public void getAnswer(String filePath, int steps) throws IOException {
        Map<String, String[]> instructions = new HashMap<>();
        String polymerTemplate = readInput2(filePath, instructions);

        Map<String, Long> pairCount = new HashMap<>();
        instructions.forEach((key, value) -> pairCount.put(key, 0L));

        for (int j = 0; j < polymerTemplate.length() - 1; j++) {
            String letterPair = polymerTemplate.substring(j, j + 2);
            long pairCountValue = pairCount.get(letterPair);
            pairCount.replace(letterPair, pairCountValue + 1);
        }

        Map<Character, Long> characterCounts = new HashMap<>();
        polymerTemplate.chars().forEach(character -> {
            Long count = characterCounts.get((char) character);
            if (count == null) {
                characterCounts.put((char) character, 1L);
            } else {
                characterCounts.replace((char) character, count + 1);
            }
        });

        Map<String, Long> newPairCount = new HashMap<>();

        for (int i = 0; i < steps; i++) {
            instructions.forEach((key, value) -> newPairCount.put(key, 0L));

            pairCount.forEach((key, value) -> {
                if (value != 0) {
                    String[] instructionResults = instructions.get(key);

                    long firstNewPairOccurrence = newPairCount.get(instructionResults[0]);
                    long secondNewPairOccurrence = newPairCount.get(instructionResults[1]);

                    newPairCount.put(instructionResults[0], firstNewPairOccurrence + value);
                    newPairCount.put(instructionResults[1], secondNewPairOccurrence + value);

                    long characterOccurrence = characterCounts.get(instructionResults[2].charAt(0)) != null ? characterCounts.get(instructionResults[2].charAt(0)) : 0L;
                    characterCounts.put(instructionResults[2].charAt(0), characterOccurrence + value);
                }
            });

            newPairCount.forEach((key, value) -> pairCount.put(key, value));
        }

        long maxValue = Long.MIN_VALUE;
        long minValue = Long.MAX_VALUE;
        for (Map.Entry<Character, Long> entry : characterCounts.entrySet()) {
            maxValue = Math.max(maxValue, entry.getValue());
            minValue = Math.min(minValue, entry.getValue());
        }

        System.out.println(maxValue - minValue);
    }

    private String readInput2(String filePath, Map<String, String[]> instructions) throws IOException {
        String file = "src/main/resources/" + filePath;
        String polymerTemplate = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLine.matches("[A-Z]{2} -> [A-Z]")) {
                    String[] instruction = currentLine.split(" -> ");
                    String firstNewPair = instruction[0].substring(0, 1) + instruction[1];
                    String secondNewPair = instruction[1] + instruction[0].substring(1, 2);
                    instructions.put(instruction[0], new String[]{firstNewPair, secondNewPair, instruction[1]});
                } else if (currentLine.matches("[A-Z]+")) {
                    polymerTemplate = currentLine;
                }
                currentLine = reader.readLine();
            }
        }
        return polymerTemplate;
    }

    public void firstPart(String filePath, int steps) throws IOException {
        Map<String, String> instructions = new HashMap<>();
        String polymerTemplate = readInput(filePath, instructions);

        for (int i = 0; i < steps; i++) {
            StringBuilder sb = new StringBuilder(polymerTemplate);

            for (int j = 0; j < polymerTemplate.length() - 1; j++) {
                String letterPair = polymerTemplate.substring(j, j + 2);
                sb.insert(j + 1 + j, instructions.get(letterPair));
            }

            polymerTemplate = sb.toString();
        }

        Map<Character, Long> characterCounts = new HashMap<>();

        polymerTemplate.chars().forEach(character -> {
            Long count = characterCounts.get((char) character);
            if (count == null) {
                characterCounts.put((char) character, 1L);
            } else {
                characterCounts.replace((char) character, count + 1);
            }
        });

        long maxValue = Long.MIN_VALUE;
        long minValue = Long.MAX_VALUE;


        for (Map.Entry<Character, Long> entry : characterCounts.entrySet()) {
            maxValue = Math.max(maxValue, entry.getValue());
            minValue = Math.min(minValue, entry.getValue());
        }
        System.out.println(maxValue - minValue);
    }

    private String readInput(String filePath, Map<String, String> instructions) throws IOException {
        String file = "src/main/resources/" + filePath;
        String polymerTemplate = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLine.matches("[A-Z]{2} -> [A-Z]")) {
                    String[] instruction = currentLine.split(" -> ");
                    instructions.put(instruction[0], instruction[1]);
                } else if (currentLine.matches("[A-Z]+")) {
                    polymerTemplate = currentLine;
                }
                currentLine = reader.readLine();
            }
        }
        return polymerTemplate;
    }
}
