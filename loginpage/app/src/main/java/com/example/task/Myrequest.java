package com.example.task;

public class Myrequest {

    String ldapGroup, userName, password, imei;

    public Myrequest(String ldapGroup, String userName, String password, String imei) {
        this.ldapGroup = ldapGroup;
        this.userName = userName;
        this.password = password;
        this.imei = imei;
    }
}
