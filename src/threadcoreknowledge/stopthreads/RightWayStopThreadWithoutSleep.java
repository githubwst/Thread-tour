package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadWithoutSleep
 * @Description TODO
 * @Author wangst71
 * @Date 2019/10/25 20:59
 **/
public class RightWayStopThreadWithoutSleep implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2 ) {
            if (num % 10000 == 0) System.out.println(num + "是10000的倍数");
            num++;
        }
        System.out.println("任务执行结束");
    }
}
