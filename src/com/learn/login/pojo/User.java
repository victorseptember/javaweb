package com.learn.login.pojo;

public class User {
    private int uid;
    private String uname;
    private String pwd;
    private String sex;
    private int age;
    private String birth;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                '}';
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public User(int uid, String uname, String pwd, String sex, int age, String birth) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;
        this.sex = sex;
        this.age = age;
        this.birth = birth;
    }

    public User() {
    }

}
