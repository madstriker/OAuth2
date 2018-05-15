package com.my.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int userId;
    @NotNull(message = "Can't be null.")
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="salary")
    private int salary;
    @Column(name="age")
    private int age;
    @Column(name="email")
    @Email
    private String email;

    public User() {
    }

    public User(String username, String password, int salary, int age, String email) {
        this.username = username;
        this.password = password;
        this.salary = salary;
        this.age = age;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
