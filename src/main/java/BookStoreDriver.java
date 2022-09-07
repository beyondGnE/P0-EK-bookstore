import com.fasterxml.jackson.databind.ObjectMapper;
import controllerlayer.BookService;
import io.javalin.Javalin;
import modellayer.Book;
import modellayer.BookStore;

public class BookStoreDriver {
    public static void main(String[] args) {

//        BookStoreApp bookstoreApp = new BookStoreApp();
        BookService bs = new BookService();
        BookStore b = new BookStore();
        Javalin app = Javalin.create().start(9000);
//
        app.get("/books/", ctx -> { ctx.json(bs.readRecords(b).toString());});
        app.get("/books/byISBN/{isbn}", ctx ->
        {
            ctx.json(bs.readRecords(b, ctx.pathParam("isbn")).toString());
        });
        // We're passing in the expected behavior to the web request into the method get
        // ctx.result returns a http response
        // ctx.pathParam grabs the param surrounded by curly braces in the app port

//        app.get("books/byISBN/{isbn}", ctx -> {
//            ctx.json(bs.readRecords(b, ctx.));
//        });

//        app.post("books", ctx -> {
//            ObjectMapper mapper = new ObjectMapper();
//            Book newBook = mapper.readValue(ctx.body(), Book.class);
//            bs.createRecord(b, newBook.getIsbn());
//        });
        // Need to convert JSON to sql format.

    }
}
