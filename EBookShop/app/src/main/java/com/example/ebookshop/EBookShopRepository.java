package com.example.ebookshop;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.ebookshop.db.Book;
import com.example.ebookshop.db.BooksDAO;
import com.example.ebookshop.db.BooksDatabase;
import com.example.ebookshop.db.Category;
import com.example.ebookshop.db.CategoryDAO;

import java.util.List;

public class EBookShopRepository {

    private CategoryDAO categoryDAO;
    private BooksDAO booksDAO;
    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> books;


    public EBookShopRepository(Application application) {
        BooksDatabase booksDatabase = BooksDatabase.getInstance(application);
        categoryDAO = booksDatabase.categoryDAO();
        booksDAO = booksDatabase.booksDAO();

    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Book>> getBooks() {
        return booksDAO.getAllBooks();
    }

    public void insertCategory(Category category) {
        new InsertCategory(categoryDAO).execute(category);
        //Non-static method 'execute(Params...)' cannot be referenced from a static context hence cast parameter to java.lang.runnable
    }

    public void insertBook(Book book) {
        new InsertBook(booksDAO).execute(book);
        //Non-static method 'execute(Params...)' cannot be referenced from a static context hence cast parameter to java.lang.runnable
    }

    public static class InsertCategory extends AsyncTask<Category, Void, Void> {


        private CategoryDAO categoryDAO;

        public InsertCategory(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;

        }


        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.insert(categories[0]);
            return null;
        }
    }


    public static class InsertBook extends AsyncTask<Book, Void, Void> {


        private BooksDAO booksDAO;

        public InsertBook(BooksDAO booksDAO) {
            this.booksDAO = booksDAO;

        }


        @Override
        protected Void doInBackground(Book... books) {

            booksDAO.insert(books[0]);
            return null;
        }
    }


/////////////////////////////////////////
//Delete
////////////////////////////////////

    public void deleteCategory(Category category) {
        new DeleteCategory(categoryDAO).execute(category);
        //Non-static method 'execute(Params...)' cannot be referenced from a static context hence cast parameter to java.lang.runnable
    }

    public void deleteBook(Book book) {
        new DeleteBook(booksDAO).execute(book);
        //Non-static method 'execute(Params...)' cannot be referenced from a static context hence cast parameter to java.lang.runnable
    }

    public static class DeleteCategory extends AsyncTask<Category, Void, Void> {


        private CategoryDAO categoryDAO;

        public DeleteCategory(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;

        }


        @Override
        protected Void doInBackground(Category... categories) {

            categoryDAO.delete(categories[0]);
            return null;
        }
    }


    public static class DeleteBook extends AsyncTask<Book, Void, Void> {


        private BooksDAO booksDAO;

        public DeleteBook(BooksDAO booksDAO) {
            this.booksDAO = booksDAO;

        }


        @Override
        protected Void doInBackground(Book... books) {

            booksDAO.delete(books[0]);
            return null;
        }


    }



///////////////////
//update
//////////////////


    public void updateCategory(Category category){
        new UpdateCategory(categoryDAO).execute(category);
        //Non-static method 'execute(Params...)' cannot be referenced from a static context hence cast parameter to java.lang.runnable
    }

    public void updateBook(Book book){
        new UpdateBook(booksDAO).execute(book);
        //Non-static method 'execute(Params...)' cannot be referenced from a static context hence cast parameter to java.lang.runnable
    }

public static class UpdateCategory extends AsyncTask<Category,Void, Void> {



    private CategoryDAO categoryDAO;

    public UpdateCategory(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;

    }



    @Override
    protected Void doInBackground(Category... categories) {

        categoryDAO.update(categories[0]);
        return null;
    }
}






public static class UpdateBook extends AsyncTask<Book,Void, Void> {



    private BooksDAO booksDAO;

    public UpdateBook(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;

    }



    @Override
    protected Void doInBackground(Book...books) {

        booksDAO.update(books[0]);
        return null;
    }
}
}



