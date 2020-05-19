package deadlock;

/**
 * @ClassName TransferMoney
 * @Description 转账时遇到死锁
 * @Author wangst71
 * @Date 2019/10/31 9:57
 **/
public class TransferMoney implements Runnable {
    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
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
        synchronized (from) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账" + amount + "元");
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
