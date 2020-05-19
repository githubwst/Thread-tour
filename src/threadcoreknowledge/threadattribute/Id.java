package threadcoreknowledge.threadattribute;

/**
 * @ClassName Id
 * @Description 线程的ID属性：供系统标识线程
 * 从1开始，但JVM运行开始后，早已不是2
 *
 * @Author wangst71
 * @Date 2019/10/27 20:53
 **/
public class Id {
    public static void main(String[] args) {
        Thread thread =new Thread();
        System.out.println("主线程ID："+Thread.currentThread().getId());
        System.out.println("子线程ID："+thread.getId());
    }
}
