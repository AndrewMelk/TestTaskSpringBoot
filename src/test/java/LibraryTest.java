import com.testTask.MyApplication;
import com.testTask.controllers.BookController;
import com.testTask.dao.BooksDao;
import com.testTask.dao.impl.BooksDaoImpl;
import com.testTask.domain.Book;
import com.testTask.service.BooksService;
import com.testTask.service.impl.BooksServiceImpl;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Andrew on 11.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.testTask.MyApplication.class)
@Import(MyApplication.class)
public class LibraryTest {

    BooksService serviceMock;
    BookController controller;
    HttpServletResponse response = new MockHttpServletResponse();
    private List<Book> books;
    private Book storeTestBook = new Book("Peace and War",1869 , "Leo Tolstoy");
    private Book testBook = new Book(48, "Clean Code", 2008,	"Robert C. Martin", "History");


    @Autowired
    private BooksService service;

    @Before
    public void setup(){
        serviceMock = EasyMock.createNiceMock(BooksService.class);
        controller = new BookController();
        controller.setBooksService(serviceMock);
    }
    @Test
    public void testGetBookById() throws IOException {

        EasyMock.expect(serviceMock.bookById(48)).andReturn(testBook);
        EasyMock.replay(serviceMock);

        controller.getBook(48,response);

        EasyMock.verify(serviceMock);

        books = service.getAllbooks();
        int tempID = books.get(books.size()-1).getId();
        testBook.setId(tempID);

        Assert.assertEquals(service.bookById(tempID).getAuthor(),"Leo Tolstoy");




    }



    @Test
    public void testStoreBook(){
        books = service.getAllbooks();
        service.storeBook(storeTestBook);

        Assert.assertEquals(books.size()+1,service.getAllbooks().size());
    }

    @Test
    public void testDeleteBook(){
        books = service.getAllbooks();
        int i = books.size()-1;
        service.deleteBook(books.get(i).getId());

        Assert.assertEquals(books.size()-1,service.getAllbooks().size());

    }

    @Test
    public void testUpdateBook(){
        books = service.getAllbooks();
        int index = books.size()-1;
        Book book = service.bookById(books.get(index).getId());

        book.setCategory("Classic");
        service.updateBook(book);

        Assert.assertEquals(book.getId(),service.bookById(book.getId()).getId());
        Assert.assertEquals(book.getTitle(),service.bookById(book.getId()).getTitle());
        Assert.assertEquals(book.getYear(),service.bookById(book.getId()).getYear());
        Assert.assertEquals(book.getAuthor(),service.bookById(book.getId()).getAuthor());
        Assert.assertEquals(book.getCategory(),service.bookById(book.getId()).getCategory());

    }

    @Test
    public void getBooksByCategory(){
        books = service.getBooksByCategory("Classic");
        System.out.println(books);

        Assert.assertEquals(1,books.size());
    }
}
