package main.day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day20 {

    public void firstPart(String filePath, int enhancements) throws IOException {
        Input input = readInput(filePath);
        char[] enhancementAlgorithm = input.enhancementAlgorithm;
        char[][] image = input.image;

        for (int i = 0; i < 150; i++) {
            image = padImage(image);
        }

        for (int i = 1; i <= enhancements; i++) {
            char[][] enhancedImage = copyImage(image);

            for (int j = 1; j < image.length-1; j++) {
                for (int k = 1; k < image.length-1; k++) {
                    enhancedImage[j][k] = enhancementAlgorithm[numberForPixel(image, j, k)];
                }
            }
            image = enhancedImage;
        }

        int lightCount = 0;
        for (int j = enhancements; j < image.length-enhancements; j++) {
            for (int k = enhancements; k < image.length-enhancements; k++) {
                if (image[j][k] == '#') {
                    lightCount++;
                }
            }
        }
        System.out.println(lightCount);
    }

    private int numberForPixel (char[][] image, int row, int column) {
        StringBuilder sb = new StringBuilder();
        sb.append(image[row-1][column-1]);
        sb.append(image[row-1][column]);
        sb.append(image[row-1][column+1]);
        sb.append(image[row][column-1]);
        sb.append(image[row][column]);
        sb.append(image[row][column+1]);
        sb.append(image[row+1][column-1]);
        sb.append(image[row+1][column]);
        sb.append(image[row+1][column+1]);

        String str = sb.toString();
        str = str.replaceAll("[.]", "0");
        str = str.replaceAll("[#]", "1");
        return Integer.parseInt(str, 2);
    }

    private char[][] copyImage(char[][] image) {
            return Arrays.stream(image).map(char[]::clone).toArray($ -> image.clone());
    }

    private char[][] padImage(char[][] image) {
        char[][] paddedImage = new char[image.length+2][image.length+2];

        char[] firstRow = new char[image.length+2];
        Arrays.fill(firstRow, '.');
        paddedImage[0] = firstRow;
        char[] lastRow = new char[image.length+2];
        Arrays.fill(lastRow, '.');
        paddedImage[image.length+1] = lastRow;


        for (int i = 1; i < image.length+1; i++) {
            char[] newRow = new char[image.length+2];
            Arrays.fill(newRow, '.');
            char[] row = image[i-1];
            System.arraycopy(row, 0, newRow, 1, image.length);
            paddedImage[i] = newRow;
        }
        return paddedImage;
    }

    private Input readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        int imageSize = -2;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                imageSize++;
                currentLine = reader.readLine();
            }
        }

        Input input = new Input();
        char[][] image = new char[imageSize][imageSize];

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            input.enhancementAlgorithm = currentLine.toCharArray();
            currentLine = reader.readLine();
            currentLine = reader.readLine();

            for (int i = 0; i < imageSize; i++) {
                char[] row = currentLine.toCharArray();
                image[i] = row;

                currentLine = reader.readLine();
            }
        }
        input.image = image;

        return input;
    }

    private static class Input {
        private char[] enhancementAlgorithm;
        private char[][] image;
    }
}
