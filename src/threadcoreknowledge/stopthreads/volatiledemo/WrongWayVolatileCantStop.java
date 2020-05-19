package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName WrongWayVolatile
 * @Description 演示volatile局限：part2
 * 陷入阻塞时，volatile无法停止线程：
 * 生产者生产速度很快，消费者消费速度慢，阻塞队列满，生产者阻塞，等待消费
 * 此时，volatile变量无法通知线程停止，线程处于阻塞态，无法响应
 *
 * @Author wangst71
 * @Date 2019/10/26 15:15
 **/
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage  = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(2000);

        Consumer consumer = new Consumer(storage);
        while(consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(1000);
        }

        System.out.println("消费者不需要更多数据");
        //消费者停止，生产者停止，但是实际情况并不会停止
        producer.canceled = true;

    }
}

class Producer implements Runnable{
    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100的倍数,被放到仓库中");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者结束运行");
        }
    }

}

class Consumer{
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums(){
        if(Math.random()>0.95){
            return false;
        }
        return true;
    }
}