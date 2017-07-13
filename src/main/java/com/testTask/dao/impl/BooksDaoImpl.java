package com.testTask.dao.impl;

import com.testTask.dao.BooksDao;
import com.testTask.domain.Book;
import com.testTask.jdbc.mappers.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrew on 05.07.2017.
 */
@Repository
public class BooksDaoImpl implements BooksDao {

    Logger logger = Logger.getLogger(this.toString());
    @Autowired
    DataSource dataSource;

    public void storeBook(Book book) {
        String sql = "INSERT INTO books(title, year, author, category) VALUES (?,?,?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(
                sql, new Object[]{book.getTitle(),book.getYear(),book.getAuthor(),book.getCategory()}
        );
    }

    public Book bookById(int id) {

        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE id = " + id;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        bookList = jdbcTemplate.query(sql, new BookRowMapper());
        if (bookList.size()==0){
            return null;
        }else {
            return bookList.get(0);

        }
    }

    public void updateBook(Book book) {
        logger.log(Level.SEVERE, book.toString());
        String sql = "UPDATE books SET title ='" + book.getTitle() +"', year = " + book.getYear() + ", author = '"+book.getAuthor()+"', category = '"+book.getCategory()+"' WHERE id = "+book.getId();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        logger.log(Level.SEVERE, sql);

        jdbcTemplate.update(sql);

    }


    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = " + id;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql);

    }


    public List<Book> getAllbooks() {
        List<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM books";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        books = jdbcTemplate.query(sql, new BookRowMapper());
        Collections.sort(books, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));

        return books;
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(category) LIKE LOWER (" + "'" + category + "')";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        bookList = jdbcTemplate.query(sql, new BookRowMapper());
        if (bookList.size()==0){
            return null;
        }else {
            return bookList;

        }
    }

    @Override
    public void deleteAll(){
        String sql = "";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    }
}
