package singleton;

/**
 * @ClassName Singleton1
 * @Description 单例模式：饿汉式（静态常量） 可用
 * @Author wangst71
 * @Date 2019/10/30 20:32
 **/
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}