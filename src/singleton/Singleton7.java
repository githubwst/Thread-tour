package singleton;

/**
 * @ClassName Singleton6
 * @Description 静态内部类：可用 线程安全
 * @Author wangst71
 * @Date 2019/10/30 20:59
 **/
public class Singleton7 {

    private Singleton7() {
    }

    //静态内部类，由类加载机制决定，在使用的时候才会实例化
    //多线程访问也只会建立一个静态变量
    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
