
class RepeatThread extends Thread {
    private String msg;
    private int n;
    public RepeatThread(String msg, int n) {
        this.msg = msg;
        this.n = n;
    }
    @Override public void run() {
        for (int i = 0; i < n; i++) {
            System.out.println(msg);
        }
    }
}

/* Custom thread with custom constructor */
class PrintThread extends Thread {
    private String msg; // Store constructor message
    public PrintThread(String msg) {
        this.msg = msg; // Saves constructor message
    }
    @Override public void run() {
        System.out.println(msg);
    }
}

 class MyThread1 extends Thread {
    @Override public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("I'm Thread 1");
        }
    }
}

 class MyThread2 extends Thread {
     @Override public void run() {
         for (int i = 0; i < 100; i++) {
             System.out.println("I'm Thread 2");
         }
     }
 }

 class MyThread extends Thread {
     @Override public void run() {
         System.out.println("I am a new thread");
     }
 }

public class Main {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();
        t1.start();
        t2.start();
        /*Thread t1 = new RepeatThread("message 1", 100);
        Thread t2 = new RepeatThread("message 2", 100);
        t1.start();
        t2.start();*/
        Thread t = new PrintThread("Hello");
        t.start();
    }
}