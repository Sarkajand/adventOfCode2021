package main.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {

    public void countFirstPart(String filePath) throws IOException {
        List<List<Character>> input = readInput(filePath);

        long wrongSum = 0;

        for (List<Character> row : input) {

            int rowSize = 1;
            int rowSizeAfterLoop = 0;

            while (rowSize > rowSizeAfterLoop) {
                rowSize = row.size();

                for (int i = 1; i < row.size(); i++) {
                    Character character = row.get(i);
                    Character previousCharacter = row.get(i - 1);

                    if (character == ')') {
                        if (previousCharacter == '(') {
                            row.remove(i);
                            row.remove(i - 1);
                        }
                    } else if (character == ']') {
                        if (previousCharacter == '[') {
                            row.remove(i);
                            row.remove(i - 1);
                        }
                    } else if (character == '}') {
                        if (previousCharacter == '{') {
                            row.remove(i);
                            row.remove(i - 1);
                        }
                    } else if (character == '>') {
                        if (previousCharacter == '<') {
                            row.remove(i);
                            row.remove(i - 1);
                        }
                    }
                }

                rowSizeAfterLoop = row.size();
            }

            for (Character character : row) {
                if (character == ')') {
                    wrongSum = wrongSum + 3;
                    break;
                } else if (character == ']') {
                    wrongSum = wrongSum + 57;
                    break;
                } else if (character == '}') {
                    wrongSum = wrongSum + 1197;
                    break;
                } else if (character == '>') {
                    wrongSum = wrongSum + 25137;
                    break;
                }
            }
        }

        System.out.println(wrongSum);
    }


    private List<List<Character>> readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        List<List<Character>> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                List<Character> row = currentLine.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
                input.add(row);
                currentLine = reader.readLine();
            }
        }

        return input;
    }

}
