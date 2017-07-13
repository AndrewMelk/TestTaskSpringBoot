package com.testTask.service.impl;


import com.testTask.dao.BooksDao;
import com.testTask.domain.Book;
import com.testTask.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andrew on 05.07.2017.
 */
@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksDao booksDao;

    public void storeBook(Book book) {
      booksDao.storeBook(book);
    }

    public Book bookById(int id) {
      return booksDao.bookById(id);
    }

    public void updateBook(Book book) {
        booksDao.updateBook(book);

    }


    public void deleteBook(int id) {
      booksDao.deleteBook(id);

    }


    public List<Book> getAllbooks() {
        return booksDao.getAllbooks();
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return booksDao.getBooksByCategory(category);
    }


    public void deleteAll(){

    }
}
