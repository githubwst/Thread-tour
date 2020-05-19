package JUC.JUCTools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<String> ex = new Exchanger();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()->{
            System.out.println("下课铃声响起...");
            try {
                String girl = ex.exchange("我暗恋你很久了");
                System.out.println("女生说：" + girl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            System.out.println("女生从教室慢慢走出来...");
            try {
                String boy = ex.exchange("我也喜欢你");
                System.out.println("男生说：" + boy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
