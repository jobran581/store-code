package finalproject;

import java.io.*;
import java.util.ArrayList;

public class Store implements Serializable {
  private ArrayList<Product> products;
  private ArrayList<Customer> customers;
  private ArrayList<Worker> workers;
  private ArrayList<Category> categories;
  private ArrayList<Chat> chats;
  private SalaryDepartment salaryDepartment;
  private ArrayList<Review> reviews;
  private ArrayList<Order> orders;

  // Constructor: Initializes empty store with all collection lists and salary
  // department.
  public Store() {
    products = new ArrayList<Product>();
    customers = new ArrayList<Customer>();
    workers = new ArrayList<Worker>();
    categories = new ArrayList<Category>();
    chats = new ArrayList<Chat>();
    reviews = new ArrayList<Review>();
    orders = new ArrayList<Order>();
    salaryDepartment = new SalaryDepartment();
  }

  // Adds a review to the store's review collection.
  public void addReview(Review review) {
    reviews.add(review);
  }

  // Returns a formatted string of all reviews in the store.
  public String printReviews() {
    if (reviews.isEmpty())
      return "No reviews";

    String result = "REVIEWS\n";

    for (Review r : reviews) {
      result += r + "\n-------------------------\n";
    }

    return result;
  }

  // Adds an order to the store's order collection.
  public void addOrder(Order order) {
    orders.add(order);
  }

  // Returns the list of all customers in the store.
  public ArrayList<Customer> getCustomers() {
    return customers;
  }

  // Returns the list of all workers in the store.
  public ArrayList<Worker> getWorkers() {
    return workers;
  }

  // Authenticates and returns a worker by username and password, null if not
  // found.
  public Worker loginWorker(String username, String password) {
    // Iterate through all workers to find matching credentials
    for (Worker w : workers) {
      if (w.login(username, password))
        return w; // Return worker immediately upon match
    }
    return null; // No matching worker found
  }

  // Authenticates and returns a customer by username and password, null if not
  // found.
  public Customer loginCustomer(String username, String password) {
    // Iterate through all customers to find matching credentials
    for (Customer c : customers) {
      if (c.login(username, password))
        return c; // Return customer immediately upon match
    }
    return null; // No matching customer found
  }

  // Serializes and saves the entire store to a file.
  public void saveToFile(String fileName) throws StoreException {
    try {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));

