package com.javarush.task.task25.task2504;

import java.util.HashMap;

/*
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот мето
      //  HashMap<Thread, Thread.State> map = new HashMap<>();
        for (Thread thread: threads) {
            Thread.State state = thread.getState();
            switch (state) {
                case NEW: thread.start(); break;
                case TIMED_WAITING: thread.interrupt(); break;
                case WAITING: thread.interrupt(); break;
                case BLOCKED: thread.interrupt(); break;
                case RUNNABLE: thread.isInterrupted(); break;
                case TERMINATED:
                    System.out.println(thread.getPriority());
                    break;
            }
        }


    }

    public static void main(String[] args) {
    }
}
