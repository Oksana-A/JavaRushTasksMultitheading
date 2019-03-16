package com.javarush.task.task22.task2201;

public class TooShortStringFirstThreadException extends RuntimeException {
    protected Throwable cause = new java.lang.StringIndexOutOfBoundsException();

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }


}
