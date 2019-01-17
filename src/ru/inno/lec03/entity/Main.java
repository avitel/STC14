package ru.inno.lec03.entity;

import ru.inno.lec03.entity.Student;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexey Balakin on 16.01.2019.
 */
public class Main {
    public static void main(String[] args) {
        List students = getStudents();
        Set obj = new HashSet();
        for (int i = 0; i < students.size() ; i++) {
            Student stu = (Student) students.get(i);
            System.out.println(stu.getFirstName());
        }

        for (Object student : students) {
            Student stu = (Student) student;
            System.out.println(stu.getFirstName());
        }
    }

    private static List getStudents() {
        return Arrays.asList(new Student[]{
                new Student("John", "Conor"),
                new Student("Ivan", "Ivanov"),
                new Student("Vlad", "Vladov"),
                new Student("John", "Smith")
        });

    }
}