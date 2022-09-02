package modellayer;

import java.util.List;

// The repo, I guess
// The main core of the app; everything else attaches to it.
public class BookStore {

    public List<Book> getBookInventory(IConnection ic) {
        ic.connectToDB();

        return null;
    }


}
