package com.cs.springboot.java8;

import com.cs.springboot.java8.vo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: stream
 * @author: chushi
 * @create: 2020-04-27 13:40
 **/
public class StreamTest {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student stu = new Student();
        stu.setAge("1");
        stu.setId("12");
        stu.setName("xiaowang");
        stu.setSex("nan");
        students.add(stu);

        Student stu1 = new Student();
        stu1.setAge("1");
        stu1.setId("22");
        stu1.setName("xiaowang");
        stu1.setSex("nan");
        students.add(stu1);

        Student stu2 = new Student();
        stu2.setAge("1");
        stu2.setId("12");
        stu2.setName("xiaohong");
        stu2.setSex("nv");
        students.add(stu2);

        Student stu3 = new Student();
        stu3.setAge("1");
        stu3.setId("12");
        stu3.setName("xiaowang");
        stu3.setSex("nv");
        students.add(stu3);

        Map<String,List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getId));

        System.out.println(map);

    }
}
