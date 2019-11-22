package cn.gjw.java8.lambda.domain;

public class FilterByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>=35;
    }
}
