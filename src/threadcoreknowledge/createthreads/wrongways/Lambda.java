package threadcoreknowledge.createthreads.wrongways;

/**
 * @ClassName Lambda
 * @Description TODO
 * @Author wangst71
 * @Date 2019/10/25 15:34
 **/
public class Lambda {

    public static void main(String[] args) {
        new Thread(()->{System.out.println(Thread.currentThread().getName());}).start();
    }
}
