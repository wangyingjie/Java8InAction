package lambdasinaction.chap1;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangyingjie1 on 2017/8/27.
 */
public class HashMapTest {

    @Test
    public void testLinkedHashMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        map.forEach((k, v) -> System.out.print(k + "==" + v + " "));

        //HashMap.DEFAULT_LOAD_FACTOR;
    }

    @Test
    public void testLinkedHashMapOrder() {
        //按元素的最后访问顺序排序
        Map<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");

        String s2 = map.get("2");
        String s3 = map.get("3");

        map.forEach((k, v) -> System.out.print(k + "==" + v + " "));
    }
}
