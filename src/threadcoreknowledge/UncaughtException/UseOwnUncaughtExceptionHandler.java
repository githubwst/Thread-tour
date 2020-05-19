package threadcoreknowledge.UncaughtException;

/**
 * @ClassName UseOwnUncaughtExceptionHandler
 * @Description 使用自己创建的UncaughtExceptionHandler
 * @Author wangst71
 * @Date 2019/10/28 9:43
 **/
public class UseOwnUncaughtExceptionHandler implements Runnable{
    public static void main(String[] args) throws InterruptedException {
            Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
            new Thread(new UseOwnUncaughtExceptionHandler(), "Thread-1").start();
            Thread.sleep(500);
            new Thread(new UseOwnUncaughtExceptionHandler(), "Thread-2").start();
            Thread.sleep(500);
            new Thread(new UseOwnUncaughtExceptionHandler(), "Thread-3").start();
            Thread.sleep(500);
            new Thread(new UseOwnUncaughtExceptionHandler(), "Thread-4").start();
            Thread.sleep(500);

    }


    @Override
    public void run() {
        throw new RuntimeException();
    }
}
