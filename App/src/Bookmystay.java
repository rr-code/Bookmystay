 UC6-ReservationConfirmationANDRoomAllocation



 UC4-RoomSearch
 main
import java.util.*;

class Reservation {
    private final String reservationId;
    private final String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private final Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void increment(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void display() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

class BookingHistory {
    private final Map<String, Reservation> confirmed;

    public BookingHistory() {
        confirmed = new HashMap<>();
    }

    public void add(Reservation r) {
        confirmed.put(r.getReservationId(), r);
    }

    public Reservation get(String id) {
        return confirmed.get(id);
    }

    public void remove(String id) {
        confirmed.remove(id);
    }

    public boolean exists(String id) {
        return confirmed.containsKey(id);
    }
}

class CancellationService {
    private final RoomInventory inventory;
    private final BookingHistory history;
    private final Stack<String> rollbackStack;

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
        rollbackStack = new Stack<>();
    }

    public void cancel(String reservationId) {
        if (!history.exists(reservationId)) {
            System.out.println("Cancellation Failed: Invalid Reservation ID " + reservationId);
            return;
        }

        Reservation r = history.get(reservationId);

        rollbackStack.push(reservationId);

        inventory.increment(r.getRoomType());

        history.remove(reservationId);

        System.out.println("Cancellation Successful for Reservation ID: " + reservationId);
    }

    public void displayRollbackStack() {
        System.out.println("\nRollback Stack:");
        for (String id : rollbackStack) {
            System.out.println(id);
        }
    }
}

public class Bookmystay {
 UC6-ReservationConfirmationANDRoomAllocation
    public static void main(String[] args) {

 UC9-ErrorHandlingValidation
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("SR101", "Single Room");
        Reservation r2 = new Reservation("DR201", "Double Room");

        history.add(r1);
        history.add(r2);

        CancellationService service = new CancellationService(inventory, history);

        service.cancel("SR101");
        service.cancel("INVALID");

        inventory.display();
        service.displayRollbackStack();

 UC10-BookingCancellation
        System.out.println("\nSystem state restored successfully.");

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
 main
    }
}