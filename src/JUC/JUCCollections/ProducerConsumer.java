package JUC.JUCCollections;

/**
 * 生产者类Producer继承线程类Thread
 * <p>
 * Email:530025983@qq.com
 *
 * @author MONKEY.D.MENG 2011-03-15
 */
class Producer extends Thread {
    // 每次生产的产品数量
    private int num;

    // 所在放置的仓库
    private StorageIterface storage;

    // 构造函数，设置仓库
    public Producer(StorageIterface storage) {
        this.storage = storage;
    }

    // 线程run函数
    @Override
    public void run() {
        produce(num);
    }

    // 调用仓库Storage的生产函数
    public void produce(int num) {
        storage.produce(num);
    }

    // get/set方法
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    /*public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }*/
}

/**
 * 消费者类Consumer继承线程类Thread
 * <p>
 * Email:530025983@qq.com
 *
 * @author MONKEY.D.MENG 2011-03-15
 */
class Consumer extends Thread {
    // 每次消费的产品数量
    private int num;

    // 所在放置的仓库
    private StorageIterface storage;

    // 构造函数，设置仓库
    public Consumer(StorageIterface storage) {
        this.storage = storage;
    }

    // 线程run函数
    @Override
    public void run() {
        consume(num);
    }

    // 调用仓库Storage的生产函数
    public void consume(int num) {
        storage.consume(num);
    }

    // get/set方法
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public StorageIterface getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        // 仓库对象
        Storage3 storage = new Storage3();

        // 生产者对象
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(80);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
        c1.start();
//        c2.start();
//        c3.start();
        p1.start();
        p2.start();
        p3.start();
//        p4.start();
//        p5.start();
//        p6.start();
        Thread.sleep(1000);
        p7.start();
    }
}

