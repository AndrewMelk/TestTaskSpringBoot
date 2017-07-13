package com.testTask.dao;


import com.testTask.domain.Book;

import java.util.List;

/**
 * Created by Andrew on 05.07.2017.
 */
public interface BooksDao {
    public void storeBook(Book book);
    public Book bookById(int id);
    public void updateBook(Book book);
    public void deleteBook(int id);
    public List<Book> getAllbooks();
    public List<Book> getBooksByCategory(String category);
    public void deleteAll();
}
