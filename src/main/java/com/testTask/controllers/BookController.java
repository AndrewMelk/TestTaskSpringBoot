package com.testTask.controllers;

import com.testTask.domain.Book;
import com.testTask.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrew on 05.07.2017.
 */
@RestController()
@RequestMapping("/library")
public class BookController {

    Logger logger = Logger.getLogger(this.toString());
    @Autowired
    BooksService booksService;

    public void setBooksService(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("books_library");
    }

    @GetMapping("book/{id}")
    @ResponseBody
    public Book getBook(@PathVariable int id,
                        HttpServletResponse response) throws IOException {
        if(booksService.bookById(id)==null){
            response.sendError(404, "Book with id = " + id +" not found");
            return null;
        }else {
            return booksService.bookById(id);
        }
    }

    @GetMapping(value= "books")
    @ResponseBody
    public List<Book> getBooks() {
        logger.log(Level.SEVERE, booksService.getAllbooks().toString());
        return booksService.getAllbooks();
    }

    @GetMapping(value = "/editForm/{id}")
    public ModelAndView editForm(@PathVariable("id") int id,
                                 ModelAndView mav){

        mav.setViewName("edit_book");
        mav.addObject("book", booksService.bookById(id));

        return mav;
    }

    @PutMapping(value = "/book")
    @ResponseBody
    public Book updateBook(@RequestBody Book book) {
        logger.log(Level.SEVERE, book.toString());
        booksService.updateBook(book);
        return book;
    }

    @GetMapping(value = "/storeBookForm")
    public ModelAndView storeBookForm(@ModelAttribute("book") Book book){


        return new ModelAndView("add_book_form","book", new Book());
    }
    // этот метод будет методом POST отдавать объект MyDataObject
    @PostMapping(value = "/book")
    @ResponseBody
    public Book storeBook(@RequestBody Book book, HttpServletResponse response) throws IOException {
        if (book.getTitle().equals("")||book.getYear()==0||book.getAuthor().equals("")){
            response.sendError(400, "Mandatory fields Title, Year, Author must be filled");

            return null;
        }else {
            booksService.storeBook(book);
            return book;

        }
    }

    @DeleteMapping(value= "book/{id}")
    @ResponseBody
    public void deleteBook(@PathVariable int id) {
        booksService.deleteBook(id);
    }

    @GetMapping("/books/{category}")
    public List<Book> getBooksByCategory(@PathVariable("category") String category,
                                         HttpServletResponse response) throws IOException {
        List<Book> bookList = new ArrayList<>();
        bookList = booksService.getBooksByCategory(category);
        if (bookList==null){
            response.sendError(404,"Books not found in " + category + " category");
            return null;
        }else {
            return bookList;
        }
    }

}
