package com.jd;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionOperation {

    //array convert stream
    @Test
    public void testStr2Stream() {
        //skuId1,num;skuId2,num
        String skuNum = "10001,1;10002,2";
        Stream<String> stream = Arrays.stream(skuNum.split(";"));
        stream.forEach(System.out::println);
    }

    //stream flatMap
    @Test
    public void testStreamFlatMap() {
        //skuId1,num;skuId2,num
        String skuNum = "10001,1;10002,2";
        Arrays.stream(skuNum.split(";"))
                .flatMap(str -> Arrays.stream(str.split(",")))
                .forEach(System.out::println);
    }

    // stream map
    @Test
    public void testStreamMap() {
        //skuId1,num;skuId2,num
        String skuNum = "10001,1;10002,2";
        Arrays.stream(skuNum.split(";"))
                .map(str -> str.split(","))
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    // string stream map Object
    @Test
    public void testStr2OrderSkuInfo() {
        //skuId1,num;skuId2,num
        String skuNum = "10001,1;10002,2";

        List<OrderSkuInfo> skuInfoStream = Arrays.stream(skuNum.split(";"))
                .map(str -> str.split(","))
                .map(arr -> new OrderSkuInfo(Long.valueOf(arr[0]), Integer.valueOf(arr[1])))
                .collect(Collectors.toList());

        System.out.println(skuInfoStream);
    }

    // string stream convert Map Collection
    @Test
    public void testStrConvertMap() {
        //skuId1,num;skuId2,num
        List<String> bookOrderList = Arrays.asList("10001,1;10002,2");

        Map<Long, OrderSkuInfo> skuInfoMap = extractOrderSkuInfo2(bookOrderList);

        System.out.println(skuInfoMap);
    }

    // 以上的方法在不考虑异常的情况下可以重构成如下ss
    private Map<Long, OrderSkuInfo> extractOrderSkuInfo2(List<String> bookOrderList) {
        //k->v: 实物子单skuId -> OrderSkuInfo
        Map<Long, OrderSkuInfo> map = bookOrderList.stream()
                .flatMap(skuInfo -> Arrays.stream(skuInfo.split(";")))
                .map(skuInfo -> skuInfo.split(","))
                .collect(Collectors.toMap(
                        arr -> Long.valueOf(arr[0]),
                        arr -> new OrderSkuInfo(Long.valueOf(arr[0]), Integer.valueOf(arr[1])),
                        (oldValue, newValue) -> new OrderSkuInfo(oldValue.getSkuId(), oldValue.getQuantity() + newValue.getQuantity()))
                );
        return map;
    }

    static class OrderSkuInfo {
        private long skuId;
        private int quantity;

        public OrderSkuInfo(long skuId, int quantity) {
            this.skuId = skuId;
            this.quantity = quantity;
        }

        public long getSkuId() {
            return skuId;
        }

        public void setSkuId(long skuId) {
            this.skuId = skuId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "OrderSkuInfo{" +
                    "skuId=" + skuId +
                    ", quantity=" + quantity +
                    '}';
        }
    }

}
