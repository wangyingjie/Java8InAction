package lambdasinaction.chap5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by wangyingjie1 on 2017/2/28.
 */
public class NumberTest {

    public static void main(String[] args) {
        IntStream intStream = IntStream.range(1, 100).filter(i -> i % 2 == 0);

        //偶数个数
        long count = intStream.count();

        System.out.println("count===========>" + count);

        //得到装箱的数字列表
        List<Integer> collect = IntStream.range(1, 100).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());

        System.out.println(collect);

        List<Integer> collect1 = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());

        System.out.println(collect1);

        //查找 20 以内的勾股数
        Stream<int[]> stream = IntStream.rangeClosed(1, 20).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 20).filter(b ->
                        Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        List<int[]> collect2 = stream.collect(Collectors.toList());

        System.out.println(collect2);

        collect2.forEach(arr -> System.out.println(arr[0] + "\t" + arr[1] + "\t" + arr[2]));
    }
}
