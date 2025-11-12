package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookReservationController {
    private final BookReservationService reservationService;

    public BookReservationController(BookReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/bookReservation")
    public String reserveBook(@RequestParam String bookTitle,
                              @RequestParam String numberOfCopies,
                              @RequestParam String readerName,
                              @RequestParam String readerAddress,
                              HttpServletRequest request,
                              Model model) {

        String clientIp = request.getRemoteAddr();

        int copies = Integer.parseInt(numberOfCopies);


        BookReservation reservation = this.reservationService
                .placeReservation(bookTitle, readerName, readerAddress, copies);

        model.addAttribute("reservation", reservation);
        model.addAttribute("ipAddress", clientIp);
        model.addAttribute("readerName", reservation.getReaderName());
        model.addAttribute("bookTitle", reservation.getBookTitle());
        model.addAttribute("numberOfCopies", reservation.getNumberOfCopies());

        return "reservationConfirmation";
    }
}
