package org.example;

public class DaemonThreadExample extends Thread{
    String threadName;
    public DaemonThreadExample(String name) {
        threadName=name;
    }

    public  void run(){
     if(Thread.currentThread().isDaemon()){
         System.out.println(threadName+" is a daemon thread.");
     }
     else{
         System.out.println(threadName+" is a daemon not thread.");
     }
    }
}
