package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName WaitNotifyPrintOddEvenSyn
 * @Description 两个线程交替打印1~100，使用wait/notify实现
 * @Author wangst71
 * @Date 2019/10/27 13:28
 **/
public class WaitNotifyPrintOddEven {
    //1.拿到锁就打印
    //2.打印完后++，唤醒另一线程后休眠
    private static int count = 1;
    private static final Object lock = new Object();

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (count <= 100) {
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName()
                                + ":" + count++);
                        lock.notify();
                        if (count <= 100) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };

        Thread thread1 = new Thread(r, "线程A");

        Thread thread2 = new Thread(r, "线程B");

        thread1.start();
        thread2.start();

    }

}
