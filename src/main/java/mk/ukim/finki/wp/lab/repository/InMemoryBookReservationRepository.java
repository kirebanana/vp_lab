package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {

    @Override
    public BookReservation save(BookReservation reservation) {
        // Remove existing with same name (prevents duplicates)
        DataHolder.reservations.removeIf(c -> c.getBookTitle().equals(reservation.getBookTitle()));
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}
