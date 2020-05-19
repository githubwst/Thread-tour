package threadcoreknowledge.createthreads;

/**
 * @ClassName BothRunnableThread
 * @Description TODO
 * @Author wangst71
 * @Date 2019/10/24 22:09
 **/
public class BothRunnableThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("我来自Runnable");
            }
        }){
            @Override
            public void run() {
                System.out.print("我来自Thread");
            }
        }.start();
    }


}
