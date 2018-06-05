package cn.zjj.tips.base.controller.java8newspec.bean;

import java.util.Optional;

/**
 * @Author: Jack
 * @Date: 2018/6/5 10:45
 * @Description:
 * 手机和邮箱不是一个人的必须有的，所以我们利用 Optional 类定义
 */
public class User {

    private long id;
    private String name;
    private int age;
    private Optional<Long> phone;
    private Optional<String> email;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Optional<Long> getPhone() {
        return phone;
    }

    public void setPhone(Optional<Long> phone) {
        this.phone = phone;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }
}
