package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName CurrentThread
 * @Description 展示：当前线程信息
 * @Author wangst71
 * @Date 2019/10/27 20:49
 **/
public class CurrentThread implements Runnable {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
       new Thread(new CurrentThread()).start();
       new Thread(new CurrentThread()).start();
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
