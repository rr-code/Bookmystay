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
    public static void main(String[] args) {

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
    }
}