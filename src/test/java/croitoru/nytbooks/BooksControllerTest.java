package croitoru.nytbooks;

import io.reactivex.rxjava3.core.Single;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BooksControllerTest {

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
    }

    @Test
    public void initialize() {
        //given
        BooksService service = mock(BooksService.class);
        BooksController controller = new BooksController(service);
        controller.url = mock(TableColumn.class);
        controller.title = mock(TableColumn.class);
        controller.author = mock(TableColumn.class);
        controller.summary = mock(TableColumn.class);

        //when
        controller.initialize();

        //then
        verify(controller.url).setCellValueFactory(any());
        verify(controller.title).setCellValueFactory(any());
        verify(controller.author).setCellValueFactory(any());
        verify(controller.summary).setCellValueFactory(any());
    }

    @Test
    public void onSubmit() {
        //given
        BooksService service = mock(BooksService.class);
        BooksController controller = new BooksController(service);
        controller.bookTitle = mock(TextField.class);
        doReturn("1Q84").when(controller.bookTitle).getText();
        doReturn(Single.never()).when(service).getBookReview("1Q84");

        //when
        controller.onSubmit(mock(ActionEvent.class));

        //then
        verify(controller.bookTitle).getText();
        verify(service).getBookReview("1Q84");
    }

    @Test
    public void getNytReview() {
        //given
        BooksFeed feed = mock(BooksFeed.class);
        BooksService service = mock(BooksService.class);
        BooksController controller = new BooksController(service);
        controller.table = mock(TableView.class);
        doReturn(any()).when(controller.table).getItems();
        //when
        controller.getNytReview(feed);

        //then
        verify(controller.table).getItems();
        verify(controller.table).refresh();
    }

}