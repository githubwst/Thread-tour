package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @ClassName JoinPrinciple
 * @Description 讲解join原理：分析join的代替写法
 *
 * @Author wangst71
 * @Date 2019/10/27 20:02
 **/
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("thread1 finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
//        thread1.join();  //先join无法起到同步的效果
        thread1.start();
        System.out.println("等待子线程执行完毕");
//        thread1.join();
        System.out.println("子线程状态："+thread1.getState());
//        synchronized(thread1){
////            System.out.println("子线程状态："+thread1.getState());
////            thread1.wait();
////        }
        System.out.println("子线程运行结束");
    }
}
