package croitoru.nytbooks;

import javafx.scene.control.Hyperlink;

import java.util.List;

public class BooksFeed {
    List<Result> results;

    public static class Result {
        String url;
        String book_title;
        String book_author;
        String summary;

        public Hyperlink getUrl() {
            Hyperlink hyperlink = new Hyperlink(url);
            return hyperlink;
        }

        public String getBook_title() {
            return book_title;
        }

        public String getBook_author() {
            return book_author;
        }

        public String getSummary() {
            return summary;
        }
    }
}
