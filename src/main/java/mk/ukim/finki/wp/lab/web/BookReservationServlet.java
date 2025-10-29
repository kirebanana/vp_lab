package mk.ukim.finki.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookReservationService reservationService;

    public BookReservationServlet(SpringTemplateEngine springTemplateEngine, BookReservationService reservationService) {
        this.springTemplateEngine = springTemplateEngine;
        this.reservationService = reservationService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookTitle = req.getParameter("bookTitle");
        String numberOfCopies = req.getParameter("numberOfCopies");
        String readerAddress = req.getParameter("readerAddress");
        String readerName = req.getParameter("readerName");
        String ipAddress = req.getRemoteAddr();

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("ipAddress", ipAddress);
        context.setVariable("readerName", readerName);
        context.setVariable("readerAddress", readerAddress);
        context.setVariable("bookTitle", bookTitle);
        context.setVariable("numberOfCopies", numberOfCopies);
        this.reservationService.placeReservation(bookTitle, readerName, readerAddress, Integer.parseInt(numberOfCopies));


        this.springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }
}

