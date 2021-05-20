package croitoru.nytbooks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BooksApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BooksService service = new BooksServiceFactory().newInstance();
        BooksController controller = new BooksController(service);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/books_application.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        root.setStyle("-fx-background-color:#edd3cb;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-color: #ceaa31;"
                + "-fx-border-width: 5;");

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("NYT Book Reviews");
        stage.setScene(scene);
        stage.show();
    }
}