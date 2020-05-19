package threadcoreknowledge.createthreads.wrongways;

/**
 * @ClassName AnonymousInnerClassDemo
 * @Description 匿名内部类实现新建线程
 * @Author wangst71
 * @Date 2019/10/25 15:30
 **/
public class AnonymousInnerClassDemo {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

    }
}
