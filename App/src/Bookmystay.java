 UC12-DataPersistenceRecovery
import java.io.*;

 UC6-ReservationConfirmationANDRoomAllocation



 UC4-RoomSearch
 main
 main
import java.util.*;

class Reservation implements Serializable {
    private String reservationId;
    private String guestName;
    private String roomType;

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

    public String toString() {
        return reservationId + " | " + guestName + " | " + roomType;
    }
}

class RoomInventory implements Serializable {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void display() {
        System.out.println("Inventory:");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}

class BookingHistory implements Serializable {
    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    public void add(Reservation r) {
        reservations.add(r);
    }

    public List<Reservation> getAll() {
        return reservations;
    }

    public void display() {
        System.out.println("\nBooking History:");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }
}

class SystemState implements Serializable {
    RoomInventory inventory;
    BookingHistory history;

    public SystemState(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }
}

class PersistenceService {
    private static final String FILE_NAME = "system_state.ser";

    public void save(SystemState state) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(state);
            System.out.println("\nState saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state.");
        }
    }

    public SystemState load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("State loaded successfully.\n");
            return (SystemState) in.readObject();
        } catch (Exception e) {
            System.out.println("No valid saved state found. Starting fresh.\n");
            return null;
        }
    }
}

 UC12-DataPersistenceRecovery
public class Bookmystay{
    public static void main(String[] args) {

        PersistenceService persistence = new PersistenceService();

        SystemState state = persistence.load();

        RoomInventory inventory;
        BookingHistory history;

        if (state != null) {
            inventory = state.inventory;
            history = state.history;
        } else {
            inventory = new RoomInventory();
            history = new BookingHistory();

public class Bookmystay {
 UC6-ReservationConfirmationANDRoomAllocation
    public static void main(String[] args) {

 UC11-ConcurrentBookingSimulation
        BookingQueue queue = new BookingQueue();

 UC9-ErrorHandlingValidation
 main
        RoomInventory inventory = new RoomInventory();

        queue.add(new Reservation("Riya", "Single Room"));
        queue.add(new Reservation("Amit", "Single Room"));
        queue.add(new Reservation("Neha", "Single Room"));
        queue.add(new Reservation("Rahul", "Double Room"));
        queue.add(new Reservation("Priya", "Double Room"));

        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);
        BookingProcessor t3 = new BookingProcessor(queue, inventory);

        t1.setName("Thread-1");
        t2.setName("Thread-2");
        t3.setName("Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
 main
        }

        history.add(new Reservation("SR101", "Riya", "Single Room"));
        history.add(new Reservation("DR201", "Amit", "Double Room"));

        inventory.display();
        history.display();

        persistence.save(new SystemState(inventory, history));

 UC12-DataPersistenceRecovery
        System.out.println("\nSystem recovered and running.");

 UC11-ConcurrentBookingSimulation
        System.out.println("\nConcurrent booking completed safely.");

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
 main
 main
    }
}