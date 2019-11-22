package cn.gjw.java8.stream;

import cn.gjw.java8.lambda.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class MyTest {
    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,99999.99, Employee.Status.BUSY),
            new Employee("李四",38,555.99, Employee.Status.BUSY),
            new Employee("王五",50,6666.66, Employee.Status.VOCATION),
            new Employee("赵六",16,3333.33, Employee.Status.FREE),
            new Employee("田七",8,77777.77, Employee.Status.VOCATION),
            new Employee("田七",8,77777.77, Employee.Status.VOCATION)
    );



    @Test
    public void test8(){
        boolean b = employees.stream().allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        boolean b1 = employees.stream().anyMatch((e) -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(b1);

        boolean b2 = employees.stream().noneMatch((e) -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(b2);

        Optional<Employee> first = employees.stream().sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        System.out.println(first.get());

        Optional<Employee> any = employees.parallelStream().filter(employee -> employee.getStatus().equals(Employee.Status.VOCATION)).findAny();
        System.out.println(any.get());

        long count = employees.stream().count();
        System.out.println(count);


        Optional<Employee> max = employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());


        Optional<Employee> min = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min.get());

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);


        Double reduce1 = employees.stream().map(e -> e.getSalary()).reduce( 0.0, (x, y) -> x + y);
        System.out.println(reduce1);

        Optional<Double> reduce2 = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce2.get());


        List<String> collect = employees.stream().map(e -> e.getName()).collect(Collectors.toList());
        System.out.println(collect);
        Set<String> collect1 = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        System.out.println(collect1);

        HashSet<String> collect2 = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect2);

        Double collect3 = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect3);

        Optional<Employee> collect4 = employees.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect4);

        Optional<Double> collect5 = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compareTo));
        System.out.println(collect5.get());

        //分组

        Map<Employee.Status, List<Employee>> collect6 = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect6);


        Map<Employee.Status, List<Employee>> collect7 = employees.stream().collect(Collectors.groupingBy(employee -> employee.getStatus()));
        System.out.println(collect7);


        //分区
        Map<Boolean, List<Employee>> collect8 = employees.stream().collect(Collectors.partitioningBy(employee -> employee.getSalary() > 8000));
        System.out.println(collect8);

        //=======
        DoubleSummaryStatistics collect9 = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect9.getAverage());
        System.out.println(collect9.getMax());


        String collect10 = employees.stream().map(Employee::getName).collect(Collectors.joining(",","<<",">>"));
        System.out.println(collect10);
    }

    @Test
    public void test(){
        employees.stream().map((item)->{
            System.out.println(item);
            return item;
        }).forEach(item->{
            System.out.println(item);
        });

    }



    @Test
    public  void test3(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().map(e->e*e).forEach(e-> System.out.println(e));

        Optional<Integer> reduce = employees.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(reduce);

        long count = employees.stream().count();
        System.out.println(count);
    }
}
