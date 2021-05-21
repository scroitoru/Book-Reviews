package croitoru.nytbooks;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BooksController {
    @FXML
    Label review;
    @FXML
    TextField bookTitle;

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
        Platform.runLater(() -> {
            review.setText(booksFeed.results.toString());
        });
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
