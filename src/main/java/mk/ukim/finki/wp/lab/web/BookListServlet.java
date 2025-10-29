package mk.ukim.finki.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookListServlet", urlPatterns = "")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String searchText = req.getParameter("searchText") == null ? "" : req.getParameter("searchText");
        String searchRatingStr = req.getParameter("searchRating");

        if (searchText == null) {
            searchText = "";
        }

        Double searchRating;
        try {
            searchRating = Double.parseDouble(searchRatingStr);
        } catch (NumberFormatException | NullPointerException e) {
            searchRating = 0.0;
        }

        context.setVariable("books", this.bookService.searchBooks(searchText, searchRating));
        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }
}
