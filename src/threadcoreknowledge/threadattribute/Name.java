package threadcoreknowledge.threadattribute;

/**
 * @ClassName Name
 * @Description 线程名：供开发者区分使用
 * nativeName一旦启动无法修改，但是java名称可以随时修改
 * @Author wangst71
 * @Date 2019/10/27 20:59
 **/
public class Name {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println(thread.getName());
    }

}
