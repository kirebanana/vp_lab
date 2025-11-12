package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }


    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(c -> c.getTitle().contains(text) &&
                        c.getAverageRating()>=rating)
                .toList();
    }

    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findFirst()
                .orElse(null);
    }


    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            // create
            book.setId(DataHolder.bookIdCounter.getAndIncrement());
            DataHolder.books.add(book);
        } else {
            Optional<Book> existing = DataHolder.books.stream()
                    .filter(b -> b.getId().equals(book.getId()))
                    .findFirst();
            if (existing.isPresent()) {
                Book b = existing.get();
                b.setTitle(book.getTitle());
                b.setGenre(book.getGenre());
                b.setAverageRating(book.getAverageRating());
                b.setAuthor(book.getAuthor());
            } else {
                DataHolder.books.add(book);
            }
        }
        return book;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.books.removeIf(b -> b.getId().equals(id));
    }
}
