package background;

/**
 * @ClassName Test
 * @Description 演示integer缓存对象的情况
 * @Author wangst71
 * @Date 2019/10/29 20:44
 **/
public class IntegerCacheTest {

    public static void main(String[] args) {
        Integer a=100,b=100,c=150,d=150;
        System.out.println(a==b);
        System.out.println(c==d);
    }
}
