package org.example;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        MyRunnable thread1=new MyRunnable();
        Thread myThread = new Thread(thread1);
        Thread thread2=new Thread(new MyRunnable());
        myThread.start();
        thread2.start();
        ProducerConsumer pc = new ProducerConsumer();
        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();

        MyThread thread1 = new MyThread();
        MyThread thread2= new MyThread();
        MyThread thread3= new MyThread();

        thread1.start();
        thread2.start();
        thread3.start();

        Thread run1=new Thread(new RunnableExample());
        Thread run2=new Thread(new RunnableExample());
        run1.start();
        run2.start();
        Runnable task = LambdaThreadExample.getTask();
        Thread thread1= new Thread(task);
        Thread thread2= new Thread(task);
        thread1.start();
        thread2.start();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(task);
        executorService.shutdown();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        ScheduledFuture<String> result = executor.schedule(() -> {
            System.out.println("Thread is executing the job");
            return "completed";
        }, 10, TimeUnit.SECONDS);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();

         JoinExample newThread = new JoinExample();
         newThread.start();
        try {
            newThread.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("main thread completed");

        Thread Priority
        System.out.println(thread1.getPriority());
        System.out.println(thread2.getPriority());
        System.out.println(thread3.getPriority());

        thread1.setPriority(10);
        thread2.setPriority(Thread.MIN_PRIORITY);
        System.out.println(thread1.getPriority());
        System.out.println(thread2.getPriority());
        System.out.println(thread3.getPriority());

//      Priority of the Main Thread
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println(Thread.currentThread().getPriority());

        DaemonThreadExample newThread1 = new DaemonThreadExample("Daemon1 Thread");
        DaemonThreadExample newThread2 = new DaemonThreadExample("Daemon2 Thread");

        newThread1.setDaemon(true);

        newThread1.start();
        newThread2.start();
        newThread2.setDaemon(true);
    }
}