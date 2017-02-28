package lambdasinaction.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;


public class StreamVsCollection {

    public static void main(String...args){
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
        // uncommenting this line will result in an IllegalStateException
        // because streams can be consumed only once
        //s.forEach(System.out::println);

        Map<Dish.Type, List<Dish>> map = Dish.menu.stream().collect(groupingBy(Dish::getType));

        System.out.println(map);
        map.forEach((k,v)->{
            System.out.println(k.toString() + "===========" + v.toString());
        });
    }
}