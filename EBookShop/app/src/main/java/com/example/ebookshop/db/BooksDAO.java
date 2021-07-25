package com.example.ebookshop.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BooksDAO {

    @Insert
    public void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("select * from booksTable")
    LiveData<List<Book>> getAllBooks();

    @Query("select * from booksTable where category_id==:categoryID")
    Category getBook(int categoryID);
}
