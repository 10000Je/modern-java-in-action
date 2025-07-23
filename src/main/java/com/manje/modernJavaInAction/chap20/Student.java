package com.manje.modernJavaInAction.chap20;

// 자바의 데이터 클래스, 게터와 세터, 생성자를 직접 정의해야 하므로 장황함.
public class Student {

    private String name;
    private int id;

    public static void main(String[] args) {
        Student s = new Student("Raoul", 1);
        System.out.println(s.name);
        s.id = 1337;
        System.out.println(s.id);
    }

    public Student() {}

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

