package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadProd
 * @Description 在无法传递异常或者不想传递时：
 * 在catch中调用Thread.currentThread().interrupt()来恢复中断
 * 以便后续执行仍能检测到中断
 * @Author wangst71
 * @Date 2019/10/26 10:35
 **/
public class RightWayStopThreadProd2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("中断，程序运行结束");
                break;
            }
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

    }
}
