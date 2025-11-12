package mk.ukim.finki.wp.lab.bootstrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    public static List<Author> authors = null;

    public static AtomicLong bookIdCounter = new AtomicLong(0);
    public static AtomicLong authorIdCounter = new AtomicLong(0);

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        authors = new ArrayList<>();

        Author a1 = new Author(authorIdCounter.getAndIncrement(), "John", "Doe", "USA", "Some biography");
        Author a2 = new Author(authorIdCounter.getAndIncrement(), "Anna", "Smith", "UK", "Some biography");
        Author a3 = new Author(authorIdCounter.getAndIncrement(), "Ivan", "Petrov", "MK", "Some biography");

        authors.add(a1);
        authors.add(a2);
        authors.add(a3);

        books.add(new Book(bookIdCounter.getAndIncrement(), "The Lord of the Rings", "Fantasy", 4.5, a1));
        books.add(new Book(bookIdCounter.getAndIncrement(), "1984", "Dystopian", 4.4, a2));
        books.add(new Book(bookIdCounter.getAndIncrement(), "To Kill a Mockingbird", "Fiction", 4.3, a3));
        books.add(new Book(bookIdCounter.getAndIncrement(), "The Great Gatsby", "Classic", 3.9, a1));
        books.add(new Book(bookIdCounter.getAndIncrement(), "Pride and Prejudice", "Romance", 4.2, a2));
        books.add(new Book(bookIdCounter.getAndIncrement(), "Dune", "Sci-Fi", 4.2, a3));
        books.add(new Book(bookIdCounter.getAndIncrement(), "The Catcher in the Rye", "Fiction", 3.8, a1));
        books.add(new Book(bookIdCounter.getAndIncrement(), "Brave New World", "Dystopian", 4.0, a2));
        books.add(new Book(bookIdCounter.getAndIncrement(), "The Hobbit", "Fantasy", 4.3, a3));
        books.add(new Book(bookIdCounter.getAndIncrement(), "Fahrenheit 451", "Dystopian", 4.1, a1));
    }

}
