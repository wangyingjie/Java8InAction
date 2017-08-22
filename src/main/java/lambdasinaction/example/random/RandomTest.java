package lambdasinaction.example.random;

import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author: wangyingjie1
 * @version: 1.0
 * @createdate: 2017-08-22 17:02
 */
public class RandomTest {

    @Test
    public void testRandom() {

        Random r = new Random();
        IntStream ints = r.ints(0, (10 + 1));
        int asInt = ints.limit(1).findFirst().getAsInt();

        System.out.println(asInt);
    }

    @Test
    public void testRandom2() {

        Random r = new Random();
        IntStream ints = r.ints(10,0, (10 + 1));
        ints.forEach(System.out::println);
    }

}