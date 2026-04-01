 UC6-ReservationConfirmationANDRoomAllocation

// Version 5.0

 UC4-RoomSearch
 main
import java.util.*;

class AddOnService {
    private String name;
    private double price;

    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services for Reservation ID: " + reservationId);
            return;
        }

        System.out.println("Add-On Services for Reservation ID: " + reservationId);
        for (AddOnService s : services) {
            System.out.println(s.getName() + " - ₹" + s.getPrice());
        }
    }

    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);
        double total = 0;

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getPrice();
            }
        }

        return total;
    }
}

public class Bookmystay {
 UC6-ReservationConfirmationANDRoomAllocation
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservation1 = "SR101";
        String reservation2 = "DR201";

        manager.addService(reservation1, new AddOnService("Breakfast", 500));
        manager.addService(reservation1, new AddOnService("Airport Pickup", 1200));

 UC7-Add-OnServiceSelection
        manager.addService(reservation2, new AddOnService("Extra Bed", 800));

        manager.displayServices(reservation1);
        System.out.println("Total Add-On Cost: ₹" + manager.calculateTotalCost(reservation1));


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

        manager.displayServices(reservation2);
        System.out.println("Total Add-On Cost: ₹" + manager.calculateTotalCost(reservation2));

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
    }
}