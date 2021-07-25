package com.example.udemyroomsassignment;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Applicant.class},version = 1)
public abstract class ApplicantsAppDatabase extends RoomDatabase {
    public abstract ApplicantDAO getApplicantDAO();
}
