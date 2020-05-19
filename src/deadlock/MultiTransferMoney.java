package deadlock;

import java.util.Random;

/**
 * @ClassName TransferMoney
 * @Description 模拟多人转账死锁
 * @Author wangst71
 * @Date 2019/10/31 9:57
 **/
public class MultiTransferMoney {
    public static final int NUM_ACCOUNTS = 30;
    public static final int NUM_MONEY = 1000;
    public static final int NUM_ITERATIONS = 1000000;
    public static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random r = new Random();
        TransferMoney2.Account[] accounts = new TransferMoney2.Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney2.Account(NUM_MONEY);
        }
        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    int fromAccount = r.nextInt(NUM_ACCOUNTS);
                    int toAccount = r.nextInt(NUM_ACCOUNTS);
                    int amount = r.nextInt(NUM_MONEY);
                    TransferMoney2.transferMoney(accounts[fromAccount], accounts[toAccount], amount);

                }
            }
        }
        for (int j = 0; j < NUM_THREADS; j++) {
            new TransferThread().start();
        }


    }


}
