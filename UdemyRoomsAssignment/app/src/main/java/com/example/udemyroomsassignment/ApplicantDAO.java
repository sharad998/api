package com.example.udemyroomsassignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ApplicantDAO {

    @Insert
    public Long addApplicant(Applicant applicant);

    @Update
    public  void updateApplicant(Applicant applicant);

    @Delete
    public void deleteApplicant(Applicant applicant);

    @Query ("select * from applicants where applicant_id==:applicantID")
    public Applicant getApplicant(long applicantID);

    @Query ("select * from applicants")
    public List<Applicant> getApplicants();


}
