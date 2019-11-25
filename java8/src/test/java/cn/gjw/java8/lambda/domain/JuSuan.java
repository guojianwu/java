package cn.gjw.java8.lambda.domain;

@FunctionalInterface
public interface JuSuan<T, R> {
    public R js(T t1, T t2);
}
