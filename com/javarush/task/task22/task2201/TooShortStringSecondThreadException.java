package com.javarush.task.task22.task2201;

public class TooShortStringSecondThreadException extends RuntimeException {
    protected Throwable cause = new java.lang.StringIndexOutOfBoundsException();

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }
}
