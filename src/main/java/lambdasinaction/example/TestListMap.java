package lambdasinaction.example;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wangyingjie1
 * @version: 1.0
 * @createdate: 2017-08-22 10:05
 */
public class TestListMap {

    @Test
    public void testToMap1() {

        List<Hosting> list = getHostings();

        Map<Integer, String> map1 = list.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getName));
        System.out.println(map1);
    }

    private List<Hosting> getHostings() {
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "mkyong.com", 1));
        return list;
    }

    @Test
    public void testToMap2() {

        List<Hosting> list = getHostings();

        list.add(new Hosting(1, "liquidweb.com", 100000));

        //map 中的key有冲突的解决方案
        Map<Integer, Long> map1 = list.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getWebsites, (oldValue, newValue) -> oldValue));
        //Map<Integer, Long> map1 = list.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getWebsites, (oldValue, newValue) -> oldValue + newValue));

        System.out.println(map1);


        Map<String, Long> longMap = list.stream().collect(Collectors.toMap(h -> h.getName(), h -> h.getWebsites()));
        System.out.println(longMap);
    }

    @Test
    public void testToMap3() {

        List<Hosting> list = getHostings();

        Map<String, Long> longMap = list.stream().collect(Collectors.toMap(h -> h.getName(), h -> h.getWebsites()));
        System.out.println(longMap);
    }

    @Test
    public void testToSortMap() {

        List<Hosting> list = getHostings();

        // 有序的Map集合
        LinkedHashMap<String, Long> collect = list.stream().sorted(
                Comparator.comparing(Hosting::getWebsites)
                //.thenComparing(Hosting::getWebsites)
                //.reversed()
        ).collect(Collectors.toMap(h -> h.getName(), h -> h.getWebsites(), (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(collect);
    }

    @Test
    public void testToSortMapeversed() {

        List<Hosting> list = getHostings();

        // 有序的Map集合
        LinkedHashMap<String, Long> collect = list.stream().sorted(
                Comparator.comparing(Hosting::getWebsites)
                        //.thenComparing(Hosting::getWebsites)
                        .reversed()
        ).collect(Collectors.toMap(Hosting::getName, v -> v.getWebsites(), (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(collect);
    }

    @Test
    public void testToSortThenComparing() {

        List<Hosting> list = getHostings();

        // 有序的Map集合
        LinkedHashMap<String, Long> collect = list.stream().sorted(
                Comparator.comparing(Hosting::getWebsites)
                        .thenComparing(Hosting::getWebsites)
                        .reversed()
        ).collect(Collectors.toMap(Hosting::getName, v -> v.getWebsites(), (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(collect);
    }

}