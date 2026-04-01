import java.io.*;
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
        }

        history.add(new Reservation("SR101", "Riya", "Single Room"));
        history.add(new Reservation("DR201", "Amit", "Double Room"));

        inventory.display();
        history.display();

        persistence.save(new SystemState(inventory, history));

        System.out.println("\nSystem recovered and running.");
    }
}