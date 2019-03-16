package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    static AtomicInteger atomicInteger = new AtomicInteger();
    public MyThread() {
        setPriority((atomicInteger.get()<10)?atomicInteger.addAndGet(1):atomicInteger.addAndGet(-9));
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority((atomicInteger.get()<10)?atomicInteger.addAndGet(1):atomicInteger.addAndGet(-9));
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority((atomicInteger.get()<10)?atomicInteger.addAndGet(1):atomicInteger.addAndGet(-9));
    }

    public MyThread(String name) {
        super(name);
        setPriority((atomicInteger.get()<10)?atomicInteger.addAndGet(1):atomicInteger.addAndGet(-9));
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority((atomicInteger.get()<10)?atomicInteger.addAndGet(1):atomicInteger.addAndGet(-9));
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority((atomicInteger.get()<10)?atomicInteger.addAndGet(1):atomicInteger.addAndGet(-9));
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority((atomicInteger.get()<10)?atomicInteger.addAndGet(1):atomicInteger.addAndGet(-9));
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority((atomicInteger.get()<10)?atomicInteger.addAndGet(1):atomicInteger.addAndGet(-9));

    }
}
