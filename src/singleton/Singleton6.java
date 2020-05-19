package singleton;

/**
 * @ClassName Singleton6
 * @Description 双重检查：推荐面试使用
 * 1、线程安全
 * 2、单层check，不安全，两次创建实例
 * 3、相比sync方法效率更高
 * 备注：需要volatile修饰，防止重排序，保证可见性
 *
 * @Author wangst71
 * @Date 2019/10/30 20:59
 **/
public class Singleton6 {
    private volatile static Singleton6 INSTANCE;

    private Singleton6() {
    }

    public static Singleton6 getInstance() {
        if (INSTANCE == null) {
            //无法保证线程安全
            synchronized (Singleton1.class) {
                if (INSTANCE == null) {
                    //可能发生重排序，导致对象过早发布，发生空指针异常
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }
}
