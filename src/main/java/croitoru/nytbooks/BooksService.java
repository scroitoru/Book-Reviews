package croitoru.nytbooks;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksService {
    @GET("http://api.nytimes.com/svc/books/v3/reviews.json?api-key=TIgBU3Q4jrlODHnKqkJwGVwayLdZTZrK")
    Single<BooksFeed>getBookReview(@Query("title")String title);
}
