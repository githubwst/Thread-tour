package threadcoreknowledge.threadobjectclasscommonmethods;

import sun.util.resources.cldr.kl.LocaleNames_kl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SleepDontReleaseLock
 * @Description Sleep不会释放Monitor和lock锁，wait会释放
 * @Author wangst71
 * @Date 2019/10/27 14:36
 **/
public class SleepDontReleaseLock implements Runnable{

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        SleepDontReleaseLock s = new SleepDontReleaseLock();
        new Thread(s,"线程A").start();
        new Thread(s,"线程B").start();
    }

    @Override
    public void run() {
        LOCK.lock();
        System.out.println("线程" +
                Thread.currentThread().getName()+"获取到了lock锁");
        try {
            Thread.sleep(2000);
            System.out.println("线程"+Thread.currentThread().getName()+"已经被唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            LOCK.unlock();
        }

    }


}
