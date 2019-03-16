package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        ArrayList<String> token = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            token.add(tokenizer.nextToken());
        }
        String[] array = new String[token.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = token.get(i);
        }
        return array;
    }
}
