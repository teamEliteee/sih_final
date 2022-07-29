package com.example.login;

public class User {
    public String email;
    public String firstName;
    public String lastName;
    public String age;
    public String gender;
    public String lang;

    public User(){

    }

    public User(String email,String firstName,String lastName,String age,String gender,String lang){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.lang = lang;
    }

}
