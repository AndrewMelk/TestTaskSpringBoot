package com.testTask.jdbc.mappers;

import com.testTask.domain.Book;
import com.testTask.jdbc.extractors.BookExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 05.07.2017.
 */
public class BookRowMapper implements RowMapper<Book> {
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        BookExtractor extractor = new BookExtractor();
        return extractor.extractData(resultSet);
    }
}
