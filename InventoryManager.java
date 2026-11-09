package finalproject;

public class InventoryManager extends Worker {

    // Constructor: Initializes an inventory manager with worker credentials.
    public InventoryManager(String name, String phone, int workerId,
            double salaryPerHour, String username, String password)
            throws StoreException {

        super(name, phone, workerId, salaryPerHour, username, password);
    }

    // Returns the role as "Inventory Manager".
    public String getRole() {
        return "Inventory Manager";
    }

    // Executes the inventory manager's work (manages inventory and stock).
    public void work() {
        System.out.println(name + " manages inventory and stock.");
    }
}
