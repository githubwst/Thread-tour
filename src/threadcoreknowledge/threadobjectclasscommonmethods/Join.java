package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName Join
 * @Description 演示join：新的线程加入到当前线程，当前线程等待加入的线程执行结束后，继续执行
 * 注意语句输出顺序变化
 *
 * @Author wangst71
 * @Date 2019/10/27 18:24
 **/
public class Join {
    public static void main(String[] args) throws InterruptedException {
       Thread thread1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"已经执行完毕");
            }
        });

        Thread thread2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"已经执行完毕");
            }
        });

        thread1.start();
        thread2.start();
        System.out.println("开始等待子线程执行");
        thread1.join();
        thread2.join();
        System.out.println("子线程执行结束");
    }

}
