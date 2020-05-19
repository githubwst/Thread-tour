package background;

import java.util.concurrent.*;

/**
 * @ClassName MultiThreadError
 * @Description 第二种线程安全问题：线程活跃性问题-死锁
 * @Author wangst71
 * @Date 2019/10/29 16:14
 **/
public class MultiThreadError {

    static final Object resourcesA = new Object();
    static final Object resourcesB = new Object();

    public static void main(String[] args) throws InterruptedException {

        /*ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();*/


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourcesA) {
                    try {
                        System.out.println("111");
                        Thread.sleep(500);
                        System.out.println("444");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resourcesB) {
                        System.out.println("111");
                    }
                }

            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resourcesB) {
                try {
                    System.out.println("222");
                    Thread.sleep(500);
                    System.out.println("333");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourcesA) {
                    System.out.println("222");
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("运行结束");
    }


}
