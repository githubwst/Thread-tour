package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MultiThreadError3
 * @Description 多线程的第三种问题：发布 逸出
 * 发布：将对象对外开放
 * 逸出：将本不该被发布出去的对象，对外发布，导致泄露、被修改
 * 1、方法返回private对象 --- 返回副本
 * 2、提供未完成初始化对象 --- 工厂模式
 * 构造函数中未完成初始化，this赋值
 * 隐式逸出---注册监听事件
 * 构造函数中运行线程
 * 构造函数中运行线程
 * @Author wangst71
 * @Date 2019/10/29 16:37
 **/
public class MultiThreadError3 {
    private Map<String, String> states;

    public MultiThreadError3() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
        states.put("5", "周五");
    }

    public Map<String, String> getStates() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadError3 multiThreadError3 = new MultiThreadError3();
        Map<String, String> states = multiThreadError3.getStates();
        states.put("1", "周日");
        System.out.println(multiThreadError3.getStates().get("1"));
        states.remove("1");
        System.out.println(multiThreadError3.getStates().get("1"));
    }
}
