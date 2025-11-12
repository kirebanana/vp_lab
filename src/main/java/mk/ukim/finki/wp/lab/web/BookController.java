package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/books", "/"})
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String searchText,
                               @RequestParam(required = false) Double searchRating,
                               @RequestParam(required = false) String error,
                               Model model) {

        List<Book> books;
        if ((searchText != null && !searchText.isBlank()) || searchRating != null) {
            books = bookService.searchBooks(searchText == null ? "" : searchText,
                    searchRating == null ? 0.0 : searchRating);
        } else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("error", error);
        return "listBooks";
    }

    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", this.authorService.findAll());
        return "book-form";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Book book = this.bookService.findById(id);
        if (book == null) {
            return "redirect:/books?error=BookNotFound";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", this.authorService.findAll());
        return "book-form";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        this.bookService.save(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        this.bookService.edit(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }
}
