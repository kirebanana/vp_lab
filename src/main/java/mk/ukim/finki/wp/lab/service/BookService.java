package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<mk.ukim.finki.wp.lab.model.Book> searchBooks(String text, Double rating);
}
