package main.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day04 {

    private List<Board> boards = new ArrayList<>();
    private Set<Board> winners = new HashSet<>();
    private List<Integer> numbers;

    public void readInput(String filePath) throws IOException {
        String file = "src/main/resources/" + filePath;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstLine = reader.readLine();
            this.numbers = Arrays.stream(firstLine.split(",")).map(Integer::parseInt).collect(Collectors.toList());

            String currentLine = reader.readLine();
            do {
                if (currentLine.isEmpty()) {
                    currentLine = reader.readLine();

                    List<Integer[]> board = new ArrayList<>();
                    while (currentLine != null && !currentLine.isEmpty()) {
                        board.add(Arrays.stream(currentLine.split(" ")).filter(s -> s.matches("[0-9]+")).map(Integer::parseInt).toArray(Integer[]::new));

                        currentLine = reader.readLine();
                    }
                    boards.add(new Board(board));
                }
            } while (currentLine != null);
        }
    }

    public void play() {
        for (Integer number : numbers) {
            for (Board board : boards) {
                board.markNumber(number);
                if (board.isWinner()) {
                    winners.add(board);
                }
                if (boards.size() == winners.size()) {
                    board.win();
                }
            }
        }
    }
}
