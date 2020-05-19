package background;

/**
 * @ClassName MultiThreadError3
 * @Description 展示发布 逸出
 * 发布：将对象对外开放
 * 逸出：将本不该被发布出去的对象，对外发布，导致泄露、被修改
 * 2、提供未完成初始化对象
 * 构造函数中未完成初始化，this赋值
 * 隐式逸出---注册监听事件
 * 构造函数中运行线程
 * 构造函数中运行线程
 * @Author wangst71
 * @Date 2019/10/29 16:37
 **/
public class MultiThreadError4 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        //在Point构造过程中，设置主类静态变量，但此时构造含未完成，就已经对外提供了该Point
        new PointMaker().start();
        //  <100ms，则Point构造未完成，得到错误结果
        //  >=100ms,则Point构造完成，得到正确结果
        Thread.sleep(100);
        if (point != null) {
            System.out.println(point);
        }
    }
}


//在Point构造过程中，会设置MultiThreadError4的静态变量为自身；
class Point {
    private final int x, y;

    Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadError4.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " : " + y;
    }
}

//用于进行Point初始化构造
class PointMaker extends Thread {
    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}