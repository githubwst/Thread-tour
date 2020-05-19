package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName WaitNotifyReleaseOwnMoniter
 * @Description  证明wait只释放当前那把锁
 *
 * @Author wangst71
 * @Date 2019/10/27 10:52
 **/
public class WaitNotifyReleaseOwnMoniter {

    private static final Object resourcesA = new Object();
    private static final Object resourcesB = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourcesA) {
                    System.out.println("1获取A锁");
                    synchronized (resourcesB) {
                        System.out.println("1获取B锁");
                        try {
                            System.out.println("1释放A锁");
                            resourcesA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourcesA) {
                    System.out.println("2获得A锁");
                    System.out.println("2试图获得B锁");
                    synchronized (resourcesB) {
                        System.out.println("2获得B锁");
                    }
                }

            }
        });

        thread1.start();
        thread2.start();

    }

}
