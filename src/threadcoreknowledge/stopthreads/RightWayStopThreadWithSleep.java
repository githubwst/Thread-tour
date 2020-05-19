package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadWithoutSleep
 * @Description 在sleep过程中被中断，会抛出中断异常并清除中断标记，被循环外层catch后，循环终止
 * @Author wangst71
 * @Date 2019/10/25 20:59
 **/
public class RightWayStopThreadWithSleep{
    public static void main(String[] args) throws InterruptedException {
       Runnable runnable = ()-> {
           int num = 0;
           try {
               while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                   if (num % 100 == 0) System.out.println(num + "是100的倍数");
                   num++;
               }
               Thread.sleep(1000);
           }catch (InterruptedException e){
               e.printStackTrace();
           }
       };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

}
