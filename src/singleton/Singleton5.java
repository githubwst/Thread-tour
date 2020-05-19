package singleton;

/**
 * @ClassName Singleton2
 * @Description 懒汉式（线程安全） 不推荐
 * @Author wangst71
 * @Date 2019/10/30 20:34
 **/
public class Singleton5 {
    private static Singleton5 INSTANCE;

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        if (INSTANCE == null) {
            //无法保证线程安全
            synchronized (Singleton1.class) {
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }
}
