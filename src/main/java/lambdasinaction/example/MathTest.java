package lambdasinaction.example;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.IntBinaryOperator;

public class MathTest {

    public int multiply(NumberProvider numberProvider, Math math) {
        Optional<Integer> result = numberProvider.getNumber()
                .flatMap(first -> numberProvider.getNumber()
                        .map(second -> math.multiply(first, second))
                );

        System.out.println("result===========>" + result.get());

        return 0;
    }

    public int multiply(NumberProvider numberProvider1, NumberProvider numberProvider2, Math math) {

        //int multiply = math.multiply(numberProvider1, numberProvider2);

        //System.out.println("result===========>" + multiply);

        return 0;
    }


    public static void main(String[] args) {
        MathTest mathTest = new MathTest();
        mathTest.multiply(() -> Optional.of(3), (x, y) -> x * y);
        mathTest.multiply(() -> Optional.of(3), (x, y) -> x + y);

        IntBinaryOperator tComparator = (x1, y1) -> x1 + y1;

        int asInt = tComparator.applyAsInt(1, 2);

        //mathTest.multiply(() -> Optional.of(7), () -> Optional.of(3), (x, y) -> x.getNumber().get() + y.getNumber().get());
    }
}
