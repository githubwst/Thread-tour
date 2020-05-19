package deadlock;

/**
 * @ClassName MustDeadLock
 * @Description 演示必然发生的情况
 * @Author wangst71
 * @Date 2019/10/31 9:24
 **/
public class MustDeadLock implements Runnable {
    private static Object o1 = new Object();
    private static Object o2 = new Object();
    int flag = 1;

    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag=1;
        r2.flag=0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();



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
