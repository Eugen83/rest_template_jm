package com.evgeniypodprugin.rest_template_learning.model;

public class User {
    private Long id;
    private String Name;
    private String lastName;
    private byte Age;

    public User() {
    }

    public User(Long id, String name, String lastName, byte age) {
        this.id = id;
        Name = name;
        this.lastName = lastName;
        Age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return Age;
    }

    public void setAge(byte age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Age=" + Age +
                '}';
    }
}
