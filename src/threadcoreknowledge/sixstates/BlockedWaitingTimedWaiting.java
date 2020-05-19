package threadcoreknowledge.sixstates;

/**
 * @ClassName BlockedWaitingTimedWaiting
 * @Description 展示Blocked、Waiting、TimedWaiting三种线程状态
 * @Author wangst71
 * @Date 2019/10/26 23:45
 **/
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting r = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(r);
        thread1.start();
        Thread thread2 = new Thread(r);
        thread2.start();
        Thread.sleep(500);
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        Thread.sleep(1300);
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
