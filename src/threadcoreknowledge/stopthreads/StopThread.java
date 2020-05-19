package threadcoreknowledge.stopthreads;

/**
 * @ClassName StopThread
 * @Description 使用Stop停止线程：线程运行一半停止，无法完成一个基本单位的操作，造成脏数据
 * @Author wangst71
 * @Date 2019/10/26 14:26
 **/
public class StopThread implements Runnable{


    @Override
    public void run() {
        //模拟指挥军队，5个连队，每连队10人，叫号领取装备
        for(int i=0;i<5;i++){
            System.out.println("连队"+i+"开始领取装备");
            for(int j=0;j<10;j++){
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队"+i+"领取完毕");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
