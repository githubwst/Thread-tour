package threadcoreknowledge.sixstates;

/**
 * @ClassName NewRunnableTerminated
 * @Description 展示线程的New、Runnable、Terminated状态
 * 即使正在运行，也是Runnable，而不是Running
 * @Author wangst71
 * @Date 2019/10/26 23:30
 **/
public class NewRunnableTerminated implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            thread.join();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getState());

    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            System.out.println(i);
        }
    }
}
