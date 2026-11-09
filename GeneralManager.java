package finalproject;

public class GeneralManager extends Manager {

    // Constructor: Initializes a general manager with worker credentials.
    public GeneralManager(String name, String phone, int workerId,
            double salaryPerHour, String username, String password)
            throws StoreException {
        super(name, phone, workerId, salaryPerHour, username, password);
    }

    // Returns the role as "General Manager".
    public String getRole() {
        return "General Manager";
    }

    // Executes the general manager's work (manages everything).
    public void work() {
        System.out.println(name + " manages everything.");
    }
}
