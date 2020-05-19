package threadcoreknowledge.UncaughtException;

/**
 * @ClassName CantCatchDirectly
 * @Description try/catch 只能捕获本线程所出现的异常，无法捕获子线程异常
 *
 *
 * @Author wangst71
 * @Date 2019/10/28 9:28
 **/
public class CantCatchDirectly implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CantCatchDirectly(), "Thread-1").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "Thread-2").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "Thread-3").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "Thread-4").start();
            Thread.sleep(500);
        }catch (RuntimeException e){
            System.out.println("Caught Exception");
        }
    }


    @Override
    public void run() {
        throw new RuntimeException();
    }
}
