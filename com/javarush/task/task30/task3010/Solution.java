package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try {
            String arg = args[0];
            int x = 0;
            for (int i = 2; i <= 36; i++) {
                try {
                    BigInteger num = new BigInteger(arg, i);
                    x = i;
                    if (x != 0) {
                        System.out.println(x);
                        break;
                    }
                } catch (NumberFormatException e) {
                    if (i == 36) System.out.println("incorrect");
                    continue;
                }
            }
        }
        catch (Exception e) {}
    }
}