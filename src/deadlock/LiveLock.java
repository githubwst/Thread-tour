package deadlock;

import java.util.Random;

/**
 * @ClassName LiveLock
 * @Description 演示活锁   线程互相谦让，导致无法获得进展
 * 对重试次数进行限制：
 *   1、引入随机性，参考以太网：指数退避算法
 *   2、设置重试上限
 *
 * 消息队列活锁：
 *      将短时间重试次数达到上限的消息：
 *      1、置于队尾
 *      2、移除队列，写入数据库
 * @Author wangst71
 * @Date 2019/10/31 18:57
 **/
public class LiveLock {
    static class Spoon {
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use() {
            System.out.printf("%s has eaten！", owner.name);
        }
    }

    static class Diner {
        String name;
        boolean isHungry = true;

        public Diner(String name) {
            this.name = name;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            int time =0;
            while (isHungry) {
                if(spoon.owner != this){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random = new Random();
                if (spouse.isHungry && random.nextInt(10)<=10-time*0.1){
                    time++;
                    System.out.println(name+": 亲爱的"+spouse.name+"你先吃吧");
                    spoon.owner = spouse;
                    continue;
                }
                spoon.use();
                isHungry =false;
                System.out.println(name+"我吃完了");
                spoon.owner = spouse;
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");
        Spoon spoon = new Spoon(husband);

        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eatWith(spoon,wife);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(spoon,husband);
            }
        }).start();

    }
}
