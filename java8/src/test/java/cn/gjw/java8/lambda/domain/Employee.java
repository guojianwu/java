package cn.gjw.java8.lambda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class Employee {
    private String name;
    private Integer age;
    private Double salary;
    private Status status;

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public enum Status {
        FREE,
        BUSY,
        VOCATION
    }


}
