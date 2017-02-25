package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import static java.util.Comparator.comparing;
/**
 * Created by wangyingjie1 on 2017/2/25.
 */
public class LambdaTest {

    public static void main(String[] args) {
        forEach(
                Arrays.asList(1, 2, 3, 4),
                (Integer i) -> System.out.println(i)
        );

        List<String> list = map(
                Arrays.asList(1, 2, 3, 4),
                (Integer i) -> i.toString() + "xxxxxxx"
        );

        System.out.println(list);

        operator((left, right) -> {
            return 1 + 2;
        });

        IntBinaryOperator operator = (int left, int right) -> {
            return left + right;
        };

        //具有类型推断的使用形式
        IntBinaryOperator operator2 = (left, right) -> {
            return left + right;
        };


        int asInt = operator.applyAsInt(1, 3);

        System.out.println("asInt====" + asInt);

        operator(operator);

        //集合排序的不同写法
        List<String> strList = Arrays.asList("xxx", "yyy");
        strList.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        strList.sort(String::compareToIgnoreCase);

        //import static java.util.Comparator.comparing;  静态方法可以直接导入
        strList.sort(comparing((s1) -> s1.compareToIgnoreCase(s1)));

    }

    public static void operator(IntBinaryOperator operator) {
        int asInt = operator.applyAsInt(1, 2);
        System.out.println("xxxxx----------" + asInt);
    }

    //自定义循环处理List集合元素
    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> fun) {
        List<R> result = new ArrayList<R>();

        for (T t : list) {
            result.add(fun.apply(t));
        }
        return result;
    }
}
