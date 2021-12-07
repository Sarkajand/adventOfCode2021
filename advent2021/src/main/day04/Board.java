package main.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private List<Integer[]> board;
    private List<Integer> markedPositions;
    private List<Integer> markerNumbers;
    private Integer lastNumber;
    private boolean win = false;

    public Board(List<Integer[]> board) {
        this.board = board;
        this.markedPositions = new ArrayList<>();
        this.markerNumbers = new ArrayList<>();
    }

    public void markNumber(int newNumber) {
        this.lastNumber = newNumber;
        int rowNumber = 1;

        for (Integer[] row : board) {
            for (int column = 0; column < row.length; column++) {
                if (newNumber == row[column]) {
                    markedPositions.add(rowNumber * 10 + column + 1);
                    markerNumbers.add(newNumber);
                    checkBingo();
                }
            }
            rowNumber++;
        }
    }

    public boolean isWinner() {
        return this.win;
    }

    private void checkBingo() {
        Map<Integer, List<Integer>> markedOnRows = markedPositions.stream().collect(Collectors.groupingBy(position -> position / 10));
        Map<Integer, List<Integer>> markedOnColumn = markedPositions.stream().collect(Collectors.groupingBy(position -> position % 10));

        markedOnRows.values().forEach(values -> {
            if (values.size() == 5) {
//                win();
                win = true;
            }
        });

        markedOnColumn.values().forEach(values -> {
            if (values.size() == 5) {
//                win();
                win = true;
            }
        });
    }

    public void win() {
        int unmarkedSum = 0;
        for (Integer[] row : board) {
            for (int number : row) {
                if (!markerNumbers.contains(number)) {
                    unmarkedSum = unmarkedSum + number;
                }
            }
        }

        throw new RuntimeException("win with score " + unmarkedSum * lastNumber);
    }

}
