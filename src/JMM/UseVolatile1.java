package JMM;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName UseVolatile
 * @Description 演示volatile适用场景：
 * 1、多线程仅赋值,boolean flag
 *
 * @Author wangst71
 * @Date 2019/10/30 18:52
 **/
public class UseVolatile1 implements Runnable{
    volatile boolean done;
    AtomicInteger realA = new AtomicInteger();


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        //与之前状态无关，仅赋值
        done = true;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new UseVolatile1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(((UseVolatile1)r).done);
        System.out.println(((UseVolatile1)r).realA.get());
    }
}

