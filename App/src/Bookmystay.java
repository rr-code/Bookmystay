
pu// Version 3.0 - New Class for Inventory

import java.util.HashMap;
import java.util.Map;

// Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor - Initialize Inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initial Room Availability
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 2);
    }

    // Get Availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update Availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display Inventory
    public void displayInventory() {
        System.out.println("===== Centralized Room Inventory =====\n");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey());
            System.out.println("Available Rooms: " + entry.getValue());
            System.out.println("-----------------------------");
        }
    }
}


// Version 3.1 - Refactored Main Class

public class Bookmystay {

    public static void main(String[] args) {

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Display Initial Inventory
        inventory.displayInventory();

        // Example Update (simulate booking)
        System.out.println("\nUpdating availability after booking...\n");
        inventory.updateAvailability("Single Room", 8);

        // Display Updated Inventory
        inventory.displayInventory();

        System.out.println("Application Terminated.");
    }
}