package com.javarush.task.task25.task2505;

/* 
Без дураков
1. Создай private class MyUncaughtExceptionHandler, который на перехват исключения должен подождать половину секунды, а затем вывести на экран secretKey, имя трэда и сообщение возникшего исключения.
Используй String.format(...).

Пример:
super secret key, Thread-0, it's an example

2. Разберись в последовательности выполняемого кода и обеспечь логирование возникновения исключения в п.1.
3. Метод main в тестировании не участвует.


Требования:
1. Создай private class MyUncaughtExceptionHandler с конструктором по-умолчанию в классе MyThread.
2. Во время перехвата исключения, MyUncaughtExceptionHandler должен вызвать Thread.sleep(500).
3. Затем, MyUncaughtExceptionHandler должен выводить в консоль secretKey, имя трэда и сообщение возникшего исключения.
4. Для вывода сообщения используй String.format(...).
5. Нужно обеспечить логирование возникновения исключения.
*/
public class Solution {

    public static void main(String[] args) {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
          //  setDaemon(true);
        }

        public String getSecretKey() {
            return secretKey;
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }

        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    t.sleep(500);
                    String message = String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage());
                    System.out.println(message);
                }
                catch (InterruptedException exc) {
                    exc.getMessage();
                }
            }
        }
    }




}

