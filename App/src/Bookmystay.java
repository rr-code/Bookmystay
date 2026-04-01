 UC6-ReservationConfirmationANDRoomAllocation

// Version 5.0

 UC4-RoomSearch
 main
import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    private final String guestName;
    private final String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private final Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }
        return inventory.get(roomType);
    }

    public void decrement(String roomType) throws InvalidBookingException {
        int available = getAvailability(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
        inventory.put(roomType, available - 1);
    }
}

class BookingValidator {

    public void validate(Reservation reservation, RoomInventory inventory) throws InvalidBookingException {

        if (reservation.getGuestName() == null || reservation.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        inventory.getAvailability(reservation.getRoomType());
    }
}

class BookingService {
    private final RoomInventory inventory;
    private final BookingValidator validator;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        this.validator = new BookingValidator();
    }

    public void processBooking(Reservation reservation) {
        try {
            validator.validate(reservation, inventory);
            inventory.decrement(reservation.getRoomType());

            System.out.println("Booking Successful for " + reservation.getGuestName() +
                    " | Room Type: " + reservation.getRoomType());

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}

public class Bookmystay {
 UC6-ReservationConfirmationANDRoomAllocation
    public static void main(String[] args) {

 UC9-ErrorHandlingValidation
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        Reservation r1 = new Reservation("Riya", "Single Room");
        Reservation r2 = new Reservation("", "Double Room");
        Reservation r3 = new Reservation("Amit", "Suite Room");
        Reservation r4 = new Reservation("Neha", "Luxury Room");

        service.processBooking(r1);
        service.processBooking(r2);
        service.processBooking(r3);
        service.processBooking(r4);

        System.out.println("\nSystem running safely after handling errors.");

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
main
    }
}