package main.day23;

import java.util.*;

public class Day23 {

    public void SecondPart() {
        int[][] boardState = new int[5][11];

//        input
        boardState[0] = new int[]{0,0,0,    0,0,    0,0, 0,0,   0,0};
        boardState[1] = new int[]{0,0,100,  0,10,   0,1, 0,1000,0,0};
        boardState[2] = new int[]{0,0,1000, 0,100,  0,10,0,1,   0,0};
        boardState[3] = new int[]{0,0,1000, 0,10,   0,1, 0,100, 0,0};
        boardState[4] = new int[]{0,0,100,  0,1000, 0,1, 0,10,  0,0};


//      test
//        boardState[0] = new int[]{0,0,0,    0,0,    0,0,    0,0,   0,0};
//        boardState[1] = new int[]{0,0,10,   0,100,  0,10,   0,1000,0,0};
//        boardState[2] = new int[]{0,0,1000, 0,100,  0,10,   0,1,   0,0};
//        boardState[3] = new int[]{0,0,1000, 0,10,   0,1,    0,100, 0,0};
//        boardState[4] = new int[]{0,0,1,    0,1000, 0,100,  0,1,   0,0};


        List<Integer> scores = new ArrayList<>();


//        long startTime = System.currentTimeMillis();
//        play(boardState, 0, scores);

//        little faster then play()
        play2(boardState, 0, scores);
//        long finishTime = System.currentTimeMillis();
//        System.out.println("That took: " + (finishTime - startTime) + " ms");

        Collections.sort(scores);
        System.out.println(scores.get(0));
    }

    private void play(int[][] boardState, int score, List<Integer> scores) {
        List<int[]> canMoveAmphipods = canMove(boardState);
        Map<int[], List<int[]>> possiblePositionsForAmphipods = new HashMap<>();

        for (int[] movingAmphipoda : canMoveAmphipods) {
            List<int[]> possiblePositions = getPossiblePositions(movingAmphipoda, boardState);
            possiblePositionsForAmphipods.put(movingAmphipoda, possiblePositions);
        }

        for (int[] movingAmphipoda : canMoveAmphipods) {
            for (int[] possiblePosition : possiblePositionsForAmphipods.get(movingAmphipoda)) {
                int scoreOfMove = getScoreOfMove(movingAmphipoda, possiblePosition);
                int[][] newBoardState = getNewBoardState(boardState, movingAmphipoda, possiblePosition);
                play(newBoardState, score + scoreOfMove, scores);
            }
        }

        if (isWinning(boardState)) {
            scores.add(score);
        }
    }

