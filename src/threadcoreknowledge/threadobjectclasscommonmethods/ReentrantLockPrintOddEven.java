package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPrintOddEven implements Runnable {
    private static final ReentrantLock reentrantLock = new ReentrantLock(true);
    private static int count = 1;


    public static void main(String[] args) {
        ReentrantLockPrintOddEven r = new ReentrantLockPrintOddEven();
        Thread thread1 = new Thread(r, "线程A");

        Thread thread2 = new Thread(r, "线程B");

        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        while(count<20){
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ":" + count++);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }
    }
}
