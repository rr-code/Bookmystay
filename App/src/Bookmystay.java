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
    public static void main(String[] args) {

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

        System.out.println("\nSystem state restored successfully.");
    }
}