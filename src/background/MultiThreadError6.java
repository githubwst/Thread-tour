package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MultiThreadError6
 * @Description 展示发布 逸出
 * * * 发布：将对象对外开放
 * * * 逸出：将本不该被发布出去的对象，对外发布，导致泄露、被修改
 * * * 2、提供未完成初始化对象
 * * *  在构造函数中运行线程--构造函数运行结束，该对象已经对外发布，但实际对象并未构造完成
 * @Author wangst71
 * @Date 2019/10/29 20:04
 **/
public class MultiThreadError6 {
    private Map<String, String> states;

    //构造函数运行结束，该对象已经对外发布，但实际对象并未构造完成
    public MultiThreadError6() {
        new Thread(() -> {
            states = new HashMap<>();
            states.put("1", "周一");
            states.put("2", "周二");
            states.put("3", "周三");
            states.put("4", "周四");
            states.put("5", "周五");
        }).start();
    }

    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) {
        MultiThreadError6 multiThreadError6 = new MultiThreadError6();
        Map<String, String> states = multiThreadError6.getStates();
        System.out.println(multiThreadError6.getStates().get("1"));
    }
}
