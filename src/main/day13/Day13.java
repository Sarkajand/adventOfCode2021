package main.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day13 {

    public void getAnswers(String filePath) throws IOException {
        Set<Dot> dots = new HashSet<>();
        Queue<String> folds = new LinkedList<>();

        readInput(filePath, dots, folds);

        int step = 0;
        while (!folds.isEmpty()) {
            String fold = folds.remove();
            boolean foldByX = fold.charAt(0) == 'x';
            int foldValue = Integer.parseInt(fold.substring(2));

            Set<Dot> dotsAfterFold = dots.stream()
                    .filter(dot -> {
                        int value = foldByX ? dot.getX() : dot.getY();
                        return value > foldValue;
                    })
                    .map(dot -> {
                        int value = foldByX ? dot.getX() : dot.getY();
                        value = foldValue - (value - foldValue);
                        Dot newDot = new Dot();
                        if (foldByX) {
                            newDot.setX(value);
                            newDot.setY(dot.getY());
                        } else {
                            newDot.setY(value);
                            newDot.setX(dot.getX());
                        }
                        return newDot;
                    }).collect(Collectors.toSet());

            dots.removeIf(dot -> {
                int value = foldByX ? dot.getX() : dot.getY();
                return value > foldValue;
            });

            dots.addAll(dotsAfterFold);

            step++;
            if (step == 1) {
                System.out.println("dots after first fold: " + dots.size());
            }
        }

        printDots(dots);
    }

    private void printDots(Set<Dot> dots) {
        String[][] dotsArray = new String[6][39];

        for (String[] row : dotsArray)
            Arrays.fill(row, ".");

        dots.forEach(dot -> dotsArray[dot.getY()][dot.getX()] = "#");

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(dotsArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    private void readInput(String filePath, Set<Dot> dots, Queue<String> folds) throws IOException {
        String file = "src/main/resources/" + filePath;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLine.matches("\\d+,\\d+")) {
                    String[] coordinates = currentLine.split(",");
                    dots.add(new Dot(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
                } else if (currentLine.matches("fold along [x,y]=\\d+")) {
                    folds.add(currentLine.substring(11));
                }
                currentLine = reader.readLine();
            }
        }
    }
}
