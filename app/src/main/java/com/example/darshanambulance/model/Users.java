package com.example.darshanambulance.model;

public class Users {
    public String fullname,age,email,address,phone;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Users(){
    }

    public Users(String fullname, String age, String email,String address,String phone) {
        this.fullname = fullname;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

}
