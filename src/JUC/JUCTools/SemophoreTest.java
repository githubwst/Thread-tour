package JUC.JUCTools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Administrator
 */
public class SemophoreTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int index = 1; index <= 20; index++) {
            final int NO = index;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "尝试获取资源");
                        semaphore.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long)Math.random()*20000);
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName() + "释放资源");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}
