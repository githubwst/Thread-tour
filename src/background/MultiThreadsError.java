package background;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MultiThreadsError
 * @Description 第一种线程安全问题：a++计数不准确（减少），找出具体出错地方
 *
 *
 * @Author wangst71
 * @Date 2019/10/28 21:27
 **/
public class MultiThreadsError implements Runnable{
    private int index;
    static MultiThreadsError instance = new MultiThreadsError();
    final boolean[] marked = new boolean[1000000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongIndex = new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(instance.index);
        System.out.println(realIndex+":"+wrongIndex);
    }

    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 100000; i++) {
            try {
                cyclicBarrier1.await();
                cyclicBarrier1.reset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier2.await();
                cyclicBarrier2.reset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance){
                if(marked[index]&&marked[index-1]){
                    System.out.println(index+"发生了错误");
                    wrongIndex.incrementAndGet();
                }
                marked[index]=true;
            }
        }

    }


}
