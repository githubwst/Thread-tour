package singleton;

/**
 * @ClassName Singleton6
 * @Description 枚举：实际生产使用，强烈推荐
 * @Author wangst71
 * @Date 2019/10/30 20:59
 **/
public enum  Singleton8 {
    //演示带有参数的单例模式
    INSTANCE("1","qw");

    private Singleton8(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private String value;


    /* 一般方法 */
    public void whatever(){

    }


    public static void main(String[] args) {
        //演示枚举形式的单例模式的使用方法
        Singleton8.INSTANCE.whatever();
        Singleton8 s8 = Singleton8.INSTANCE;

    }
}
