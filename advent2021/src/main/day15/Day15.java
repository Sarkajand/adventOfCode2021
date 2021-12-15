package main.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day15 {

    public void firstPart(String filePath) throws IOException {
        int caveSize = getCaveSize(filePath);
        int[][] chitons = readInput(filePath, caveSize);

        getShortestPath(chitons, caveSize);
    }

    public void secondPart(String filePath) throws IOException {
        int caveSize = getCaveSize(filePath);
        int[][] chitons = readInput(filePath, caveSize);

        int[][] newChitons = new int[caveSize*5][caveSize*5];

        for (int r = 0; r < caveSize; r++) {
            int[] row = chitons[r];
            int[] row1 = Arrays.stream(row).map(i -> i+1 > 9 ? 1 : i+1).toArray();
            int[] row2 = Arrays.stream(row1).map(i -> i+1 > 9 ? 1 : i+1).toArray();
            int[] row3 = Arrays.stream(row2).map(i -> i+1 > 9 ? 1 : i+1).toArray();
            int[] row4 = Arrays.stream(row3).map(i -> i+1 > 9 ? 1 : i+1).toArray();
            int[] newRow = Arrays.copyOf(row, caveSize*5);
            System.arraycopy(row1, 0, newRow, caveSize, caveSize);
            System.arraycopy(row2, 0, newRow, caveSize*2, caveSize);
            System.arraycopy(row3, 0, newRow, caveSize*3, caveSize);
            System.arraycopy(row4, 0, newRow, caveSize*4, caveSize);
            newChitons[r] = newRow;
        }
        
        for (int j = 1; j < 5; j++) {
            for (int r = 0; r < caveSize; r++) {
                int[] row = newChitons[r];
                int x = j;
                int[] newRow = Arrays.stream(row).map(i -> i+x > 9 ? i+x-9 : i+x).toArray();
                newChitons[(caveSize)*j + r] = newRow;
            }
        }

        getShortestPath(newChitons, caveSize*5);

    }

    private void getShortestPath(int[][] chitons, int caveSize) {
        int[][] bestScores = new int[caveSize][caveSize];
        for (int[] row: bestScores) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            int distance = currentNode.score;
            int row = currentNode.row;
            int column = currentNode.column;

            List<Node> nextNodes = new ArrayList<>(Arrays. asList(new Node(row - 1, column), new Node(row, column + 1), new Node(row, column - 1), new Node(row + 1, column)));

            for (Node node : nextNodes) {
                if (node.row >= 0 && node.row < caveSize && node.column >= 0 && node.column < caveSize) {
                    int newDistance = distance + chitons[node.row][node.column];
                    if (newDistance < bestScores[node.row][node.column]) {
                        bestScores[node.row][node.column] = newDistance;
                        node.score = newDistance;
                        queue.add(node);
                    }
                }
            }
        }

        System.out.println(bestScores[caveSize-1][caveSize-1]);
    }

    private int getCaveSize(String filePath) throws IOException {
        int caveSize = 0;
        String file = "src/main/resources/" + filePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                caveSize++;
                currentLine = reader.readLine();
            }
        }
        return caveSize;
    }

    private int[][] readInput(String filePath, int caveSize) throws IOException {
        String file = "src/main/resources/" + filePath;

        int[][] chitons = new int[caveSize][caveSize];

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();

            for (int i = 0; i < caveSize; i++) {
                int[] row = currentLine.chars().map(Character::getNumericValue).toArray();
                chitons[i] = row;

                currentLine = reader.readLine();
            }
        }

        return chitons;
    }

    private static class Node {
        int row;
        int column;
        int score;

        public Node(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public Node(int row, int column, int score) {
            this.row = row;
            this.column = column;
            this.score = score;
        }
    }
}
