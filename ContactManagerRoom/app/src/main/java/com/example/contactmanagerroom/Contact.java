package com.example.contactmanagerroom;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {

    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ColumnInfo(name = "contact_name")
    public String name;

    @ColumnInfo(name = "contact_email")
    public String email;

    public Contact(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
