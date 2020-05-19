package background;

/**
 * @ClassName MultiThreadError
 * @Description 死锁的第二种实现方式
 * @Author wangst71
 * @Date 2019/10/29 16:14
 **/
public class MultiThreadError2 implements Runnable {
    int flag = 1;
    static final Object resourcesA = new Object();
    static final Object resourcesB = new Object();


    public static void main(String[] args) throws InterruptedException {
        MultiThreadError2 r1 = new MultiThreadError2();
        MultiThreadError2 r2 = new MultiThreadError2();
        r1.flag = 1;
        r2.flag = 0;

        new Thread(r1).start();
        new Thread(r2).start();
    }


    @Override
    public void run() {
        if (flag == 1) {
            synchronized (resourcesA) {
                try {
                    System.out.println("111");
                    Thread.sleep(500);
                    System.out.println("444");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourcesB) {
                    System.out.println("111");
                }
            }

        } else {
            synchronized (resourcesB) {
                try {
                    System.out.println("222");
                    Thread.sleep(500);
                    System.out.println("333");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourcesA) {
                    System.out.println("222");
                }
            }
        }
    }
}
