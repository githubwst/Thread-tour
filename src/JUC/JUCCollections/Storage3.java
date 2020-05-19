package JUC.JUCCollections;

import java.util.concurrent.LinkedBlockingQueue;

public class Storage3 implements StorageIterface {
    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>();

    // 生产num个产品
    @Override
    public void produce(int num) {

        if (list.size() == MAX_SIZE) {
            System.out.println("【现仓储量为】:" + list.size() + "\t暂时不能执行生产任务！");
        }

        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                list.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【现仓储量为】:" + list.size());
        }
    }


    // 消费num个产品
    @Override
    public void consume(int num) {
        // 同步代码段
        if (list.size() == 0) {
            System.out.println("【库存量】：0\t暂时不能执行消费任务！");
        }
        for (int i = 0; i < num; i++) {
            try {
                list.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【现仓储量为】:" + list.size());
        }
    }

    // get/set方法
    public LinkedBlockingQueue<Object> getList() {
        return list;
    }

    public void setList(LinkedBlockingQueue<Object> list) {
        this.list = list;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }
}
