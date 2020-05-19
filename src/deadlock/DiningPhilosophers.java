package deadlock;

/**
 * @ClassName DiningPhilosophers
 * @Description 演示：哲学家就餐问题导致死锁
 * 通过改变一个哲学家的就餐方式，可以避免死锁
 * 通过检测与恢复，领导监察
 * 通过发放饭票，避免死锁（5个人，4张饭票）
 *
 * @Author wangst71
 * @Date 2019/10/31 14:06
 **/
public class DiningPhilosophers {

    public static class Philosopher implements Runnable {
        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        //拿到左边筷子
                        doAction("Picked up left chopstick");
                        synchronized (rightChopstick) {
                            //拿到了右边的筷子--开始进餐
                            doAction("Picked up right chopstick - eating");
                            //进餐结束，放下右筷子
                            doAction("Put down right chopstick");
                        }
                        //放下左筷子
                        doAction("Put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " ," + action);
            Thread.sleep((long) Math.random() * 10);
        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsticks = new Object[philosophers.length];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i+1)%chopsticks.length];
            //改变一个哲学家就餐的方式，可避免死锁
            if(i == philosophers.length-1){
                philosophers[i] = new Philosopher(rightChopstick,leftChopstick);
            }else {
                philosophers[i] = new Philosopher(leftChopstick,rightChopstick);
            }
            new Thread(philosophers[i],"哲学家-"+(i+1)).start();
        }
    }

}
