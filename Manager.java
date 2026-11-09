package finalproject;

public class Manager extends Worker {

    // Constructor: Initializes a manager with worker credentials.
    public Manager(String name, String phone, int workerId,
            double salaryPerHour, String username, String password)
            throws StoreException {
        super(name, phone, workerId, salaryPerHour, username, password);
    }

    // Returns the role as "Manager".
    public String getRole() {
        return "Manager";
    }

    // Executes the manager's work (manages the store).
    public void work() {
        System.out.println(name + " manages the store.");
    }
}
