package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable,CustomThreadManipulator {
    private Thread thread;

    @Override
    public void run() {
        try {
            while (!thread.isInterrupted()) {
                System.out.println(thread.getName());
                thread.sleep(100);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(String threadName) {
        thread = new Thread(this, threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
