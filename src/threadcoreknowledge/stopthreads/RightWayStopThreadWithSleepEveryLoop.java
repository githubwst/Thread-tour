package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadWithoutSleep
 * @Description 每次循环都有sleep，在中断抛出异常并catch住后，循环依然会继续，中断标记自动被清除
 * @Author wangst71
 * @Date 2019/10/25 20:59
 **/
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
       Runnable runnable = ()-> {
           int num = 0;

               while (num <= 10000 && !Thread.currentThread().isInterrupted() ) {
                   if (num % 100 == 0) System.out.println(num + "是100的倍数");
                   num++;
                   try {
                       Thread.sleep(10);
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                       e.printStackTrace();
                   }
               }
       };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();
    }

}
