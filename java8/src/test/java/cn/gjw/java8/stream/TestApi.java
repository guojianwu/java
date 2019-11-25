package cn.gjw.java8.stream;


import cn.gjw.java8.stream.domain.Trader;
import cn.gjw.java8.stream.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TestApi {
    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "剑桥");
        Trader mario = new Trader("Mario", "米兰");
        Trader alan = new Trader("Alan", "剑桥");
        Trader brian = new Trader("Brian", "剑桥");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void test() {
        transactions.stream().filter(e -> e.getYear().equals(2011)).forEach(System.out::println);

        transactions.stream().map(e -> e.getTrader().getCity()).distinct().forEach(System.out::println);

        transactions.stream().filter(e -> e.getTrader().getCity().equals("剑桥"))
                .sorted((e1, e2) -> e1.getTrader().getName().compareTo(e2.getTrader().getName()))
                .forEach(System.out::println);


        boolean b = transactions.stream().anyMatch(e -> e.getTrader().getCity().equals("米兰"));
        System.out.println(b);

        Integer reduce = transactions.stream().filter(e -> e.getTrader().getCity().equals("剑桥")).map(e -> e.getValue()).reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        Optional<Integer> max = transactions.stream().map(e -> e.getValue()).max((e1, e2) -> Integer.compare(e1, e2));
        System.out.println(max.get());
        Optional<Integer> max1 = transactions.stream().map(Transaction::getValue).max(Integer::compareTo);
        System.out.println(max1.get());


        Optional<Transaction> min = transactions.stream().min((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        System.out.println(min.get());
//        Optional<Integer> min = transactions.stream().map(e -> e.getValue()).min((e1, e2) -> Integer.compare(e1, e2));
//        System.out.println(min);

    }

}
