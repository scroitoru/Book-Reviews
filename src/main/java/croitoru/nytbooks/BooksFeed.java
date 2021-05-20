package croitoru.nytbooks;

import java.util.Date;
import java.util.List;

public class BooksFeed {
    List<Results> results;

    public static class Results{
        String book_title;
        String book_author;
        String summary;

        public String getBook_author() {
            return book_author;
        }

        public String getBook_title() {
            return book_title;
        }

        public String getSummary() {
            return summary;
        }
    }
}
