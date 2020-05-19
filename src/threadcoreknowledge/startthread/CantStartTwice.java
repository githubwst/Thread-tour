package threadcoreknowledge.startthread;

/**
 * @ClassName CantStartTwice
 * @Description TODO
 * @Author wangst71
 * @Date 2019/10/25 19:54
 **/
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
