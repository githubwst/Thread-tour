package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName WrongWayVolatileFixed
 * @Description TODO
 * @Author wangst71
 * @Date 2019/10/26 15:52
 **/
public class WrongWayVolatileFixed {


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
                while (num <= 100000 ) {
                    if (num % 100 == 0) {
                        //阻塞队列也可以相应中断，并抛出异常
                        storage.put(num);
                        System.out.println(num + "是100的倍数,被放到仓库中");
                    }
                    num++;
                    //sleep、wait过程中可响应中断，抛出异常并清除中断状态标记
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("生产者结束运行0");
                System.out.println(Thread.currentThread().isInterrupted());
                e.printStackTrace();
                //在return之前会先检查finally中是否有return
                return;
            }finally {
                System.out.println("生产者结束运行1");
                return ;
             }
        }

    }

    class Consumer{
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums(){
            if(Math.random()>0.75){
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage  = new ArrayBlockingQueue(10);

        WrongWayVolatileFixed body = new WrongWayVolatileFixed();

        Producer producer = body.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(2000);

        Consumer consumer = body.new Consumer(storage);
        while(consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(1000);
        }

        System.out.println("消费者不需要更多数据");
        //消费者停止，生产者停止
        producerThread.interrupt();
        Thread.interrupted();//自动清除线程中断状态
        producerThread.isInterrupted();//不会自动清除中断状态

    }




}

