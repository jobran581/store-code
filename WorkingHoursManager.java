package finalproject;

public class WorkingHoursManager extends Worker {

    // Constructor: Initializes a working hours manager with worker credentials.
    public WorkingHoursManager(String name, String phone, int workerId,
            double salaryPerHour, String username, String password)
            throws StoreException {

        super(name, phone, workerId, salaryPerHour, username, password);
    }

    // Returns the role as "Working Hours Manager".
    public String getRole() {
        return "Working Hours Manager";
    }

    // Executes the working hours manager's work (manages worker schedules and
    // hours).
    public void work() {
        System.out.println(name + " manages worker schedules and hours.");
    }

}
