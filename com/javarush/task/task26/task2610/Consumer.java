package com.javarush.task.task26.task2610;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (Object x : queue){
                System.out.println(x);
                queue.remove(x);
            }
//            int i = 0;
//            while (true) {
//                queue.put(String.valueOf(i++));
//                Thread.sleep(300);
//            }
        } catch (Exception e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }

}
