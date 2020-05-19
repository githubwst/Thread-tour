package background;

/**
 * @ClassName MultiThreadError5
 * @Description 展示发布 逸出
 * * 发布：将对象对外开放
 * * 逸出：将本不该被发布出去的对象，对外发布，导致泄露、被修改
 * * 2、提供未完成初始化对象
 * *  隐式逸出---注册监听事件---观察者模式
 * @Author wangst71
 * @Date 2019/10/29 17:07
 **/
public class MultiThreadError5 {

    int count;
    public MultiThreadError5(MySource source){
        source.registerListener(new Observer() {
            @Override
            public void update(Event e) {
                System.out.println("我得到的数字是"+count);
            }
        });

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        count=100;
    }

    public static void main(String[] args) {
        MySource source =new MySource();
        //被监听的subject类尚未构造完成，监听器已经可以运行，导致监听到错误结果
        //模拟事件到来，此时构造函数可能尚未运行结束，监听器得到错误结果
        // 只有延迟时间>10ms，构造函数运行结束，可得到正确结果；
        new Thread(()->{
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            source.eventCome(new Event() {
            });
        }).start();
        //在MultiThreadError5构造过程中，为MySource设置监听器以及监听行为，同时设置监听的变量；
        MultiThreadError5 multiThreadError5 = new MultiThreadError5(source);
    }


    static class MySource {
        private Observer listener;
        void registerListener(Observer listener){
            this.listener = listener;
        }
        void eventCome(Event e){
            if(listener!=null){
                listener.update(e);
            }else{
                System.out.println("未初始化完毕");
            }
        }
    }

    interface Observer {
        void update(Event e);
    }
    interface Event{
    }
}
