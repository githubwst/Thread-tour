package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName Wait
 * @Description 展示wait、notify的基本用法 ：线程1获得锁然后wait休眠，线程2notify唤醒线程1
 * 研究代码执行顺序
 * 证明wait释放锁
 * @Author wangst71
 * @Date 2019/10/27 10:15
 **/
public class Wait {
    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName()+"开始执行");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                object.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"调用了notify");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 =new Thread1();
        Thread2 thread2 =new Thread2();
        thread1.start();
        Thread.sleep(200);
        thread2.start();

    }
}
