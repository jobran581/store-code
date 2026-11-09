package finalproject;

public class DepartmentManager extends Manager {

    private String departmentName;

    // Constructor: Initializes department manager with department name validation.
    public DepartmentManager(String name, String phone, int workerId, double salaryPerHour, String username,
            String password, String departmentName)
            throws StoreException {

        super(name, phone, workerId, salaryPerHour, username, password);

        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new StoreException("Department name cannot be empty");
        }

        this.departmentName = departmentName;
    }

    // Returns the name of the department this manager supervises.
    public String getDepartmentName() {
        return departmentName;
    }

    // Returns the role including the department name.
    public String getRole() {
        return "Department Manager of " + departmentName;
    }

    // Executes the department manager's work (manages the assigned department).
    public void work() {
        System.out.println(name + " manages the " + departmentName + " department.");
    }
}
