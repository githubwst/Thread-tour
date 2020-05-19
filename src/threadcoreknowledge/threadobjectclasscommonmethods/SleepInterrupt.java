package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SleepInterrupt
 * @Description sleep使线程休眠，不占用cpu但不释放锁，期间被中断会抛出异常并清除中断标记。
 * TimeUnit.SECONDS.sleep(:s)的形式更优雅
 *
 * @Author wangst71
 * @Date 2019/10/27 15:00
 **/
public class SleepInterrupt implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupt());
        thread.start();
        Thread.sleep(5400);
        thread.interrupt();

    }

    @Override
    public void run() {
        for(int i=0;i<10 && !Thread.currentThread().isInterrupted();i++){
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("我被中断了!");
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
