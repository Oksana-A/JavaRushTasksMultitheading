package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        int median;
        if (array.length%2 == 0) {
            median = (array[array.length/2] + array[array.length/2 - 1])/2;
        }
        else {
            median = array[array.length/2];
        }

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int difference1 = o1 - median;
                int difference2 = o2 - median;
                if (difference1 < 0) difference1 *= (-1);
                if (difference2 < 0) difference2 *= (-1);
                return difference1 - difference2;
            }
        });

        return array;
    }
}
