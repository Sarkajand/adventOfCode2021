package main.day21;

public class Day21 {

    public void firstPart(int player1, int player2) {

        int player1position = player1;
        int player2position = player2;
        int score1 = 0;
        int score2 = 0;
        int totalRoll = 0;

        while (score2 < 1000) {
            player1position = movePlayer(totalRoll, player1position);
            score1 = score1 + player1position;
            totalRoll = totalRoll + 3;
            if (score1 > 999) {
                break;
            }

            player2position = movePlayer(totalRoll, player2position);
            score2 = score2 + player2position;
            totalRoll = totalRoll + 3;
        }

        System.out.println(Math.min(score1, score2) * totalRoll);
    }

    public void secondPart(int player1, int player2) {
        ActualState actualState = new ActualState(player1, 0, player2, 0, 1);
        Wins wins = new Wins();
        play(actualState, wins, true);

        System.out.println(Math.max(wins.player1wins, wins.player2wins));
    }

    private void play(ActualState actualState, Wins wins, boolean playPlayer1) {

        for (int diceValue = 3; diceValue < 10; diceValue++) {
            PositionScore newPositionScore = countPositionScore(new PositionScore(actualState.player1position, actualState.player1score), diceValue);
            long newUniverseCountForDiceValue = getNewUniverseCountForDiceValue(diceValue);

            if (newPositionScore.score > 20) {
                if (playPlayer1) {
                    wins.player1wins = wins.player1wins + actualState.count * newUniverseCountForDiceValue;
                } else {
                    wins.player2wins = wins.player2wins + actualState.count * newUniverseCountForDiceValue;
                }
            } else {
                play(new ActualState(actualState.player2position, actualState.player2score, newPositionScore.position, newPositionScore.score, actualState.count * newUniverseCountForDiceValue), wins, !playPlayer1);
            }
        }
    }

    private PositionScore countPositionScore(PositionScore positionScore, int diceValue) {
        int position = positionScore.position;
        int score = positionScore.score;
        int newPosition = ((position + diceValue) % 10) != 0 ? (position + diceValue) % 10 : 10;
        int newScore = score + newPosition;
        return new PositionScore(newPosition, newScore);
    }

    private int getNewUniverseCountForDiceValue(int diceValue) {
        switch (diceValue) {
            case 3:
                return 1;
            case 4:
                return 3;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 6;
            case 8:
                return 3;
            case 9:
                return 1;
            default:
                return 0;
        }
    }

    private int movePlayer(int totalRoll, int playerPosition) {
        int score = totalRoll + 1 + totalRoll + 2 + totalRoll + 3;
        return ((playerPosition + score) % 10) != 0 ? (playerPosition + score) % 10 : 10;
    }

    private static class PositionScore {
        int position;
        int score;

        public PositionScore(int position, int score) {
            this.position = position;
            this.score = score;
        }
    }

    private static class ActualState {
        int player1position;
        int player1score;
        int player2position;
        int player2score;
        long count;

        public ActualState(int player1position, int player1score, int player2position, int player2score, long count) {
            this.player1position = player1position;
            this.player1score = player1score;
            this.player2position = player2position;
            this.player2score = player2score;
            this.count = count;
        }
    }

    private static class Wins {
        long player1wins = 0;
        long player2wins = 0;
    }
}
