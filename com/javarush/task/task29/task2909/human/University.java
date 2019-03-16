package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;


    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student returnStudent = null;
        for (Student student: students) {
            if (student.getAverageGrade() == averageGrade)
                returnStudent = student;
        }
        return returnStudent != null ? returnStudent : null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Map<Double, Student> mapOfStudents = new TreeMap<>();
        for (Student student: students) {
          mapOfStudents.put(student.getAverageGrade(), student);
        }
        return mapOfStudents.get(((TreeMap<Double, Student>) mapOfStudents).lastKey());
    }

    public Student getStudentWithMinAverageGrade() {
        Map<Double, Student> mapOfStudents = new TreeMap<>();
        for (Student student: students) {
            mapOfStudents.put(student.getAverageGrade(), student);
        }
        return mapOfStudents.get(((TreeMap<Double, Student>) mapOfStudents).firstKey());
    }

    public void expel(Student student) {
        students.remove(student);
    }
}