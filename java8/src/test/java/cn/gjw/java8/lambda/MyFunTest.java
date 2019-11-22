package cn.gjw.java8.lambda;

import cn.gjw.java8.lambda.domain.JuSuan;
import cn.gjw.java8.lambda.domain.MyFun;
import cn.gjw.java8.lambda.domain.MyUp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SpringBootTest
public class MyFunTest {


    @Test
    public void  test2(){
        Integer test = test(10, i -> i * i);
        System.out.println(test);
    }


    public Integer test(Integer i, MyFun myFun){
        return myFun.test(i);
    }
    @Test
    public void testMyUp(){
        String s = myUp("dfghjkKGHJKijd", (str) -> str.toUpperCase());
        System.out.println(s);
    }

    public String myUp(String str, MyUp myUp){
        return myUp.getValue(str);
    };


    @Test
    public void test(){
        Long a = a(10L, 20L, (l1, l2) -> l1 * l2);
        System.out.println(a);
    }


    public Long a(Long l1, Long l2, JuSuan<Long,Long> js){
        return js.js(l1,l2);
    }

    @Test
    public void testHa(){
        ha(100,(m)-> System.out.println(m));
    }

    public void ha(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    @Test
    public  void  testSupp(){
        List<Integer> list = supp(10, () -> (int)(Math.random() * 100));
//        for (Integer i:list){
//            System.out.println(i);
//        }
        list.forEach(x->{
            System.out.println(x);
        });
    }

    public List<Integer> supp(int num , Supplier<Integer> supplier){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<num;i++){
            list.add(supplier.get());
        }
        return  list;
    }

    @Test
    public void testmyfun(){
        String s = myfun("ghjklHJKLghjklhjHHJhjk", str -> str.toUpperCase());
        System.out.println(s);
    }

    public String  myfun(String str, Function<String,String> function){
        return function.apply(str);

    }

    @Test
    public void testPre(){
        List<String> list = Arrays.asList("sdfsd","sdf","as","swdfgh");
        List<String> list1 = pre(list, (str) -> str.length() > 3);
        list1.forEach((item)->{
            System.out.println(item);
        });
    }

    public List<String> pre(List<String> list, Predicate<String> predicate){
        ArrayList<String> newList = new ArrayList<>();
        list.forEach(item->{
            if(predicate.test(item)){
                newList.add(item);
            }
        });





        return  newList;
    }

    @Test
    public void test3(){
        Comparator<Integer> com=(x,y)->Integer.compare(x,y);
        int i = com.compare(10, 20);
        System.out.println(i);
    }

}
