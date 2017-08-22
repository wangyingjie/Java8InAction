package lambdasinaction.chap3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by wangyingjie1 on 2017/2/27.
 */
public class ReduceTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        Integer reduce = list.stream().reduce(0, (a, b) -> a + b);

        //使用方法引用、简化代码
        Integer reduce2 = list.stream().reduce(0, Integer::sum);

        //求列表中的最大值
        Optional<Integer> max1 = list.stream().reduce((x, y) -> x > y ? x : y);

        //求列表中的最大值
        Optional<Integer> max2 = list.stream().reduce(Integer::max);

        System.out.println(reduce);
    }
}
