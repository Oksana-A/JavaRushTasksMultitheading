package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder stringBuilder;
        if (a < b) {
            stringBuilder = new StringBuilder();
            for (int i = a; i <= b; i++) {
                stringBuilder.append(i + " ");
            }
            return stringBuilder.toString().trim();
        }
        else {
            if (a > b) {
                stringBuilder = new StringBuilder();
                for (int i = a; i >= b; i--) {
                    stringBuilder.append(i + " ");
                }
                return stringBuilder.toString().trim();
            } else return Integer.toString(a);
        }

    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(100);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}