    private List<int[]> canMove(int[][] boardState) {
        List<int[]> canMove = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (boardState[i][j] != 0) {
                    int[] movingAmphipoda = new int[]{i, j, boardState[i][j]};

                    if (i == 0) {
                        if (canMoveToHallWay(boardState[0], movingAmphipoda)) {
                            int[] positionInRoom = tryMoveToRoom(movingAmphipoda, boardState);
                            if (positionInRoom != null) {
                                canMove.add(movingAmphipoda);
                            }
                        }
                    } else {
                        if (canMoveFromRoom(movingAmphipoda, boardState)) {
                            if (boardState[0][j + 1] == 0 || boardState[0][j - 1] == 0) {
                                canMove.add(movingAmphipoda);
                            }
                        }
                    }
                }
            }
        }
        return canMove;
    }

    private void play2(int[][] boardState, int score, List<Integer> scores) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++){
                if (boardState[i][j] != 0) {
                    int[] movingAmphipoda = new int[]{i,j,boardState[i][j]};
                    List<int[]> possiblePositions = getPossiblePositions(movingAmphipoda, boardState);
                    for (int[] possiblePosition : possiblePositions) {
                        int scoreOfMove = getScoreOfMove(movingAmphipoda, possiblePosition);
                        int[][] newBoardState = getNewBoardState(boardState, movingAmphipoda, possiblePosition);
                        play2(newBoardState, score + scoreOfMove, scores);
                    }

                    if (isWinning(boardState)) {
                        scores.add(score);
                    }
                }
            }
        }
    }

    private void printState(int[][] boardState, int score, List<Integer> scores) {
        System.out.println();
        System.out.println("score: " + score);


        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int j = 0; j < 11; j++) {
            if (boardState[0][j] == 0) {
                sb.append("   0  ");
            }
            if (boardState[0][j] == 1) {
                sb.append("   1  ");
            }
            if (boardState[0][j] == 10) {
                sb.append("  10  ");
            }
            if (boardState[0][j] == 100) {
                sb.append(" 100  ");
            }
            if (boardState[0][j] == 1000) {
                sb.append("1000  ");
            }
        }
        sb.append(" ]");
        System.out.println(sb);

        for (int i = 1; i < 5; i++) {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("[ ");
            for (int j = 0; j < 11; j++) {
                if (j == 0 || j == 1 || j == 3 || j == 5 || j == 7 || j == 9 || j == 10) {
                    sb1.append("      ");
                } else {

                    if (boardState[i][j] == 0) {
                        sb1.append("   0  ");
                    }
                    if (boardState[i][j] == 1) {
                        sb1.append("   1  ");
                    }
                    if (boardState[i][j] == 10) {
                        sb1.append("  10  ");
                    }
                    if (boardState[i][j] == 100) {
                        sb1.append(" 100  ");
                    }
                    if (boardState[i][j] == 1000) {
                        sb1.append("1000  ");
                    }
                }
            }
            sb1.append(" ]");
            System.out.println(sb1);
        }
    }

    private boolean isWinning(int[][] boardState) {
        return
        boardState[1][2] == 1 &&
        boardState[2][2] == 1 &&
        boardState[3][2] == 1 &&
        boardState[4][2] == 1 &&

        boardState[1][4] == 10 &&
        boardState[2][4] == 10 &&
        boardState[3][4] == 10 &
        boardState[4][4] == 10 &&

        boardState[1][6] == 100 &&
        boardState[2][6] == 100 &&
        boardState[3][6] == 100 &&
        boardState[4][6] == 100 &&

        boardState[1][8] == 1000 &&
        boardState[2][8] == 1000 &&
        boardState[3][8] == 1000 &&
        boardState[4][8] == 1000;
    }

    private int[][] getNewBoardState(int[][] boardState, int[] movingAmphipoda, int[] position) {
        int[][] newBoardState = Arrays.stream(boardState).map(int[]::clone).toArray($ -> boardState.clone());
        newBoardState[movingAmphipoda[0]][movingAmphipoda[1]] = 0;
        newBoardState[position[0]][position[1]] = movingAmphipoda[2];
        return newBoardState;
    }

    private int getScoreOfMove(int[] movingAmphipoda, int[] position) {
        int scoreOfMove = Math.max(movingAmphipoda[0], position[0]) + ((Math.max(movingAmphipoda[1], position[1])) - (Math.min(movingAmphipoda[1], position[1])));
        return scoreOfMove * movingAmphipoda[2];
    }

    private List<int[]> getPossiblePositions(int[] movingAmphipoda, int[][] boardState) {
        List<int[]> possiblePositions = new ArrayList<>();

        if (movingAmphipoda[0] == 0) {
            if (canMoveToHallWay(boardState[0], movingAmphipoda)) {
                int[] positionInRoom = tryMoveToRoom(movingAmphipoda, boardState);
                if (positionInRoom != null) {
                    possiblePositions.add(positionInRoom);
                }
            }
        } else {
            if (canMoveFromRoom(movingAmphipoda, boardState)) {
                for (int i = movingAmphipoda[1]; i < 11; i++) {
                    if (i != 2 && i != 4 && i != 6 && i != 8 && boardState[0][i] == 0) {
                        possiblePositions.add(new int[]{0, i});
                    } else if (i != 2 && i != 4 && i != 6 && i != 8 && boardState[0][i] != 0) {
                        break;
                    }
                }

                for (int i = movingAmphipoda[1]; i > -1; i--) {
                    if (i != 2 && i != 4 && i != 6 && i != 8 && boardState[0][i] == 0) {
                        possiblePositions.add(new int[]{0, i});
                    } else if (i != 2 && i != 4 && i != 6 && i != 8 && boardState[0][i] != 0) {
                        break;
                    }
                }
            }
        }
        return possiblePositions;
    }

    private boolean canMoveFromRoom(int[] movingAmphipoda, int[][] boardState) {
        if (boardState[movingAmphipoda[0] - 1][movingAmphipoda[1]] != 0) {
            return false;
        }

        if (movingAmphipoda[2] == 1 && movingAmphipoda[1] == 2) {
            for (int i = 4; i > movingAmphipoda[0]; i--) {
                if (boardState[i][2] != 1) {
                    return true;
                }
            }
            return false;
        }

        if (movingAmphipoda[2] == 10 && movingAmphipoda[1] == 4) {
            for (int i = 4; i > movingAmphipoda[0]; i--) {
                if (boardState[i][4] != 10) {
                    return true;
                }
            }
            return false;
        }

        if (movingAmphipoda[2] == 100 && movingAmphipoda[1] == 6) {
            for (int i = 4; i > movingAmphipoda[0]; i--) {
                if (boardState[i][6] != 100) {
                    return true;
                }
            }
            return false;
        }

        if (movingAmphipoda[2] == 1000 && movingAmphipoda[1] == 8) {
            for (int i = 4; i > movingAmphipoda[0]; i--) {
                if (boardState[i][8] != 1000) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    private int[] tryMoveToRoom(int[] movingAmphipoda, int[][] boardState) {
        int[] positionInTheRoom = null;
        if (movingAmphipoda[2] == 1) {
            for (int i = 4; i > 0; i--) {
                if (boardState[i][2] == 0) {
                    positionInTheRoom = new int[]{i, 2};
                    break;
                } else if (boardState[i][2] != 1) {
                    break;
                }
            }
        }

        if (movingAmphipoda[2] == 10) {
            for (int i = 4; i > 0; i--) {
                if (boardState[i][4] == 0) {
                    positionInTheRoom = new int[]{i, 4};
                    break;
                } else if (boardState[i][4] != 10) {
                    break;
                }
            }
        }

        if (movingAmphipoda[2] == 100) {
            for (int i = 4; i > 0; i--) {
                if (boardState[i][6] == 0) {
                    positionInTheRoom = new int[]{i, 6};
                    break;
                } else if (boardState[i][6] != 100) {
                    break;
                }
            }
        }

        if (movingAmphipoda[2] == 1000) {
            for (int i = 4; i > 0; i--) {
                if (boardState[i][8] == 0) {
                    positionInTheRoom = new int[]{i, 8};
                    break;
                } else if (boardState[i][8] != 1000) {
                    break;
                }
            }
        }

        return positionInTheRoom;
    }

    private boolean canMoveToHallWay(int[] hallWay, int[]movingAmphipoda) {
        if (movingAmphipoda[2] == 1) {
            for (int i = Math.min(movingAmphipoda[1], 2) + 1; i < Math.max(movingAmphipoda[1], 2); i++) {
                if (hallWay[i] != 0) {
                    return false;
                }
            }
            return true;
        }

        if (movingAmphipoda[2] == 10) {
            for (int i = Math.min(movingAmphipoda[1], 4) + 1; i < Math.max(movingAmphipoda[1], 4); i++) {
                if (hallWay[i] != 0) {
                    return false;
                }
            }
            return true;
        }

        if (movingAmphipoda[2] == 100) {
            for (int i = Math.min(movingAmphipoda[1], 6) + 1; i < Math.max(movingAmphipoda[1], 6); i++) {
                if (hallWay[i] != 0) {
                    return false;
                }
            }
            return true;
        }

        if (movingAmphipoda[2] == 1000) {
            for (int i = Math.min(movingAmphipoda[1], 8) + 1; i < Math.max(movingAmphipoda[1], 8); i++) {
                if (hallWay[i] != 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
