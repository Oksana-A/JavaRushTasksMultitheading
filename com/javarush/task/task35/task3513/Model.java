package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    public int score = 0;
    public int maxTile = 0;
    private  Model model;
    private  View view;
    private Stack previousStates;
    private Stack previousScores;
    private boolean isSaveNeeded = true;

    public Model() {
        previousScores = new Stack();
        previousStates = new Stack();
        resetGameTiles();
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int k = 0; k < FIELD_WIDTH; k++) {
                gameTiles[i][k] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> tileList = getEmptyTiles();
        if (!tileList.isEmpty()) {
            int i =(int) (Math.random() * tileList.size());
            tileList.get(i).setValue((Math.random() < 0.9 ? 2 : 4));
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> tileList = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int k = 0; k < FIELD_WIDTH; k++) {
                if (gameTiles[i][k].isEmpty())
                    tileList.add(gameTiles[i][k]);
            }
        }

        return tileList;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean flag = false;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].isEmpty()) {
                for (int k = i + 1; k < tiles.length; k++) {
                    if (!tiles[k].isEmpty()) {
                        Tile tile = tiles[i];
                        tiles[i] = tiles[k];
                        tiles[k] = tile;
                        flag = true;
                        break;
                    }
                }
            }

        }
        return flag;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean flag = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (!tiles[i].isEmpty() && tiles[i].getValue() == tiles[i+1].getValue()) {
                int value = tiles[i].getValue() + tiles[i+1].getValue();
                if (maxTile < value)
                    maxTile = value;
                score += value;
                tiles[i].setValue(value);
                tiles[i+1].setValue(0);
                compressTiles(tiles);
                flag = true;
            }
        }
        return flag;
    }

    public void left() {
        if (isSaveNeeded) saveState(this.gameTiles);
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged) {
            addTile();
            isSaveNeeded = true;
        }


    }

    public  void right() {
        saveState(this.gameTiles);
        turnLeft();
        turnLeft();
        left();
        turnLeft();
        turnLeft();
    }

    public void  down() {
        saveState(this.gameTiles);
        turnLeft();
        left();
        turnLeft();
        turnLeft();
        turnLeft();
    }

    public  void up() {
        saveState(this.gameTiles);
        turnLeft();
        turnLeft();
        turnLeft();
        left();
        turnLeft();
    }

    private  void  turnLeft() {
        Tile[][]  buffer = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        buffer[0][0] = gameTiles[3][0];
        buffer[0][1] = gameTiles[2][0];
        buffer[0][2] = gameTiles[1][0];
        buffer[0][3] = gameTiles[0][0];
        buffer[1][0] = gameTiles[3][1];
        buffer[1][1] = gameTiles[2][1];
        buffer[1][2] = gameTiles[1][1];
        buffer[1][3] = gameTiles[0][1];
        buffer[2][0] = gameTiles[3][2];
        buffer[2][1] = gameTiles[2][2];
        buffer[2][2] = gameTiles[1][2];
        buffer[2][3] = gameTiles[0][2];
        buffer[3][0] = gameTiles[3][3];
        buffer[3][1] = gameTiles[2][3];
        buffer[3][2] = gameTiles[1][3];
        buffer[3][3] = gameTiles[0][3];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int k = 0; k < FIELD_WIDTH; k++) {
                gameTiles[i][k] = buffer[i][k];
            }
        }
        
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public int getScore() {
        return score;
    }

    public  boolean canMove() {
        boolean flag = false;
        if (!getEmptyTiles().isEmpty())
            flag = true;
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            for (int k = 0; k < FIELD_WIDTH - 1; k++) {
                if (gameTiles[i][k].value == gameTiles[i+1][k].value)
                    flag = true;
                if (gameTiles[i][k].value == gameTiles[i][k+1].value)
                    flag = true;

            }
        }
        return flag;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] buffer = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int k = 0; k < FIELD_WIDTH; k++) {
                buffer[i][k] = new Tile(tiles[i][k].getValue());
            }
        }
        int newScore = score;
        previousStates.push(buffer);
        previousScores.push(newScore);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left(); break;
            case 1:
                right(); break;
            case 2:
                up(); break;
            case 3:
                down(); break;
        }
    }

    public boolean hasBoardChanged() {
        boolean answer = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int k = 0; k < FIELD_WIDTH; k++) {
                if (gameTiles[i][k].getValue() != ((Tile[][])previousStates.peek())[i][k].getValue())
                    answer = true;
            }
        }
        return answer;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency = null;
        move.move();
        if (hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        else {
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();
        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue priorityQueue = new PriorityQueue(4, Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));
        ((MoveEfficiency)priorityQueue.poll()).getMove().move();
    }
}
