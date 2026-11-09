package finalproject;

public class ProductManager extends Worker {

    // Constructor: Initializes a product manager with worker credentials.
    public ProductManager(String name, String phone, int workerId,
            double salaryPerHour, String username, String password)
            throws StoreException {

        super(name, phone, workerId, salaryPerHour, username, password);
    }

    // Returns the role as "Product Manager".
    public String getRole() {
        return "Product Manager";
    }

    // Executes the product manager's work (manages products and categories).
    public void work() {
        System.out.println(name + " manages products and categories.");
    }
}
