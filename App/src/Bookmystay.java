import java.util.*;

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

class BookingRequestQueue {
    private final Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventory {
    private final HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

class BookingService {
    private final RoomInventory inventory;
    private final Set<String> allocatedRoomIds;
    private final HashMap<String, Set<String>> roomAllocations;
    private int idCounter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRoomIds = new HashSet<>();
        roomAllocations = new HashMap<>();
    }

    public void processBookings(BookingRequestQueue queue) {
        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();
            String type = r.getRoomType();

            if (inventory.getAvailability(type) > 0) {
                String roomId = generateRoomId(type);

                allocatedRoomIds.add(roomId);

                roomAllocations.putIfAbsent(type, new HashSet<>());
                roomAllocations.get(type).add(roomId);

                inventory.decrement(type);

                System.out.println("Booking Confirmed for " + r.getGuestName() +
                        " | Room Type: " + type +
                        " | Room ID: " + roomId);
            } else {
                System.out.println("Booking Failed for " + r.getGuestName() +
                        " | Room Type: " + type +
                        " (No Availability)");
            }
        }
    }

    private String generateRoomId(String type) {
        String id;
        do {
            id = type.substring(0, 2).toUpperCase() + idCounter++;
        } while (allocatedRoomIds.contains(id));
        return id;
    }
}

public class Bookmystay {
    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Riya", "Single Room"));
        queue.addRequest(new Reservation("Amit", "Double Room"));
        queue.addRequest(new Reservation("Neha", "Single Room"));
        queue.addRequest(new Reservation("Rahul", "Suite Room"));
        queue.addRequest(new Reservation("Priya", "Single Room"));

        RoomInventory inventory = new RoomInventory();

        BookingService service = new BookingService(inventory);

        service.processBookings(queue);

        inventory.displayInventory();
    }
}