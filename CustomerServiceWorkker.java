package finalproject;

public class CustomerServiceWorkker extends Worker {

    // Constructor: Initializes a customer service worker with worker credentials.
    public CustomerServiceWorkker(String name, String phone, int workerId,
            double salaryPerHour, String username, String password)
            throws StoreException {
        super(name, phone, workerId, salaryPerHour, username, password);
    }

    // Returns the role as "Customer Service Worker".
    public String getRole() {
        return "Customer Service Worker";
    }

    // Executes the customer service worker's work (helps customers).
    public void work() {
        System.out.println(name + " helps customers.");
    }
}
