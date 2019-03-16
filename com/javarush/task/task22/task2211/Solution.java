package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Charset windows1251 = Charset.forName("Windows-1251");
        Charset utf8 = Charset.forName("UTF-8");

        FileInputStream inputStream = new FileInputStream(new File(args[0]));
        FileOutputStream outputStream = new FileOutputStream(new File(args[1]));

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        while (inputStream.available() > 0) {
            buffer.write(inputStream.read());
        }
        String s = new String(buffer.toByteArray(), windows1251);
        byte[] buffer1 = s.getBytes(utf8);
        outputStream.write(buffer1);
    }
}
