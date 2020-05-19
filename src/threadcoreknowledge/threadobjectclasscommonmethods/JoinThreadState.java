package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName JoinThreadState
 * @Description 验证在被join时候，线程的状态：Waiting
 * @Author wangst71
 * @Date 2019/10/27 19:08
 **/
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("主线程状态:" + main.getState());
                    System.out.println("子线程0运行结束 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread1.join();

        System.out.println("等待子线程执行完毕");
        System.out.println("子线程运行结束");
    }
}
