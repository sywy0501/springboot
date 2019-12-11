package com.cs.springboot.huawei;

import java.util.Scanner;

/**
 * @author: cs
 * @date: 2019/10/18 2:31 下午
 * @desc:
 */
public class Test {

    static class Teacher{

        public void teacherMethod(){}
    }

    static class Student{
        public void studentMethod(){}
    }

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        Student student = new Student();
        teacher.teacherMethod();
        student.studentMethod();
    }
}