      out.writeObject(this);
      out.close();

    } catch (Exception e) {
      throw new StoreException("Could not save file");
    }
  }

  // Deserializes and loads a store from a file.
  public static Store loadFromFile(String fileName) throws StoreException {
    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));

      Store store = (Store) in.readObject();
      // Returns formatted string of products with quantity less than 5.
      in.close();

      return store;

    } catch (Exception e) {
      throw new StoreException("Could not load file");
    }
  }

  public String printLowStockProducts() {
    String result = " Low stock almost gone\n";

    boolean found = false;

    for (Product p : products) {
      if (p.getQuantity() < 5) {
        result += p + "\n";
        found = true;
        // Adds a product to the store's product collection.
      }
    }

    if (!found)
      // Adds a customer to the store's customer collection.
      return "No low stock products";

    return result;
  }
  // Adds a worker to the store's worker collection.

  public void addProduct(Product product) {
    products.add(product);
  }
  // Adds a category to the store's category collection.

  public void addCustomer(Customer customer) {
    customers.add(customer);
  }
  // Searches and returns a product by name (case-insensitive), null if not found.

  public void addWorker(Worker worker) {
    workers.add(worker);
  }

  public void addCategory(Category category) {
    categories.add(category);
  }
  // Searches and returns a customer by name (case-insensitive), null if not
  // found.

  // Searches and returns a product by name (case-insensitive), null if not found.
  public Product searchProduct(String name) {
    // Iterate through all products using case-insensitive comparison
    for (Product p : products) {
      if (p.getName().equalsIgnoreCase(name))
        return p; // Return product immediately upon match
    }
    return null; // Product not found
  }
  // Deletes a product from the store by name.

  // Searches and returns a customer by name (case-insensitive), null if not
  // found.
  public Customer searchCustomer(String name) {
    // Iterate through all customers using case-insensitive comparison
    for (Customer c : customers) {
      if (c.getName().equalsIgnoreCase(name))
        return c; // Return customer immediately upon match
    }
    return null; // Customer not found
  }

  // Sorts products by price in ascending order using bubble sort.
  // Deletes a product from the store by name.
  public void deleteProduct(String name) throws StoreException {
    // Find product in store
    Product p = searchProduct(name);

    if (p == null)
      throw new StoreException("Product not found");

    // Remove the found product
    products.remove(p);
  }

  public void sortProductsByPrice() {
    for (int i = 0; i < products.size() - 1; i++) {
      for (int j = 0; j < products.size() - 1 - i; j++) {
        // Returns a formatted string of all products in the store.
        if (products.get(j).getPrice() > products.get(j + 1).getPrice()) {
          Product temp = products.get(j);
          products.set(j, products.get(j + 1));
          products.set(j + 1, temp);
        }
      }
    }
  }

  public String printAllProducts() {
    if (products.isEmpty())
      return "No products";

    // Returns a formatted string of all workers in the store.
    String result = "";

    for (Product p : products) {
      result += p + "\n";
    }

    return result;
  }

  public String printAllWorkers() {
    if (workers.isEmpty())
      return "No workers";

    // Returns a formatted string of all customers in the store.
    String result = "";

    for (Worker w : workers) {
      result += w + "\n";
    }

    return result;
  }

  public String printAllCustomers() {
    if (customers.isEmpty())
      return "No customers";

    // Creates a duplicate of a product by name.
    String result = "";

    // Sorts products by price in ascending order (duplicate of
    // sortProductsByPrice).
    for (Customer c : customers) {
      result += c + "\n";
    }

    return result;
  }

  // Returns the list of all products in the store.
  // Duplicates a product with 'COPY' appended to the name.
  public void cloneProduct(String name) throws StoreException {
    // Find the original product to clone
    Product p = searchProduct(name);

    if (p == null)
      throw new StoreException("Product not found");

    // Add the cloned product (with COPY in name) to the store
    products.add(p.clone());
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  // Sorts products by price in ascending order using bubble sort.
  public void sortProductsByPriceAscending() {

    for (int i = 0; i < products.size() - 1; i++) {
      // Outer loop: number of passes through the list
      for (int j = 0; j < products.size() - 1 - i; j++) {
        // Inner loop: compare adjacent elements and swap if needed
        if (products.get(j).getPrice() > products.get(j + 1).getPrice()) {
          // Swap products if current is more expensive than next
          Product temp = products.get(j);
          products.set(j, products.get(j + 1));
          products.set(j + 1, temp);
        }
      }
    }
  }

  // Sorts products alphabetically by name in descending order (Z to A).
  public void sortProductsByNameDescending() {
    for (int i = 0; i < products.size() - 1; i++) {
      for (int j = 0; j < products.size() - 1 - i; j++) {
        if (products.get(j).getName().compareToIgnoreCase(products.get(j + 1).getName()) < 0) {
          Product temp = products.get(j);
          products.set(j, products.get(j + 1));
          products.set(j + 1, temp);
        }
      }
    }
  }

  // Sorts products alphabetically by name in ascending order (A to Z).
  public void sortProductsByNameAscending() {
    for (int i = 0; i < products.size() - 1; i++) {
      for (int j = 0; j < products.size() - 1 - i; j++) {
        if (products.get(j).getName().compareToIgnoreCase(products.get(j + 1).getName()) > 0) {
          Product temp = products.get(j);
          products.set(j, products.get(j + 1));
          products.set(j + 1, temp);
        }
      }
    }
  }

  // Sorts products by price in descending order (high to low).
  public void sortProductsByPriceDescending() {
    for (int i = 0; i < products.size() - 1; i++) {
      for (int j = 0; j < products.size() - 1 - i; j++) {
        if (products.get(j).getPrice() < products.get(j + 1).getPrice()) {
          Product temp = products.get(j);
          products.set(j, products.get(j + 1));
          products.set(j + 1, temp);
        }
      }
    }
  }

  // Sorts products by category name in ascending order (A to Z).
  public void sortProductsByCategoryAscending() {

    for (int i = 0; i < products.size() - 1; i++) {
      // Outer loop: number of passes through the list
      for (int j = 0; j < products.size() - 1 - i; j++) {
        // Inner loop: compare adjacent product categories
        if (products.get(j).getCategory().getName()
            .compareToIgnoreCase(products.get(j + 1).getCategory().getName()) > 0) {
          // Swap if current category comes after next category alphabetically
          Product temp = products.get(j);
          products.set(j, products.get(j + 1));
          products.set(j + 1, temp);
        }
      }
    }
  }

  // Sorts products by category name in descending order (Z to A).
  public void sortProductsByCategoryDescending() {
    for (int i = 0; i < products.size() - 1; i++) {
      for (int j = 0; j < products.size() - 1 - i; j++) {
        if (products.get(j).getCategory().getName()
            .compareToIgnoreCase(products.get(j + 1).getCategory().getName()) < 0) {
          Product temp = products.get(j);
          products.set(j, products.get(j + 1));
          products.set(j + 1, temp);
        }
      }
    }
  }

  // Adds a chat to the store's chat collection.
  public void addChat(Chat chat) {
    chats.add(chat);
  }

  // Returns the list of all chats in the store.
  public ArrayList<Chat> getChats() {
    return chats;
  }

  // Finds and returns the first manager in the workers list, null if none found.
  public Manager getFirstManager() {
    for (Worker w : workers) {
      if (w instanceof Manager) {
        return (Manager) w;
      }
    }
    return null;
  }

  // Finds and returns the first customer service worker, null if none found.
  public CustomerServiceWorkker getFirstCustomerServiceWorker() {
    for (Worker w : workers) {
      if (w instanceof CustomerServiceWorkker) {
        return (CustomerServiceWorkker) w;
      }
    }
    return null;
  }

  // Returns the salary department instance for managing payroll.
  public SalaryDepartment getSalaryDepartment() {
    return salaryDepartment;
  }

  // Searches and returns a worker by name (case-insensitive), null if not found.
  public Worker searchWorkerByName(String name) {
    for (Worker w : workers) {
      if (w.getName().equalsIgnoreCase(name))
        return w;
    }
    return null;
  }

  // Finds and returns the first general manager in the workers list, null if none
  // found.
  public GeneralManager getFirstGeneralManager() {
    for (Worker w : workers) {
      if (w instanceof GeneralManager)
        return (GeneralManager) w;
    }
    return null;
  }

  public String printFinancialReport(user viewer) throws StoreException {

    if (!(viewer instanceof GeneralManager)) {
      throw new StoreException("Only general manager can view financial report");
    }

    if (products.isEmpty())
      return "No products";

    String result = "FINANCIAL REPORT\n";

    double totalRevenue = 0;
    double totalCost = 0;
    double totalProfit = 0;

    for (Product p : products) {
      result += p.financialInfo();
      result += "\n----------------\n";

      totalRevenue += p.getRevenue();
      totalCost += p.getTotalCost();
      totalProfit += p.getProfit();
    }

    result += "\nTOTAL REVENUE: " + totalRevenue;
    result += "\nTOTAL COST: " + totalCost;
    result += "\nTOTAL PROFIT: " + totalProfit;

    return result;
  }

}
