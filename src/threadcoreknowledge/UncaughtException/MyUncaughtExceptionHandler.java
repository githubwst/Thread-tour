package threadcoreknowledge.UncaughtException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName MyUncaughtExceptionHandler
 * @Description 自己的UnCaughtExceptionHandler
 *
 * @Author wangst71
 * @Date 2019/10/28 9:32
 **/
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING,"线程异常终止了" +t.getName(),e);
        System.out.println(name+"捕获了异常"+t.getName()+"异常"+e);
    }
}
