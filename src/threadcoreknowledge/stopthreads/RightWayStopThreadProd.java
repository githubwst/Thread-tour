package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadProd
 * @Description catch了interruptedException之后，优先选择：在方法签名中抛出异常
 * 那么run（）就会强制try/catch
 * @Author wangst71
 * @Date 2019/10/26 10:35
 **/
public class RightWayStopThreadProd implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {

        while (true) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("保存日志");
                e.printStackTrace();
                break;
            }
        }


    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }
}
