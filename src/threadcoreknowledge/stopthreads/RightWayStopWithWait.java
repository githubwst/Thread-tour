package threadcoreknowledge.stopthreads;

import java.io.IOException;

public class RightWayStopWithWait {

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        T1 t1 = new T1();
        T2 t2 = new T2(t1);
        t1.start();
        t2.start();

    }


    static Integer i = 0;

    static class T1 extends Thread {

        public void run() {

            synchronized (i) {
                System.out.println("T1-在syn里");
                try {
                    i.wait();//验证表明：wait时间到或被中断唤醒，不会继续执行或者跳到catch里
                    // (因为根本得不到执行，根本没法抛出InterruptedException，所以即使catch块放在syschronized外也一样 )，
                    //而是还需要等待获得锁。
                    //如果wait时间到或被中断唤醒，而T2还在syn里，那么T1还是会等待。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束wait-T1");
                System.out.println("T1-在syn里");
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1-在syn里");
            }
            System.out.println("离开syn-T1");

        }
    }


    static class T2 extends Thread {

        Thread t1;
        public T2(Thread t1){
            this.t1 = t1;
        }

        public void run() {
//            synchronized (i) {
                System.out.println("T2-在syn里");
                try {
                    Thread.sleep(1000);
                    t1.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2-还在syn里");
//            }
            System.out.println("T2-离开syn");

        }
    }
}  