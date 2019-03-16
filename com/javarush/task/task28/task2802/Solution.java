package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {
        static AtomicInteger poolCount = new AtomicInteger(1);
        AtomicInteger threadCount = new AtomicInteger(1);

        //volatile String myPoolCount;
        volatile int myPoolNumber;
        String threadName = "-thread-";
        String poolName = "-pool-";

        public AmigoThreadFactory() {
            //this.myPoolCount = poolCount.toString();
            this.myPoolNumber = poolCount.get();
            poolCount.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r) {
            String newName = threadName + threadCount.toString();
            threadCount.incrementAndGet();
            Thread x = new Thread(r, newName);
            x.setDaemon(false);
            x.setPriority(Thread.NORM_PRIORITY);
            //x.setName(x.getThreadGroup().getName() + this.poolName + myPoolCount + newName);
            x.setName(x.getThreadGroup().getName() + this.poolName + myPoolNumber + newName);
            return x;
        }
    }
}
