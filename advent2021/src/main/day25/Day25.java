package main.day25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day25 {

    public void firstPart(String filePath) throws IOException {
        char[][] cucumberMap = readInput(filePath);

        int counter = 0;
        boolean move = true;

        while (move) {
            move = false;
            counter++;
            for (int i = 0; i < 3; i++) {
                char[][] newCucumberMap = copyMap(cucumberMap);
                for (int j = 0; j < cucumberMap.length; j++) {
                    for (int k = 0; k < cucumberMap[j].length; k++) {
                        if (i == 0 && cucumberMap[j][k] == '>') {
                            if (k <= cucumberMap[j].length - 2 && cucumberMap[j][k + 1] == '.') {
                                newCucumberMap[j][k + 1] = '>';
                                newCucumberMap[j][k] = '.';
                                move = true;
                            } else if (k == cucumberMap[j].length - 1 && cucumberMap[j][0] == '.') {
                                newCucumberMap[j][0] = '>';
                                newCucumberMap[j][k] = '.';
                                move = true;
                            }
                        }

                        if (i == 1 && cucumberMap[j][k] == 'v') {
                            if (j <= cucumberMap.length - 2 && cucumberMap[j + 1][k] == '.') {
                                newCucumberMap[j + 1][k] = 'v';
                                newCucumberMap[j][k] = '.';
                                move = true;
                            } else if (j == cucumberMap.length - 1 && cucumberMap[0][k] == '.') {
                                newCucumberMap[0][k] = 'v';
                                newCucumberMap[j][k] = '.';
                                move = true;
                            }
                        }
                    }
                }
                cucumberMap = newCucumberMap;
            }
        }
        System.out.println(counter);

    }

    private void printMap(char[][] cucumberMap) {
        for (char[] chars : cucumberMap) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private char[][] copyMap(char[][] cucumberMap) {
        return Arrays.stream(cucumberMap).map(char[]::clone).toArray($ -> cucumberMap.clone());
    }

    private char[][] readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;
        List<char[]> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                char[] row = currentLine.toCharArray();
                input.add(row);
                currentLine = reader.readLine();
            }
        }

        return input.toArray(new char[input.size()][input.size()]);
    }
}
