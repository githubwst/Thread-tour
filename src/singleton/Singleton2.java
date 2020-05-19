package singleton;

/**
 * @ClassName Singleton2
 * @Description 饿汉式（静态代码块） 可用
 * @Author wangst71
 * @Date 2019/10/30 20:34
 **/
public class Singleton2 {
    private final static Singleton2 INSTANCE ;

    static {
        INSTANCE= new Singleton2();
    }

    private Singleton2(){}

    public static Singleton2 getInstance(){
        return INSTANCE;
    }
}
