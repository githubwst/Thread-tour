package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName WaitNotifyPrintOddEvenSyn
 * @Description 两个线程交替打印1~100
 * @Author wangst71
 * @Date 2019/10/27 13:28
 **/
public class WaitNotifyPrintOddEvenSyn {
    //1.新建两个线程
    //2.A打印奇数，B打印偶数
    //3.A在打印奇数后通知B打印下一个数
    //备注：使用synchronized来通信，使用位运算处理奇数
    //优化：使用wait/notify进行协调同步
    private static int count=1;
    private static final Object lock = new Object();
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    System.out.println("A:"+count);
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName()
                                    + ":" + count++);
                        }
                    }
                }
            }
        }, "偶数线程");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    System.out.println("B:"+count);
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName()
                                    + ":" + count++);
                        }
                    }
                }
            }
        }, "奇数线程");

        thread1.start();
        thread2.start();

    }

}
