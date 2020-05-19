package deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @ClassName ThreadMXBeanDetection
 * @Description 演示：使用ThreadMXBean检测死锁，进行处理
 * @Author wangst71
 * @Date 2019/10/31 12:26
 **/
public class ThreadMXBeanDetection implements Runnable{
    private static Object o1 = new Object();
    private static Object o2 = new Object();
    int flag = 1;

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBeanDetection r1 = new ThreadMXBeanDetection();
        ThreadMXBeanDetection r2 = new ThreadMXBeanDetection();
        r1.flag=1;
        r2.flag=0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //通过管理器工厂，得到ThreadMXBean实例对象
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //获取死锁线程的id数组
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        //如果存在死锁线程组，进行处理（循环遍历，打印信息，保存日志等）
        if(deadlockedThreads !=null &&deadlockedThreads.length>0){
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                System.out.println("发现死锁："+threadInfo.getThreadName());
            }
        }


    }

    @Override
    public void run() {
        System.out.println("flag: "+flag);
        if(flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("r1获取到o2");
                }
            }
        }else {
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("r2获取到o1");
                }
            }
        }
    }
}
