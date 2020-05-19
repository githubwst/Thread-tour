package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName SleepDontReleaseMoniter
 * @Description Sleep不会释放Monitor和lock锁，wait会释放
 * @Author wangst71
 * @Date 2019/10/27 14:36
 **/
public class SleepDontReleaseMonitor implements Runnable{

    public static void main(String[] args) {
        SleepDontReleaseMonitor s = new SleepDontReleaseMonitor();
        new Thread(s,"线程A").start();
        new Thread(s,"线程B").start();
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        System.out.println("线程"+Thread.currentThread().getName()+"获得了monitor");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"退出同步代码块");
    }
}
