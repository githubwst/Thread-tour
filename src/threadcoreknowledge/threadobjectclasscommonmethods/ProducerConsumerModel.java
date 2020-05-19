package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * @ClassName ProducerConsumerModel
 * @Description 使用wait/notify实现生产者消费者设计模式
 * @Author wangst71
 * @Date 2019/10/27 11:50
 **/
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Thread thread1 = new Thread(new Producer(storage));
        Thread thread2 = new Thread(new Consumer(storage));
        thread1.start();
        thread2.start();
    }
}
class Producer implements Runnable {

    private EventStorage storage;

    Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

class Consumer implements Runnable {

    private EventStorage storage;

    Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;

    EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void put() {
        while (storage.size() >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("仓库中有了" + storage.size() + "个产品");
        notify();
    }

    public synchronized void take() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了" + storage.pollFirst() + ",现在还剩下" + storage.size());
        notify();
    }
}
