package com.metacodersbd.doctorhousedoctor.model;

public class getuserProfilemodle {


    String name , gender , age ;

    public getuserProfilemodle() {
    }

    public getuserProfilemodle(String name, String gender, String age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
