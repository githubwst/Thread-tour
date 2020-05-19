package deadlock;

/**
 * @ClassName TransferMoney
 * @Description 转账时遇到死锁：
 * //使用hashCode作为识别码，按序获取锁，避免死锁；
 *         //hashcode可能冲突，实际开发可以采用数据库主键等唯一标识；
 * @Author wangst71
 * @Date 2019/10/31 9:57
 **/
public class TransferMoney2 implements Runnable {
    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        TransferMoney2 r1 = new TransferMoney2();
        TransferMoney2 r2 = new TransferMoney2();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a的余额：" + a.balance);
        System.out.println("b的余额：" + b.balance);
    }

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        } else {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {
        class Helper {
            public void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账" + amount + "元");
            }
        }

        //使用hashCode作为识别码，按序获取锁，避免死锁；
        //hashcode可能冲突，实际开发可以采用数据库主键等唯一标识；
        int fromHashCode = System.identityHashCode(from);
        int toHashCode = System.identityHashCode(to);
        if (fromHashCode > toHashCode) {
            synchronized (from) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHashCode < toHashCode) {
            synchronized (to) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            //当hashcode发生冲突时，进行“加时赛”，争夺新锁
            synchronized (lock) {
                synchronized (to) {
                    synchronized (from) {
                        new Helper().transfer();
                    }
                }
            }
        }

    }

    static class Account {
        public int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }
}
