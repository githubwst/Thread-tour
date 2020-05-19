package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName WaitNotifyAll
 * @Description 展示notity和notifyAll
 * 3个线程：1,2被阻塞，3唤醒他们
 * 先start不代表先执行
 * notify只随机唤醒一个等待线程，notifyAll唤醒所有
 *
 * @Author wangst71
 * @Date 2019/10/27 10:34
 **/
public class WaitNotifyAll implements Runnable{

    private static final Object resourcesA=new Object();

    @Override
    public void run() {
        synchronized (resourcesA){
            System.out.println(Thread.currentThread().getName()+"获取资源A的锁");
            try {
                System.out.println(Thread.currentThread().getName()+"等待重新start");
                resourcesA.wait();
                System.out.println(Thread.currentThread().getName()+"即将结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll r = new WaitNotifyAll();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourcesA) {
                    resourcesA.notifyAll();
//                    resourcesA.notify();
                    System.out.println("线程3notified");
                }
            }
        });
        thread1.start();
        thread2.start();
//        Thread.sleep(200);
        thread3.start();
    }
}
