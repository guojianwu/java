package cn.gjw.java8.lambda;


import cn.gjw.java8.lambda.domain.Employee;
import cn.gjw.java8.lambda.domain.FilterByAge;
import cn.gjw.java8.lambda.domain.MyPredicate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Stream;

@SpringBootTest
public class MyTest {


    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 99999.99),
            new Employee("李四", 38, 555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 77777.77)
    );

    @Test
    public void test6() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        employees.forEach(employee -> System.out.println(employee));

    }


    public List<Employee> myfillter(List<Employee> employees, MyPredicate<Employee> mp) {
        ArrayList<Employee> employees1 = new ArrayList<>();
        for (Employee emps : employees) {
            if (mp.test(emps)) {
                employees1.add(emps);
            }
        }
        return employees1;
    }

    @Test
    public void test5() {
        employees.stream().filter((e) -> e.getSalary() > 6000).forEach(e -> {
            System.out.println(e);
        });
//        Stream<Employee> employeeStream = employees.stream().filter((e) -> e.getSalary() > 6000);
//        employeeStream.forEach((e -> {
//            System.out.println(e);
//        }));
    }

    @Test
    public void test4() {
        List<Employee> list = myfillter(employees, (e) -> e.getSalary() >= 500);
        for (Employee emps : list) {
            System.out.println(emps);
        }
    }

    @Test
    public void test3() {
        List<Employee> myfillter = myfillter(employees, new FilterByAge());
        for (Employee emps : myfillter) {
            System.out.println(emps);
        }
    }


    public List<Employee> fllterAge(List<Employee> list) {
        ArrayList<Employee> employees = new ArrayList<>();
        for (Employee emps : list) {
            if (emps.getAge() >= 35) {
                employees.add(emps);
            }
        }
        return employees;
    }

    @Test
    public void test2() {
        List<Employee> employees = this.fllterAge(this.employees);
        for (Employee emps : employees) {
            System.out.println(emps);
        }
    }

    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);

        TreeSet<Integer> ts = new TreeSet<>(com1);
        ts.add(45);
        ts.add(12);
        ts.add(12);

        ts.add(67);

        System.out.println(ts);


    }
}
