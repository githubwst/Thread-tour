package threadcoreknowledge.UncaughtException;

/**
 * @ClassName ExceptionInChildThread
 * @Description 单线程，抛出异常  处理  有异常堆栈
 * 多线程， 子线程发生异常，有所不同
 *
 * @Author wangst71
 * @Date 2019/10/28 9:01
 **/
public class ExceptionInChildThread implements Runnable{

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for(int i=0;i<1000;i++){
            System.out.println(i);
        }

    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
