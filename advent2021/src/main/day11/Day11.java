package main.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day11 {

    public void countFirstPart(String filePath) throws IOException {
        int[][] octopuses = readInput(filePath);

        int flashes = 0;
        for (int step = 0; step < 100; step++) {

            for (int row = 1; row < 11; row++) {
                for (int column = 1; column < 11; column++) {
                    octopuses[row][column] += 1;

                    if (octopuses[row][column] == 10) {
                        addEnergy(octopuses, row, column);
                    }

                }
            }

            for (int row = 1; row < 11; row++) {
                for (int column = 1; column < 11; column++) {
                    if (octopuses[row][column] > 9) {
                        flashes++;
                        octopuses[row][column] = 0;

                    }

                }
            }

            System.out.println("step : " + (step + 1));
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    System.out.print(octopuses[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }

        System.out.println(flashes);
    }

    public void countSecondPart(String filePath) throws IOException {
        int[][] octopuses = readInput(filePath);

        int flashes = 0;
        int step = 0;

        while (flashes != 100) {
            flashes = 0;
            for (int row = 1; row < 11; row++) {
                for (int column = 1; column < 11; column++) {
                    octopuses[row][column] += 1;

                    if (octopuses[row][column] == 10) {
                        addEnergy(octopuses, row, column);
                    }

                }
            }

            for (int row = 1; row < 11; row++) {
                for (int column = 1; column < 11; column++) {
                    if (octopuses[row][column] > 9) {
                        flashes++;
                        octopuses[row][column] = 0;

                    }

                }
            }

            step++;
        }
        System.out.println(step);

    }

    private int[][] readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;
        int[][] octopuses = new int[12][12];

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            octopuses[0] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            octopuses[11] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

            for (int i = 1; i < 11; i++) {
                int[] row = new int[12];
                row[0] = 0;
                row[11] = 0;
                int[] input = currentLine.chars().map(Character::getNumericValue).toArray();
                System.arraycopy(input, 0, row, 1, 10);
                octopuses[i] = row;

                currentLine = reader.readLine();
            }
        }

        return octopuses;
    }

    private void addEnergy(int[][] octopuses, int row, int column) {
        octopuses[row - 1][column - 1] += 1;
        if (row > 1 && column > 1 && row < 11 && column < 11 && octopuses[row - 1][column - 1] == 10) {
            addEnergy(octopuses, row - 1, column - 1);
        }

        octopuses[row - 1][column] += 1;
        if (row > 1 && column > 0 && row < 11 && column < 11 && octopuses[row - 1][column] == 10) {
            addEnergy(octopuses, row - 1, column);
        }

        octopuses[row - 1][column + 1] += 1;
        if (row > 1 && column > 0 && row < 11 && column < 10 && octopuses[row - 1][column + 1] == 10) {
            addEnergy(octopuses, row - 1, column + 1);
        }

        octopuses[row][column - 1] += 1;
        if (row > 0 && column > 1 && row < 11 && column < 11 && octopuses[row][column - 1] == 10) {
            addEnergy(octopuses, row, column - 1);
        }

        octopuses[row][column + 1] += 1;
        if (row > 0 && column > 0 && row < 11 && column < 10 && octopuses[row][column + 1] == 10) {
            addEnergy(octopuses, row, column + 1);
        }

        octopuses[row + 1][column - 1] += 1;
        if (row > 0 && column > 1 && row < 10 && column < 11 && octopuses[row + 1][column - 1] == 10) {
            addEnergy(octopuses, row + 1, column - 1);
        }

        octopuses[row + 1][column] += 1;
        if (row > 0 && column > 0 && row < 10 && column < 11 && octopuses[row + 1][column] == 10) {
            addEnergy(octopuses, row + 1, column);
        }

        octopuses[row + 1][column + 1] += 1;
        if (row > 0 && column > 0 && row < 10 && column < 10 && octopuses[row + 1][column + 1] == 10) {
            addEnergy(octopuses, row + 1, column + 1);
        }
    }
}
