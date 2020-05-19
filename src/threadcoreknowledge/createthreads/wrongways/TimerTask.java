package threadcoreknowledge.createthreads.wrongways;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @ClassName TimerTask
 * @Description 定时器创建线程
 * @Author wangst71
 * @Date 2019/10/25 15:22
 **/

/**
 * 多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。
 *
 *     //org.apache.commons.lang3.concurrent.BasicThreadFactory
 *     ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
 *         new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
 *     executorService.scheduleAtFixedRate(new Runnable() {
 *         @Override
 *         public void run() {
 *             //do something
 *         }
 *     },initialDelay,period, TimeUnit.HOURS);
 */

public class TimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println(df.format(new Date()));
            }
        },1000,1000);

    }

}
