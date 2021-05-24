package croitoru.nytbooks;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.scene.text.Text;
import javafx.util.Callback;

public class BooksController {
    @FXML
    Label review;
    @FXML
    TextField bookTitle;
    @FXML
    TableColumn url;
    @FXML
    TableColumn title;
    @FXML
    TableColumn author;
    @FXML
    TableColumn summary;
    @FXML
    TableView<BooksFeed.Result> table;

    BooksService service;

    public BooksController(BooksService service) {
        this.service = service;
    }

    @FXML
    public void initialize() {
        url.setCellValueFactory(new PropertyValueFactory<BooksFeed.Result, Hyperlink>("url"));
        title.setCellValueFactory(new PropertyValueFactory<BooksFeed.Result, String>("book_title"));
        author.setCellValueFactory(new PropertyValueFactory<BooksFeed.Result, String>("book_author"));
        summary.setCellValueFactory(new PropertyValueFactory<BooksFeed.Result, String>("summary"));
    }

    public void onSubmit(ActionEvent actionEvent) {
        String title = bookTitle.getText();
        Disposable disposable = service.getBookReview(title)
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::getNytReview, this::onError);
    }

    private void getNytReview(BooksFeed feed) {
        Platform.runLater(() -> {
            table.getItems().setAll(feed.results);
            table.refresh();
        });
        //making the URL a clickable hyperlink that says 'click here'
        url.setCellFactory(tc -> {
            TableCell<BooksFeed.Result, Hyperlink> urlCell = new TableCell<BooksFeed.Result, Hyperlink>() {
                public void updateItem(Hyperlink url, boolean empty) {
                    super.updateItem(url, empty);
                    setText(empty ? null : "Review URL \n click here");
                }
            };
            urlCell.setOnMouseClicked(e -> {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(feed.results.get(0).url));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            return urlCell;
        });

        //to wrap the text of the summary so it fits in 1 cell
        summary.setCellFactory((Callback<TableColumn<BooksFeed.Result, String>, TableCell<BooksFeed.Result, String>>) param -> {
            TableCell<BooksFeed.Result, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(cell.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
