package croitoru.nytbooks;

import javafx.scene.control.TableColumn;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BooksControllerTest {

    @Test
    public void initialize(){
        //given
        BooksService service = mock(BooksService.class);
        BooksController controller = new BooksController(service);

        //when

        //then
    }

}