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
        return this.bookRepository.searchBooks(text, rating);
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {
        Author author = this.authorRepository.findById(authorId);
        Book book = new Book(null, title, genre, averageRating, author);
        return this.bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String title, String genre, Double averageRating, Long authorId) {
        Author author = this.authorRepository.findById(authorId);
        Book book = new Book(id, title, genre, averageRating, author);
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
