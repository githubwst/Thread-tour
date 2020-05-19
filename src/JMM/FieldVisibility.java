package JMM;

import java.lang.reflect.Field;

/**
 * @ClassName FieldVisibility
 * @Description 演示可见性问题
 * 使用volatile关键字，解决可见性问题
 *
 * @Author wangst71
 * @Date 2019/10/30 12:49
 **/
public class FieldVisibility {
    //使用volatile关键字，保证在读取a,b之前，先去flush最新数据，再去读取
    volatile int a = 1, b = 2;

    private void print() {
        System.out.println("b=" + b + "; a=" + a);
    }

    private void change() {
        a = 3;
        //b：刷新之前变量的触发器，其他变量可不必设置为volatile，
        // 只需要保证b可见，则b之前的操作均可见
        b = a;
    }

    public static void main(String[] args) {
        while (true) {

            FieldVisibility test = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.change();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.print();
                }
            }).start();
        }

    }


}
