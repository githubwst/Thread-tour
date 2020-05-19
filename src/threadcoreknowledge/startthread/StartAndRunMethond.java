package threadcoreknowledge.startthread;

/**
 * @ClassName StartAndRunThread
 * @Description TODO
 * @Author wangst71
 * @Date 2019/10/25 17:08
 **/
public class StartAndRunMethond {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();

        new Thread(runnable).start();
    }
}
