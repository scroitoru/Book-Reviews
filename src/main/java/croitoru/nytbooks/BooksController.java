package croitoru.nytbooks;

public class BooksController {
    BooksService service;

    public BooksController(BooksService service){
        this.service = service;
    }
}
