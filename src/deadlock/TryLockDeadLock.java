package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TryLockDeadLock
 * @Description 使用tryLock来避免死锁
 * @Author wangst71
 * @Date 2019/10/31 17:43
 **/
public class TryLockDeadLock implements Runnable {
    int flag =1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadLock tryLockDeadLock1 = new TryLockDeadLock();
        TryLockDeadLock tryLockDeadLock2 = new TryLockDeadLock();
        tryLockDeadLock2.flag=0;
        Thread t1 = new Thread(tryLockDeadLock1);
        Thread t2 = new Thread(tryLockDeadLock2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag==1){
                try {
                    if(lock1.tryLock(800, TimeUnit.MICROSECONDS)){
                        System.out.println("线程1成功获取到锁1");
                        if(lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                            System.out.println("线程1成功获取到两把锁");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        }else {
                            System.out.println("线程1尝试获取锁2失败，同时放弃锁1");
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程1获取锁1失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag==0){
                try {
                    if(lock2.tryLock(3000, TimeUnit.MICROSECONDS)){
                        System.out.println("线程2成功获取到锁2");
                        if(lock1.tryLock(3000,TimeUnit.MILLISECONDS)){
                            System.out.println("线程2成功获取到两把锁");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        }else {
                            System.out.println("线程2尝试获取锁1失败，同时释放锁2");
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程2获取锁2失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
