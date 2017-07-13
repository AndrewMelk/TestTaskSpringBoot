package com.testTask.jdbc.extractors;

import com.testTask.domain.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 05.07.2017.
 */
public class BookExtractor implements ResultSetExtractor<Book> {
    public Book extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Book book = new Book();
        book.setId(resultSet.getInt(1));
        book.setTitle(resultSet.getString(2));
        book.setYear(resultSet.getInt(3));
        book.setAuthor(resultSet.getString(4));
        book.setCategory(resultSet.getString(5));

        return book;
    }
}
