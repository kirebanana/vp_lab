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
    public static List<BookReservation> reservations = null;

    public static AtomicLong bookIdCounter = new AtomicLong(0);
    public static AtomicLong authorIdCounter = new AtomicLong(0);

    @PostConstruct
    public void init() {
        reservations = new ArrayList<>();
    }

}
