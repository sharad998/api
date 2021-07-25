package com.example.ebookshop.db;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "booksTable", foreignKeys = @ForeignKey(entity=Category.class,parentColumns ="id",childColumns = "category_id",onDelete = CASCADE))
public class Book extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    private int bookId;

    @ColumnInfo(name = "book_name")
    private String bookName;

    @ColumnInfo(name = "unit_Price")
    private String unitPrice;


    @ColumnInfo(name = "category_id")
    private int categoryId;

    public Book(int bookId, String bookName, String unit_Price, int categoryId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.unitPrice = unit_Price;
        this.categoryId = categoryId;
    }

    public Book() {

    }

    @Bindable
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
        notifyPropertyChanged(BR.bookId);

    }

    @Bindable
    public String getBookName() {
        return bookName;

    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(BR.bookName);
    }

    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(BR.unitPrice);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }
}

