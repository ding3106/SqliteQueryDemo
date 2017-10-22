package com.lenovo.dingjq1.sqlitequerydemo.com.imooc.bean;

/**
 * Created by dingjq on 2017/10/13.
 */

public class Person {

    private int _id;
    private String name;
    private int age;

    public Person(int _id, String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
