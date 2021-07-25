package com.example.assignment01;

public class Users {
    String Name,Address,Contact, Gender, UserId;
    public Users(){ }

    public Users(String userid,String name, String contact,String address,  String gender) {
        Name = name;
        Address = address;
        Contact = contact;
        Gender = gender;
        UserId=userid;
    }

    public String getName() {
        return Name;
    }

    public String getContact() {
        return Contact;
    }

    public String getAddress() {
        return Address;
    }

    public String getGender() { return Gender;}

    public String getUserId(){return UserId;}
}
