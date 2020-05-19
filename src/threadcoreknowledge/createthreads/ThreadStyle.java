package threadcoreknowledge.createthreads;

/**
 * @ClassName ThreadStyle
 * @Description 使用Thread方式实现线程
 * @Author wangst71
 * @Date 2019/10/24 22:03
 **/
public class ThreadStyle extends Thread{
    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.print("使用Thread方式实现线程!!!");
    }
}
