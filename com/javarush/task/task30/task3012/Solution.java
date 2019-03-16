package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
        solution.createExpression(1);
        solution.createExpression(2);
        solution.createExpression(3);
        solution.createExpression(1234);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        StringBuilder strBuilder = new StringBuilder(number + " =");

        ArrayList<Integer> listOfNum = new ArrayList<>();
        listOfNum.addAll(Arrays.asList(1, 3, 9, 27, 81, 243, 729, 2187));

        ArrayList<Character> signList = new ArrayList<>();
            if (number == 1) {
                signList.add('+');
            } else {
                if (number == 2) {
                    signList.add('-');
                    signList.add('+');
                } else {
                    int x;
                    while (true) {
                        x = number;
                        number = number/3;
                        int checker = x - number*3;
                        if (checker == 1) {
                            signList.add('+');
                        }
                        if (checker == 0) {
                            signList.add('0');
                        }
                        if (checker == 2) {
                            signList.add('-');
                            number++;
                        }
                        if (number == 1) {
                            signList.add('+');
                            break;
                        }

                    }
                }
            }
      //  System.out.println(signList.toString());


            for (int i = 0; i < signList.size(); i++) {
                if (signList.get(i) != '0') {
                    strBuilder.append(" ");
                    strBuilder.append(signList.get(i));
                    strBuilder.append(" ");
                    strBuilder.append(listOfNum.get(i));
                }
            }

      //  System.out.print("stringBuilder: ");
        System.out.println(strBuilder);
    }
}