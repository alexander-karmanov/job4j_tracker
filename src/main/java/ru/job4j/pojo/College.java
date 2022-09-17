package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("John Smith");
        student.setGroup("1");
        student.setAdmission(new Date());
        System.out.println(student.getName() + " is in group " + student.getGroup() + ", date of admission: " + student.getAdmission());
    }
}
