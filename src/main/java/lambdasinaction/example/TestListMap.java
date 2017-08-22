package lambdasinaction.example;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Test
    public void testToMapSort() {

        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);

        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        // sort by keys, a,b,c..., and return a new LinkedHashMap
        // toMap() will returns HashMap by default, we need LinkedHashMap to keep the order.
        Map<String, Integer> result = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        // Not Recommend, but it works.
        //Alternative way to sort a Map by keys, and put it into the "result" map
        Map<String, Integer> result2 = new LinkedHashMap<>();
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

        System.out.println("Sorted...");
        System.out.println(result);
        System.out.println(result2);
    }

    @Test
    public void testToMapSort2() {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        //sort by values, and reserve it, 10,9,8,7,6...
        Map<String, Integer> result = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        //Alternative way
        Map<String, Integer> result2 = new LinkedHashMap<>();
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

        System.out.println("Sorted...");
        System.out.println(result);
        System.out.println(result2);
    }


    @Test
    public void testMap2List() {

        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");

        System.out.println("\n1. Export Map Key to List...");

        List<Integer> result = new ArrayList(map.keySet());

        result.forEach(System.out::println);
        System.out.println("\n2. Export Map Value to List...");

        List<String> result2 = new ArrayList(map.values());
        result2.forEach(System.out::println);
    }

    @Test
    public void testConvertMapToList() {
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");

        // split a map into 2 List
        List<Integer> resultSortedKey = new ArrayList<>();

        List<String> resultValues = map.entrySet().stream()
                //sort a Map by key and stored in resultSortedKey
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .peek(e -> resultSortedKey.add(e.getKey()))
                .map(x -> x.getValue())
                // filter banana and return it to resultValues
                .filter(x -> !"banana".equalsIgnoreCase(x))
                .collect(Collectors.toList());

        resultSortedKey.forEach(System.out::println);
        resultValues.forEach(System.out::println);
    }

    //统计字符的出现次数
    @Test
    public void testFrequency() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("a");
        list.add("a");

        System.out.println("\nExample 1 - Count 'a' with frequency");
        System.out.println("a : " + Collections.frequency(list, "a"));

        System.out.println("\nExample 2 - Count all with frequency");
        Set<String> uniqueSet = new HashSet<String>(list);
        System.out.println("==========>" + uniqueSet);
        for (String temp : uniqueSet) {
            System.out.println(temp + ": " + Collections.frequency(list, temp));
        }
    }


    @Test
    public void testStreamClosed() {
        Stream<String> stream = Stream.of("x", "a", "c");
        stream.forEach(System.out::println);

        //java.lang.IllegalStateException: stream has already been operated upon or closed
        long count = stream.filter(s -> s.contains("a")).count();
        System.out.println(count);
    }


    @Test
    public void testStreamOfClosed() {
        Stream<String> stream = Stream.of("x", "a", "c");

        Supplier<Stream<String>> supplier = () -> Stream.of("x", "a", "c");
        supplier.get().forEach(System.out::println);

        long count = supplier.get().filter(s -> s.contains("a")).count();
        System.out.println(count);

    }


    @Test
    public void testFlatMap() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);

        //filter a stream of string[], and return a string[]?
        Stream<String[]> stream = temp.filter(x -> "a".equals(x));

        stream.forEach(System.out::println);
    }


    @Test
    public void testFlatMap2() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);

        //Stream<String>, GOOD!
        Stream<String> stringStream = temp.flatMap(x -> Arrays.stream(x));

        Stream<String> stream = stringStream.filter(x -> "a".equals(x.toString()));

        stream.forEach(System.out::println);

        Stream<String> stream2 = Arrays.stream(data)
                .flatMap(x -> Arrays.stream(x))
                .filter(x -> "a".equals(x.toString()));
        stream2.forEach(System.out::println);
    }

    @Test
    public void testFlatMap2Int() {

        int[] intArray = {1, 2, 3, 4, 5, 6};

        //1. Stream<int[]>
        Stream<int[]> streamArray = Stream.of(intArray);

        //2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

        intStream.forEach(x -> System.out.println(x));

    }


}