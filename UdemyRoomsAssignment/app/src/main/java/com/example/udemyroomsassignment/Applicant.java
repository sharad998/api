package com.example.udemyroomsassignment;

import androidx.annotation.ColorInt;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "applicants")
public class Applicant {

    @ColumnInfo(name = "applicant_name")
    private String name;

    @ColumnInfo(name = "applicant_email")
    private String email;

    @ColumnInfo(name = "applicant_country")
    private String counrty;

    @ColumnInfo(name = "applicant_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    public Applicant(long id,String name, String email, String counrty) {
        this.name = name;
        this.email = email;
        this.counrty = counrty;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCounrty() {
        return counrty;
    }

    public void setCounrty(String counrty) {
        this.counrty = counrty;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

