package mk.ukim.finki.wp.lab.bootstrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        books.add(new Book("The Lord of the Rings", "Fantasy", 4.5));
        books.add(new Book("1984", "Dystopian", 4.4));
        books.add(new Book("To Kill a Mockingbird", "Fiction", 4.3));
        books.add(new Book("The Great Gatsby", "Classic", 3.9));
        books.add(new Book("Pride and Prejudice", "Romance", 4.2));
        books.add(new Book("Dune", "Sci-Fi", 4.2));
        books.add(new Book("The Catcher in the Rye", "Fiction", 3.8));
        books.add(new Book("Brave New World", "Dystopian", 4.0));
        books.add(new Book("The Hobbit", "Fantasy", 4.3));
        books.add(new Book("Fahrenheit 451", "Dystopian", 4.1));
    }

}
