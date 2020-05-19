package JMM;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName NoVolatile
 * @Description 演示volatile不适用于解决a++的问题
 * a++不具备原子性，多线程a++的同步（线程A执行完a++后，B进行a++）无法通过volatile得到保障
 *
 * @Author wangst71
 * @Date 2019/10/30 18:42
 **/
public class NoVolatile implements Runnable{
    volatile int a;
    AtomicInteger realA = new AtomicInteger();


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new NoVolatile();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(((NoVolatile)r).a);
        System.out.println(((NoVolatile)r).realA.get());
    }
}
