package com.syy.springboot.util;

import java.util.Objects;

/**
 * User
 *
 * @author: shiyan
 * @version: 2020-01-21 13:57
 * @Copyright: 2020 www.lenovo.com Inc. All rights reserved.
 */
public class User {

    private int id ;

    private String name;
    private int age;
    private int sc;

    public User(int id, String name, int age, int sc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sc = sc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sc=" + sc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
