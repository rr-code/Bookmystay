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
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservation1 = "SR101";
        String reservation2 = "DR201";

        manager.addService(reservation1, new AddOnService("Breakfast", 500));
        manager.addService(reservation1, new AddOnService("Airport Pickup", 1200));

        manager.addService(reservation2, new AddOnService("Extra Bed", 800));

        manager.displayServices(reservation1);
        System.out.println("Total Add-On Cost: ₹" + manager.calculateTotalCost(reservation1));

        System.out.println();

        manager.displayServices(reservation2);
        System.out.println("Total Add-On Cost: ₹" + manager.calculateTotalCost(reservation2));

        System.out.println("\nCore booking and inventory remain unchanged.");
    }
}