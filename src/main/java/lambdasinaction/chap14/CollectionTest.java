package lambdasinaction.chap14;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangyingjie1 on 2017/8/21.
 */
public class CollectionTest {

    @Test
    public void testToMap() {
        Stream<String> strStream = Stream.of("java", "c++", "c", "python");
        Map<String, Integer> map1;
        map1 = strStream.collect(Collectors.toMap(Function.identity(), (x) -> 0));
        //Function.identity()-->返回strStream中的元素，toMap方法的我两个参数都是Function接口型的，所以第二个参数即使只放0，也不能直接写作0，可以使用如上的方式进行操作

        for (String key : map1.keySet()) {
            System.out.println("key:" + key + "->" + "value:" + map1.get(key));
        }
        //结果
        /*
        key:python->value:0
        key:c++->value:0
        key:c->value:0
        key:java->value:0
         */
    }

    @Test
    public Map<String, Integer> testToMap2() {
        Stream<String> strStream = Stream.of("java", "c++", "c", "python");
        Map<String, Integer> map1;
        map1 = strStream.collect(Collectors.toMap(Function.identity(), (x) -> 0));
        if (false) {
            return null;
        }
        return map1;
    }

    @Test
    public void testCollect() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
                collect(() -> new ArrayList<Integer>(),
                        (list, item) -> list.add(item),
                        (list1, list2) -> list1.addAll(list2));
        System.out.println(numsWithoutNull);
        nums.stream().filter(num -> num != null).collect(Collectors.toList());

        //限制条件，key 不能重复
        Map<Integer, Integer> collectMap = nums.stream()
                .filter(num -> num != null)
                .collect(Collectors.toMap(
                        Function.identity(),//Function.identity()-->返回stream中的元素
                        (x) -> x.intValue() + 1000,
                        (existingValue, newValue) -> existingValue,//key 重复处理
                        TreeMap::new//指定map的返回类型
                ));
        collectMap.forEach((k, v) -> System.out.println(k + "======" + v));

        //Map<Integer, Movie> mappedMovies = movies.stream().collect(Collectors.toMap(Movie::getRank, (p) -> p));


    }


}
