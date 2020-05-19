package singleton;

/**
 * @ClassName Singleton2
 * @Description 懒汉式（线程安全） 不推荐
 * @Author wangst71
 * @Date 2019/10/30 20:34
 **/
public class Singleton4 {
    private static Singleton4 INSTANCE;

    private Singleton4() {
    }

    //加入sync关键字，保证线程安全，但是效率变低
    public synchronized static Singleton4 getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Singleton4();
        return INSTANCE;
    }
}
