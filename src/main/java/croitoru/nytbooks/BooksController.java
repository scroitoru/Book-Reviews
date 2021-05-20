package croitoru.nytbooks;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class BooksController {
    @FXML
    Label reviews;
    @FXML
    TextField bookTitle;
    @FXML
    Button button;
    BooksService service;

    public BooksController(BooksService service){
        this.service = service;
    }

    public void onSubmit(ActionEvent actionEvent){
        String title = bookTitle.getText();
        Disposable disposable = service.getBookReview(title)
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::getNytReview, this::onError);
    }

    private void getNytReview(BooksFeed booksFeed) {
        reviews.setText(booksFeed.results.toString());
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
