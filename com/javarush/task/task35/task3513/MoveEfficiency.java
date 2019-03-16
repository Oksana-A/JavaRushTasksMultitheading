package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public int getNumberOfEmptyTiles() {
        return numberOfEmptyTiles;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(MoveEfficiency me) {
        int answer = 0;
        answer = Integer.compare(numberOfEmptyTiles, me.getNumberOfEmptyTiles());
        if (answer == 0)
            answer = Integer.compare(score, me.getScore());
        return answer;
    }
}
