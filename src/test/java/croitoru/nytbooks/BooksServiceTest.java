package croitoru.nytbooks;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BooksServiceTest {

    BooksServiceFactory factory = new BooksServiceFactory();

    @Test
    public void getBookReview(){
        //given
        BooksService service = factory.newInstance();

        //when
        BooksFeed feed = service.getBookReview("1Q84").blockingGet();

        //then
        assertNotNull(feed);
        assertNotNull(feed.results);
        assertFalse(feed.results.isEmpty());


    }

}