package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;
/*

 */

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move(){
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).move();
        }
    }

    public void print() {
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public Horse getWinner(){
        Horse aHorse = horses.get(0);
        for (int i = 0; i < horses.size(); i++) {
            if (aHorse.getDistance() < horses.get(i).getDistance()) {
                aHorse = horses.get(i);
            }
        }
        return aHorse;
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner().getName());
    }


    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(new ArrayList<Horse>());
        game.horses.add(new Horse("Horse1", 3, 0));
        game.horses.add(new Horse("Horse2", 3, 0));
        game.horses.add(new Horse("Horse3", 3, 0));
        game.run();
        game.printWinner();

    }
}
