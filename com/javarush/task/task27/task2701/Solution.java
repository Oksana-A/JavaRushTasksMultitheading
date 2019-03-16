package com.javarush.task.task27.task2701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Избавляемся от меток
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        String substring = reader.readLine();

        if (isSubstringPresent(substring, string)) {
            System.out.println("String: \n" + substring + "\nis present in the string: \n" + string);
        } else {
            System.out.println("String: \n" + substring + "\nis not present in the string: \n" + string);
        }
    }

    static boolean isSubstringPresent(String substring, String string) {
//        boolean found = false;
//        int x = 0;
//        int k = 0;
//        if (string.length() > substring.length()) {
//            for (int i = 0; i < string.length(); i++) {
//                if (string.charAt(i) == substring.charAt(k)) {
//                    k++;
//                    x++;
//                } else x = 0;
//                if (x == substring.length()) found = true;
//            }
//        }

        return string.contains(substring);
    }
}

