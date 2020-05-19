package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName JoinInterrupt
 * @Description 演示在等待新加入线程执行完毕期间，当前线程被中断后，不再等待；
 * 需要手动去中断加入的线程，之后两者并行执行
 *
 * @Author wangst71
 * @Date 2019/10/27 18:48
 **/
public class JoinInterrupt {

    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    main.interrupt();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+"被中断");
                }
                System.out.println("子线程执行结束");
            }
        });
        thread1.start();
        System.out.println("等待子线程执行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断了");
            thread1.interrupt();
            e.printStackTrace();
        }
        System.out.println("预计子线程执行结束");
    }

}
