package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(text, rating);    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {
        Author author = this.authorRepository.findById(authorId).orElse(null);
        Book book = new Book(title, genre, averageRating, author);
        return this.bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String title, String genre, Double averageRating, Long authorId) {
        Book book = bookRepository.findById(id).orElseThrow();
        Author author = authorRepository.findById(authorId).orElse(null);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
    @Override
    public List<Book> listByAuthor(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }
}
