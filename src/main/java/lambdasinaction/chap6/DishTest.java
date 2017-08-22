package lambdasinaction.chap6;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

/**
 * Created by wangyingjie1 on 2017/3/1.
 */
public class DishTest {

    public static void main(String[] args) {

        // 对Dish 的menu菜单的所有卡路里求和
        Integer sum = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("sum=========" + sum);

        // 使用collectors提供的广义方法 reducing
        Integer sum2 = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (a, b) -> a + b));
        Integer sum3 = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

        IntSummaryStatistics intSummaryStatistics = Dish.menu.stream().mapToInt(Dish::getCalories).summaryStatistics();



        // 求平均数
        Double average = Dish.menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

        // 一次性可以得到 getCalories 的各种统计信息
        IntSummaryStatistics statistics = Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println(statistics.getAverage() + "\t" + statistics.getSum() + "\t" + statistics.getMax());
        System.out.println("==========>" + statistics);

        //String names = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(",", "|", "|"));

        //String names = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(","));

        //最为推荐的使用 join 链接字符串
        //String names = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining());

        //最为通用的reduce使用方法
        String names = Dish.menu.stream().collect(Collectors.reducing("", Dish::getName, (name1, name2) -> name1 + name2));

        System.out.println("names========" + names);


    }
}
