package com.cs.springboot.java8;

import com.cs.springboot.java8.function.Inter;
import com.cs.springboot.java8.function.InterImpl;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author: ChuShi
 * @date: 2019/11/25 1:57 下午
 * @desc: Java8的函数式编程
 */
public class Function8 {
    public static void main(String[] args) {
        Inter.print();
        InterImpl inter = new InterImpl();
        inter.ptintf();
        String str = "try";
        print(str, System.out::println);

        Consumer<String> consumer1 = s-> System.out.print("车名:"+s.split("，")[0]);
        Consumer<String> consumer2 = s-> System.out.println("-->颜色:"+s.split("，")[1]);

        String[] strings = {"保时捷，白色","奔驰，红色"};
        for (String string:strings){
            consumer1.andThen(consumer2).accept(string);
        }

        Supplier<String> supplier = ()->"扩大肺活量";
        System.out.println(supplier.get());

        Function<Integer,Integer> function1 = e->e*6;
        Function<Integer,Integer> function2 = e->e*e;
        Integer apply2 = function1.compose(function2).apply(3);
        System.out.println(apply2);
        Integer apply = function1.andThen(function2).apply(3);
        System.out.println(apply);

        Function<Integer,Integer> identity = Function.identity();
        Integer apply1 = identity.apply(3);
        System.out.println(apply1);

        Predicate<Integer> predicate = t->t>0;
        boolean test = predicate.test(1);
        System.out.println(test);

        Predicate<String> predicate1 = s->s.length()>0;
        Predicate<String> predicate2 = Objects::isNull;
        boolean test1 = predicate1.and(predicate2).test("&&测试");
        System.out.println(test1);
        boolean test2 = predicate1.or(predicate2).test("||测试");
        System.out.println(test2);
        boolean test3 = predicate1.negate().test("取反");
        System.out.println(test3);
    }

    private static void print(String text,Inter inter){
        inter.println(text);
    }
}
