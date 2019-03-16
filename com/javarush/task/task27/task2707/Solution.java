package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        Thread thread1 = new Thread() {
            public void run() {
            synchronized (o1)
            {
                try {
                    this.sleep(500);
                }
                catch (InterruptedException e) {
                    e.getMessage();
                }
                synchronized (o2) {
                }
            }
        }
        };
        Thread thread2 = new Thread(){
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1,o2);
            }
        };
        thread1.start();
        thread2.start();
        thread2.sleep(2000);
        return thread2.getState() == Thread.State.BLOCKED ? false : true;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
