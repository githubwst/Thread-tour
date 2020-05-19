package singleton;

/**
 * @ClassName Singleton2
 * @Description 懒汉式（线程不安全） 不可用
 * @Author wangst71
 * @Date 2019/10/30 20:34
 **/
public class Singleton3 {
    private static Singleton3 INSTANCE;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Singleton3();
        return INSTANCE;
    }
}
