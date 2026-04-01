import java.util.*;

class Reservation {
    private final String reservationId;
    private final String guestName;
    private final String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

class BookingHistory {
    private final List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("===== Booking History =====\n");

        for (Reservation r : reservations) {
            r.display();
        }
    }

    public void generateSummary(List<Reservation> reservations) {
        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : reservations) {
            String type = r.getRoomType();
            summary.put(type, summary.getOrDefault(type, 0) + 1);
        }

        System.out.println("\n===== Booking Summary =====\n");

        for (Map.Entry<String, Integer> entry : summary.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

public class Bookmystay {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("SR101", "Riya", "Single Room"));
        history.addReservation(new Reservation("DR201", "Amit", "Double Room"));
        history.addReservation(new Reservation("SR102", "Neha", "Single Room"));
        history.addReservation(new Reservation("SU301", "Rahul", "Suite Room"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getAllReservations());

        reportService.generateSummary(history.getAllReservations());

        System.out.println("\nReporting completed. No data modified.");
    }
}