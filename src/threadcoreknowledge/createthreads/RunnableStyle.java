package threadcoreknowledge.createthreads;

/**
 * @Description TODO
 * @Author wangst71
 * @Date 2019/10/24 21:56
 **/
public class RunnableStyle implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.print("使用runnable接口实现线程！！！");
    }
}
