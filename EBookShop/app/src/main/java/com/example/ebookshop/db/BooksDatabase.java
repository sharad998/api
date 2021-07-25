package com.example.ebookshop.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Locale;

public abstract class BooksDatabase extends RoomDatabase {
    public abstract CategoryDAO categoryDAO();
    public abstract BooksDAO booksDAO();

    private static BooksDatabase instance;

    public static synchronized BooksDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BooksDatabase.class, "books_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void,Void,Void>{
        private CategoryDAO categoryDAO;
        private BooksDAO  booksDAO;

        public InitialDataAsyncTask(BooksDatabase booksDatabase){
            categoryDAO= booksDatabase.categoryDAO();
            booksDAO=booksDatabase.booksDAO();

        }


        @Override
        protected Void doInBackground(Void... voids) {

            Category category1= new Category();
            category1.setCategoryName("TextBooks");
            category1.setCategoryDescription("Text Books Description");

            Category category2 =new Category();
            category2.setCategoryName("Novels");
            category2.setCategoryDescription("Novels description");

            Category category3 =new Category();
            category3.setCategoryName("OtherBooks");
            category3.setCategoryDescription("Ohter Books description");

            categoryDAO.insert(category1);
            categoryDAO.insert(category2);
            categoryDAO.insert(category3);

            Book book1 = new Book();
            book1.setBookName("High School Java");
            book1.setUnitPrice("$ 15");
            book1.setCategoryId(1);

            Book book2= new Book();
            book1.setBookName("sociology");
            book1.setUnitPrice("$ 5");
            book2.setCategoryId(1);

            Book book3 = new Book();
            book1.setBookName("Quantum in universe");
            book1.setUnitPrice("$ 150");
            book3.setCategoryId(1);

            Book book4= new Book();
            book4.setBookName("tale of two cities");
            book4.setCategoryId(2);
            book4.setUnitPrice("$ 20");

            Book book5 = new Book();
            book5.setBookName("Trivia");
            book5.setUnitPrice("$ 80");
            book5.setCategoryId(3);

            Book book6 = new Book();
            book5.setBookName("Living in seldom");
            book5.setUnitPrice("$ 67");
            book5.setCategoryId(3);

            booksDAO.insert(book1);
            booksDAO.insert(book2);
            booksDAO.insert(book3);
            booksDAO.insert(book4);
            booksDAO.insert(book5);
            booksDAO.insert(book6);







            return null;
        }
    }


}
