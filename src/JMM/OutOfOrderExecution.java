package JMM;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName OutOfOrderExecution
 * @Description 演示重排序现象
 * “直到达到某个条件才停止” ， 测试小概率事件
 * @Author wangst71
 * @Date 2019/10/30 11:43
 **/
public class OutOfOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;


    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        do{
            i++;
            x=0;
            y=0;
            a=0;
            b=0;
            //程序栅栏
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        latch.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    a = 1;
                    x = b;
                }
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        latch.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    b = 1;
                    y = a;
                }
            });

            one.start();
            two.start();
            //解除栅栏
//            latch.countDown();
            one.join();
            two.join();
            StringBuilder sb = new StringBuilder("第");
            sb.append(i).append("次，（").append(x).append(",").append(y).append(")");
            System.out.println(sb.toString());

        }while(x != 0 || y != 0);
    }


}
