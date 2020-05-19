package JUC.JUCTools;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 */
public class CountLatchTest implements Runnable {

    private CountDownLatch countDownLatch;

    public CountLatchTest(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("线程"+Thread.currentThread().getName()+"开始等待");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"等待結束，開始執行");
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountLatchTest r = new CountLatchTest(countDownLatch);
        new Thread(r, "A").start();
        new Thread(r, "B").start();
        new Thread(r, "C").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("释放开始信号");
        countDownLatch.countDown();
    }
}
