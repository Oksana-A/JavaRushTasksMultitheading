package com.javarush.task.task22.task2203;

/* 
Между табуляциями
1. Класс TooShortStringException должен быть потомком класса Exception.
2. Метод getPartOfString должен принимать строку в качестве параметра.
3. В случае, если строка, переданная в метод getPartOfString содержит менее 2 табуляций должно возникнуть исключение TooShortStringException.
4. Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
            if (string == null) throw new TooShortStringException();
            String str = null;
            int tab1 = string.indexOf("\t");
      //  System.out.println("tab1: " + tab1);
            if (tab1 != -1) {
                str = string.substring(tab1 + 1);
             //   System.out.println("str-tab1:" + str);
            }
            else {throw new TooShortStringException(); }
            int tab2 = str.indexOf("\t");
     //   System.out.println("tab2: " + tab2);
            if (tab2 != -1) {
                str = str.substring(0, tab2);
            }else {throw new TooShortStringException();}

        return str;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
