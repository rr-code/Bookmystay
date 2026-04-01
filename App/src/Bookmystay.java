// Version 5.0

import java.util.*;

// -------------------- Reservation Class --------------------
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

    public void display() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// -------------------- Booking Request Queue --------------------
class BookingRequestQueue {

    private final Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request (enqueue)
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    // View all requests (FIFO order)
    public void displayQueue() {
        System.out.println("\n===== Booking Request Queue (FIFO) =====\n");

        if (queue.isEmpty()) {
            System.out.println("No pending booking requests.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }

    // Peek next request (without removing)
    public Reservation peekNext() {
        return queue.peek();
    }
}

// -------------------- Main Class --------------------
public class Bookmystay {

    public static void main(String[] args) {

        // Initialize Queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate Guest Requests
        bookingQueue.addRequest(new Reservation("Riya", "Single Room"));
        bookingQueue.addRequest(new Reservation("Amit", "Double Room"));
        bookingQueue.addRequest(new Reservation("Neha", "Suite Room"));

        // Display Queue (FIFO Order)
        bookingQueue.displayQueue();

        // Show Next Request (without removing)
        Reservation next = bookingQueue.peekNext();
        if (next != null) {
            System.out.println("\nNext to be processed:");
            next.display();
        }

        System.out.println("\nNo rooms allocated yet. Only requests stored.");
    }
}