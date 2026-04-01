 UC6-ReservationConfirmationANDRoomAllocation

// Version 5.0

 UC4-RoomSearch
 main
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
 UC6-ReservationConfirmationANDRoomAllocation
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

 UC8-BookingHistoryandReporting
        history.addReservation(new Reservation("SR101", "Riya", "Single Room"));
        history.addReservation(new Reservation("DR201", "Amit", "Double Room"));
        history.addReservation(new Reservation("SR102", "Neha", "Single Room"));
        history.addReservation(new Reservation("SU301", "Rahul", "Suite Room"));

 UC7-Add-OnServiceSelection
        manager.addService(reservation2, new AddOnService("Extra Bed", 800));
 main

        BookingReportService reportService = new BookingReportService();

 UC8-BookingHistoryandReporting
        reportService.displayAllBookings(history.getAllReservations());


// Version 2.0
main

// Abstract Class
abstract class Room {
    private int beds;
    private double size;
    private double price;

    // Constructor
    public Room(int beds, double size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    // Getters (Encapsulation)
    public int getBeds() {
        return beds;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    // Abstract Method
    public abstract String getRoomType();

    // Display Method
    public void displayDetails(int availability) {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price: ₹" + price);
        System.out.println("Available Rooms: " + availability);
        System.out.println("-----------------------------");
    }
}

// Single Room Class
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 200, 1500);
    }

    public String getRoomType() {
        return "Single Room";
    }
}

// Double Room Class
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 350, 2500);
    }

    public String getRoomType() {
        return "Double Room";
    }
}

// Suite Room Class
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 600, 5000);
    }

    public String getRoomType() {
        return "Suite Room";
    }
}

// Main Class
public class Bookmystay{
    public static void main(String[] args) {

 UC5-BookingRequest
        // Initialize Queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
 main

        queue.addRequest(new Reservation("Riya", "Single Room"));
        queue.addRequest(new Reservation("Amit", "Double Room"));
        queue.addRequest(new Reservation("Neha", "Single Room"));
        queue.addRequest(new Reservation("Rahul", "Suite Room"));
        queue.addRequest(new Reservation("Priya", "Single Room"));

UC6-ReservationConfirmationANDRoomAllocation
        RoomInventory inventory = new RoomInventory();

                        UC4-RoomSearch
        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();
 main

        // Display Queue (FIFO Order)
        bookingQueue.displayQueue();
 main
main

        System.out.println();
main

        reportService.generateSummary(history.getAllReservations());

 UC8-BookingHistoryandReporting
        System.out.println("\nReporting completed. No data modified.");

 UC7-Add-OnServiceSelection
        System.out.println("\nCore booking and inventory remain unchanged.");

 UC6-ReservationConfirmationANDRoomAllocation
        inventory.displayInventory();

 UC5-BookingRequest
        System.out.println("\nNo rooms allocated yet. Only requests stored.");

        System.out.println("Search Completed. No inventory modified.");

        // Static Availability Variables
        int singleRoomAvailability = 10;
        int doubleRoomAvailability = 5;
        int suiteRoomAvailability = 2;

        // Polymorphism (using Room reference)
        Room single = new SingleRoom();
        Room doub = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display Details
        System.out.println("===== Hotel Room Availability =====\n");

        single.displayDetails(singleRoomAvailability);
        doub.displayDetails(doubleRoomAvailability);
        suite.displayDetails(suiteRoomAvailability);

        System.out.println("Application Terminated.");
 main
> main
 main
 main
    }
}