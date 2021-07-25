package com.example.ebookshop.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CategoryDAO {
    @Insert
    public void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("select * from categories_table")
    LiveData<List<Category>> getAllCategories();

    @Query("select * from categories_table where id==:categoryID")
    Category getCategory(int categoryID);



}
