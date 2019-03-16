package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread thread;

    LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State state = thread.getState();
        System.out.println(state);
        State state1;
        while (state != State.TERMINATED){
            state1 = thread.getState();
            if (state != state1) {
                System.out.println(state1);
                state = state1;
            }
        }
    }
}
