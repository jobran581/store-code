package finalproject;

import javax.swing.JOptionPane;

public class StoreApp {

    static Store store = new Store();

    static user currentUser = null;
    // null means Guest

    // Main entry point: Initializes demo data and displays appropriate menu based
    // on user role.
    public static void main(String[] args) {
        addDemoData();

        while (true) {

            if (currentUser == null) {
                guestMenu();
            } else if (currentUser instanceof Customer) {
                customerMenu();
            } else if (currentUser instanceof CustomerServiceWorkker) {
                customerServiceMenu();
            } else if (currentUser instanceof ProductManager) {
                productManagerMenu();
            } else if (currentUser instanceof InventoryManager) {
                inventoryManagerMenu();
            } else if (currentUser instanceof GeneralManager) {
                generalManagerMenu();
            } else if (currentUser instanceof Manager) {
                managerMenu();
            } else {
                show("Unknown user type");
                currentUser = null;
            }
        }
    }

    // Populates the store with demo categories, products, customers, workers,
    // reviews, orders, chats, and salary records.
    public static void addDemoData() {

        try {
            // ==================== CATEGORIES ====================
            // Create sample product categories
            Category phones = new Category("Phones");
            Category laptops = new Category("Laptops");
            Category accessories = new Category("Accessories");
            Category gaming = new Category("Gaming");
            Category audio = new Category("Audio");

            // Add all categories to the store
            store.addCategory(phones);
            store.addCategory(laptops);
            store.addCategory(accessories);
            store.addCategory(gaming);
            store.addCategory(audio);

            // ==================== PRODUCTS ====================
            // Add products with name, price, cost, quantity, and category
            store.addProduct(new Product("iPhone 15 Pro", 5200, 3500, 12, phones));
            store.addProduct(new Product("Samsung S24 Ultra", 4900, 3700, 7, phones));
            store.addProduct(new Product("Xiaomi Redmi", 1200, 700, 20, phones));

            store.addProduct(new Product("Gaming Laptop RTX", 7800, 5000, 3, laptops));
            store.addProduct(new Product("MacBook Pro", 9500, 4000, 6, laptops));
            store.addProduct(new Product("Office Laptop", 2600, 2000, 10, laptops));

            store.addProduct(new Product("Wireless Mouse", 120, 50, 25, accessories));
            store.addProduct(new Product("Mechanical Keyboard", 350, 100, 15, accessories));
            store.addProduct(new Product("USB-C Charger", 90, 5, 40, accessories));

            store.addProduct(new Product("PS5 Controller", 280, 130, 4, gaming));
            store.addProduct(new Product("Gaming Headset", 450, 100, 8, gaming));

            store.addProduct(new Product("AirPods Pro", 950, 400, 9, audio));
            store.addProduct(new Product("Bluetooth Speaker", 300, 100, 13, audio));

            // ==================== CUSTOMERS ====================
            // Create registered customers with credentials
            Customer c1 = new Customer("Ali", "0501111111", "ali", "1234");
            Customer c2 = new Customer("Sara", "0502222222", "sara", "1234");
            Customer c3 = new Customer("John", "0503333333", "john", "1234");
            Customer c4 = new Customer("Maya", "0504444444", "maya", "1234");
            Customer c5 = new Customer("David", "0505555555", "david", "1234");

            // Add all customers to the store
            store.addCustomer(c1);
            store.addCustomer(c2);
            store.addCustomer(c3);
            store.addCustomer(c4);
            store.addCustomer(c5);

            // ==================== SAVED CARDS ====================
            // Save payment cards for selected customers
            c1.saveCard(new PaymentCard("Ali", "1234567812341111"));
            c2.saveCard(new PaymentCard("Sara", "9999888877776666"));

            // ==================== WORKERS ====================
            // Create various worker types with their roles and credentials
            store.addWorker(new CustomerServiceWorkker("Ahmad", "0507777777", 1, 40, "service1", "1234"));
            store.addWorker(new CustomerServiceWorkker("Lina", "0508888888", 2, 42, "service2", "1234"));
            store.addWorker(new InventoryManager("Omar", "0509999999", 3, 45, "inventory", "1234"));
            store.addWorker(new ProductManager("Khaled", "0511111111", 4, 60, "product", "1234"));
            store.addWorker(new WorkingHoursManager("Noa", "0512222222", 5, 55, "hours", "1234"));
            store.addWorker(new Manager("Rami", "0513333333", 6, 75, "manager", "1234"));
            store.addWorker(new DepartmentManager("Yousef", "0514444444", 7, 85, "depmanager", "1234", "Products"));
            store.addWorker(new GeneralManager("Boss", "0515555555", 8, 120, "boss", "1234"));

            // ==================== REVIEWS ====================
            // Add sample reviews from customers
            store.addReview(new Review(c1, store.searchProduct("iPhone 15 Pro"), 5, "Amazing phone"));
            store.addReview(new Review(c2, store.searchProduct("Gaming Laptop RTX"), 4, "Very strong laptop"));
            store.addReview(new Review(c3, store.searchProduct("AirPods Pro"), 5, "Best audio quality"));

            // ==================== TEST ORDER ====================
            // Create a sample order with multiple items
            Cart cart = new Cart();
            cart.addItem(store.searchProduct("Wireless Mouse"), 2);
            cart.addItem(store.searchProduct("Mechanical Keyboard"), 1);

            Order order = new Order(c1, cart);
            order.setStatus(OrderStatus.PAID); // Mark order as paid
            c1.addOrder(order);
            store.addOrder(order);

            // ==================== CHAT TEST ====================
            // Create a sample chat interaction with manager escalation
            CustomerServiceWorkker service = store.getFirstCustomerServiceWorker();
            Chat chat = new Chat(c1, service);
            chat.sendCustomerMessage("My order arrived damaged");
            chat.sendWorkerMessage("We are checking the issue");
            chat.forwardToManager(store.getFirstManager(), "Possible refund");
            chat.requestRefund(120);
            store.addChat(chat);

            // ==================== SALARY TEST ====================
            // Record sample salary calculations for different workers
            store.getSalaryDepartment().calculateSalary(service, 180, 500);
            store.getSalaryDepartment().calculateSalary(store.searchWorkerByName("Khaled"), 200, 1200);
            store.getSalaryDepartment().calculateSalary(store.searchWorkerByName("Boss"), 220, 5000);

        } catch (StoreException e) {
            show(e.getMessage());
        }
    }

    // GUEST MENU

    // Displays guest menu with options to browse, search, buy, sign up, or login.
    public static void guestMenu() {
        int choice = readInt(
                "===== STORE - GUEST =====\n" +
                        "1. View products\n" +
                        "2. Search product\n" +
                        "3. Buy as guest\n" +
                        "4. Sign up\n" +
                        "5. Login\n" +
                        "6. Exit\n\n" +
                        "Choose:");

        try {
            switch (choice) {
                case 1:
                    show(store.printAllProducts());
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    buyAsGuest();
                    break;
                case 4:
                    signUp();
                    break;
                case 5:
                    login();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    show("Invalid choice");
            }
        } catch (StoreException e) {
            show("Error: " + e.getMessage());
        }
    }

    // CUSTOMER MENU

    // Displays customer menu with options for browsing, shopping, history, chat,
    // and reviews.
    public static void customerMenu() {
        Customer customer = (Customer) currentUser;

        int choice = readInt(
                "===== CUSTOMER MENU =====\n" +
                        "Logged in as: " + customer.getName() + "\n\n" +
                        "1. View products\n" +
                        "2. Search product\n" +
                        "3. Buy product\n" +
                        "4. View purchase history\n" +
                        "5. Open support chat\n" +
                        "6. Add product review\n" +
                        "7. View reviews\n" +
                        "8. Logout\n\n" +
                        "Choose:");

        try {
            switch (choice) {
                case 1:
                    show(store.printAllProducts());
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    buyAsCustomer(customer);
                    break;
                case 4:
                    showPurchaseHistory(customer);
                    break;
                case 5:
                    openSupportChat(customer);
                    break;
                case 6:
                    addReview(customer);
                    break;

                case 7:
                    show(store.printReviews());
                    break;

                case 8:
                    currentUser = null;
                    break;
                default:
                    show("Invalid choice");
            }
        } catch (StoreException e) {
            show("Error: " + e.getMessage());
        }
    }

    // CUSTOMER SERVICE MENU

    // Displays customer service worker menu with product view, chat, and refund
    // options.
    public static void customerServiceMenu() {
        int choice = readInt(
                "===== CUSTOMER SERVICE MENU =====\n" +
                        "1. View all products\n" +
                        "2. Open chat with customer\n" +
                        "3. Forward last chat to manager\n" +
                        "4. Request refund from manager\n" +
                        "5. Logout\n\n" +
                        "Choose:");

        try {
            switch (choice) {
                case 1:
                    show(store.printAllProducts());
                    break;
                case 2:
                    openSupportChatByWorker();
                    break;
                case 3:
                    forwardLastChatToManager();
                    break;
                case 4:
                    requestRefund();
                    break;
                case 5:
                    currentUser = null;
                    break;
                default:
                    show("Invalid choice");
            }
        } catch (StoreException e) {
            show("Error: " + e.getMessage());
        }
    }

    // PRODUCT MANAGER MENU

    // Displays product manager menu with product CRUD operations and discounts.
    public static void productManagerMenu() {
        int choice = readInt(
                "===== PRODUCT MANAGER MENU =====\n" +
                        "1. Add product\n" +
                        "2. Delete product\n" +
                        "3. Search product\n" +
                        "4. Print all products\n" +
                        "5. Sort products\n" +
                        "6. Clone product\n" +
                        "7. Apply discount\n" +
                        "8. Logout\n\n" +
                        "Choose:");

        try {
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    show(store.printAllProducts());
                    break;
                case 5:
                    sortProducts();
                    break;
                case 6:
                    cloneProduct();
                    break;
                case 7:
                    applyDiscount();
                    break;
                case 8:
                    currentUser = null;
                    break;
                default:
                    show("Invalid choice");
            }
        } catch (StoreException e) {
            show("Error: " + e.getMessage());
        }
    }

    // INVENTORY MANAGER MENU

    // Displays inventory manager menu for viewing and managing product quantities.
    public static void inventoryManagerMenu() {
        int choice = readInt(
                "===== INVENTORY MANAGER MENU =====\n" +
                        "1. View products\n" +
                        "2. Add quantity to product\n" +
                        "3. Search product\n" +
                        "4. Sort products\n" +
                        "5. View low stock products\n" +
                        "6. Logout\n\n" +
                        "Choose:");

        try {
            switch (choice) {
                case 1:
                    show(store.printAllProducts());
                    break;
                case 2:
                    addQuantityToProduct();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    sortProducts();
                    break;
                case 5:
                    show(store.printLowStockProducts());
                    break;

                case 6:
                    currentUser = null;
                    break;
                default:
                    show("Invalid choice");
            }
        } catch (StoreException e) {
            show("Error: " + e.getMessage());
        }
    }

    // MANAGER MENU

    // Displays manager menu with product/customer/worker viewing and refund
    // approval.
    public static void managerMenu() {
        int choice = readInt(
                "===== MANAGER MENU =====\n" +
                        "1. View products\n" +
                        "2. View customers\n" +
                        "3. View workers\n" +
                        "4. View all chats\n" +
                        "5. Approve full refund\n" +
                        "6. Approve half refund\n" +
                        "7. Logout\n\n" +
                        "Choose:");

        try {
            switch (choice) {
                case 1:
                    show(store.printAllProducts());
                    break;
                case 2:
                    show(store.printAllCustomers());
                    break;
                case 3:
                    show(store.printAllWorkers());
                    break;
                case 4:
                    managerViewAllChats();
                    break;
                case 5:
                    approveRefund();
                    break;
                case 6:
                    approveHalfRefund();
                    break;
                case 7:
                    currentUser = null;
                    break;
                default:
                    show("Invalid choice");
            }
        } catch (StoreException e) {
            show("Error: " + e.getMessage());
        }
    }

    // GENERAL MANAGER MENU

    // Displays general manager menu with all store management and reporting
    // features.
    public static void generalManagerMenu() {
        int choice = readInt(
                "===== GENERAL MANAGER MENU =====\n" +
                        "1. View products\n" +
                        "2. View customers\n" +
                        "3. View workers\n" +
                        "4. View all chats\n" +
                        "5. Approve full refund\n" +
                        "6. Approve half refund\n" +
                        "7. Add worker\n" +
                        "8. Calculate salary\n" +
                        "9. View salary records\n" +
                        "10. View financial report\n" +
                        "11. Save store\n" +
                        "12. Load store\n" +
                        "13. Logout\n\n" +
                        "Choose:");

        try {
            switch (choice) {
                case 1:
                    show(store.printAllProducts());
                    break;
                case 2:
                    show(store.printAllCustomers());
                    break;
                case 3:
                    show(store.printAllWorkers());
                    break;
                case 4:
                    managerViewAllChats();
                    break;
                case 5:
                    approveRefund();
                    break;
                case 6:
                    approveHalfRefund();
                    break;
                case 7:
                    addWorker();
                    break;
                case 8:
                    calculateSalary();
                    break;
                case 9:
                    viewSalaryRecords();
                    break;
                case 10:
                    show(store.printFinancialReport(currentUser));
                    break;

                case 11:
                    saveStore();
                    break;

                case 12:
                    loadStore();
                    break;

                case 13:
                    currentUser = null;
                    break;
                default:
                    show("Invalid choice");
            }
            // Handles user login for both customers and workers with credential validation.
        } catch (StoreException e) {
            show("Error: " + e.getMessage());
        }
    }

    // LOGIN / SIGNUP

    public static void login() throws StoreException {
        int type = readInt("Login as:\n" + "1. Customer\n" + "2. Worker\n" + "Choose:");
        String username = readString("Username:");
        String password = readString("Password:");

        if (type == 1) {
            Customer customer = store.loginCustomer(username, password);

            if (customer == null)
                throw new StoreException("Wrong customer username/password");

            currentUser = customer;
            show("Customer login successful");

        } else if (type == 2) {
            Worker worker = store.loginWorker(username, password);

            if (worker == null)
                throw new StoreException("Wrong worker username/password");

            currentUser = worker;
            // Handles new customer registration with username and password.
            show("Worker login successful as " + worker.getRole());

        } else {
            throw new StoreException("Invalid login type");
        }
    }

    public static void signUp() throws StoreException {
        String name = readString("Name:");
        String phone = readString("Phone:");
        String username = readString("Username:");
        String password = readString("Password:");

        Customer customer = new Customer(name, phone, username, password);
        store.addCustomer(customer);

        // Creates and adds a new product to the store.
        currentUser = customer;

        show("Signup successful. You are now logged in.");
    }

    // PRODUCTS

    public static void addProduct() throws StoreException {
        String name = readString("Product name:");
        double price = readDouble("Selling price:");
        double cost = readDouble("Product cost:");
        int quantity = readInt("Product quantity:");
        String categoryName = readString("Category name:");

        Category category = new Category(categoryName);
        Product product = new Product(name, price, cost, quantity, category);
        // Removes a product from the store by name.

        store.addCategory(category);
        store.addProduct(product);

        show("Product added");
    }
    // Displays details of a product by name.

    public static void deleteProduct() throws StoreException {
        String name = readString("Product name to delete:");
        store.deleteProduct(name);
        show("Product deleted");
    }

    public static void searchProduct() {
        String name = readString("Product name to search:");

        Product product = store.searchProduct(name);
        // Duplicates a product with 'COPY' appended to the name.

        if (product == null)
            show("Product not found");
        else
            show(product.toString());
    }
    // Sorts products by price, name, or category in ascending or descending order.

    public static void cloneProduct() throws StoreException {
        String name = readString("Product name to clone:");
        store.cloneProduct(name);
        show("Product cloned");
    }

    public static void sortProducts() throws StoreException {
        int sortBy = readInt(
                "Sort by:\n" +
                        "1. Price\n" +
                        "2. Name\n" +
                        "3. Category");

        int direction = readInt(
                "Direction:\n" +
                        "1. Up / Ascending / Small to Big / A to Z\n" +
                        "2. Down / Descending / Big to Small / Z to A");

        if (sortBy == 1 && direction == 1) {
            store.sortProductsByPriceAscending();
            show("Sorted by price: small to big");
        } else if (sortBy == 1 && direction == 2) {
            store.sortProductsByPriceDescending();
            show("Sorted by price: big to small");
        } else if (sortBy == 2 && direction == 1) {
            store.sortProductsByNameAscending();
            show("Sorted by name: A to Z");
        } else if (sortBy == 2 && direction == 2) {
            store.sortProductsByNameDescending();
            show("Sorted by name: Z to A");
        } else if (sortBy == 3 && direction == 1) {
            store.sortProductsByCategoryAscending();
            show("Sorted by category: A to Z");
        } else if (sortBy == 3 && direction == 2) {
            // Applies a discount percentage to a product's price.
            store.sortProductsByCategoryDescending();
            show("Sorted by category: Z to A");
        } else {
            throw new StoreException("Invalid sort option");
        }
    }

    // Applies a discount percentage to a product's price.
    public static void applyDiscount() throws StoreException {
        // Get product and validate it exists
        String name = readString("Product name:");
        Product product = store.searchProduct(name);

        if (product == null)
            throw new StoreException("Product not found");

        // Get discount percentage and apply to product
        double percent = readDouble("Discount percent:");
        product.applyDiscount(percent);

        show("Discount applied");
    }

    // Increases a product's stock quantity.
    public static void addQuantityToProduct() throws StoreException {
        // Get product and validate it exists
        String name = readString("Product name:");
        Product product = store.searchProduct(name);

        if (product == null)
            throw new StoreException("Product not found");

        // Get amount to add and update product quantity
        int amount = readInt("Amount to add:");
        product.addQuantity(amount);

        show("Quantity updated");
    }

    // PURCHASE

    public static void buyAsGuest() throws StoreException {
        String name = readString("Guest name:");
        String phone = readString("Phone:");

        Customer guest = new Customer(name, phone);
        store.addCustomer(guest);

        buyAsCustomer(guest);
    }

    public static void buyAsCustomer(Customer customer) throws StoreException {
        Cart cart = new Cart();

        while (true) {
            String productName = readString("Product name:");
            Product product = store.searchProduct(productName);

            if (product == null)
                throw new StoreException("Product not found");

            int quantity = readInt("Quantity:");
            cart.addItem(product, quantity);

            int again = readInt("Add another product?\n1. Yes\n2. No");

            if (again == 2)
                break;
        }

        show(cart.toString());

        int payType = readInt(
                "Choose payment:\n" +
                        "1. Credit card\n" +
                        "2. Cash\n" +
                        "3. PayPal");

        PaymentMethod payment;

        if (payType == 1) {
            String holder = readString("Card holder:");
            String number = readString("Card number:");
            PaymentCard card = new PaymentCard(holder, number);

            int save = readInt("Save card?\n1. Yes\n2. No");

            if (save == 1)
                customer.saveCard(card);

            payment = new CreditCardPayment(card);

        } else if (payType == 2) {
            payment = new CashPayment();

        } else if (payType == 3) {
            String email = readString("PayPal email:");
            payment = new PayPalPayment(email);

        } else {
            throw new StoreException("Invalid payment type");
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        // Displays a customer's order history.

        Order order = new Order(customer, cart);
        order.setStatus(OrderStatus.PAID);

        customer.addOrder(order);
        store.addOrder(order);

        String paymentInfo = payment.pay(order.getTotalPrice());

        Receipt receipt = new Receipt(order, paymentInfo);

        show("Purchase completed\n\n" + receipt);
    }

    public static void showPurchaseHistory(Customer customer) {
        if (customer.getPurchaseHistory().isEmpty()) {
            // Creates and adds a new worker of specified type to the store.
            show("No purchase history");
            return;
        }

        String result = "===== PURCHASE HISTORY =====\n";

        for (Order o : customer.getPurchaseHistory()) {
            result += o + "\n--------------------------------------\n";
        }

        show(result);
    }

    // WORKERS

    public static void addWorker() throws StoreException {
        int type = readInt(
                "Choose worker type:\n" +
                        "1. Customer Service\n" +
                        "2. Inventory Manager\n" +
                        "3. Product Manager\n" +
                        "4. Working Hours Manager\n" +
                        "5. Manager\n" +
                        "6. General Manager");

        String name = readString("Worker name:");
        String phone = readString("Worker phone:");
        int id = readInt("Worker ID:");
        double salary = readDouble("Salary per hour:");
        String username = readString("Username:");
        String password = readString("Password:");

        Worker worker;

        if (type == 1) {
            worker = new CustomerServiceWorkker(name, phone, id, salary, username, password);
        } else if (type == 2) {
            worker = new InventoryManager(name, phone, id, salary, username, password);
        } else if (type == 3) {
            worker = new ProductManager(name, phone, id, salary, username, password);
        } else if (type == 4) {
            // Initiates a support chat between logged-in customer and a service worker.
            worker = new WorkingHoursManager(name, phone, id, salary, username, password);
        } else if (type == 5) {
            worker = new Manager(name, phone, id, salary, username, password);
        } else if (type == 6) {
            worker = new GeneralManager(name, phone, id, salary, username, password);
        } else {
            throw new StoreException("Invalid worker type");
        }

        store.addWorker(worker);
        show("Worker added");
    }

    // ================= CHAT =================

    public static void openSupportChat(Customer customer) throws StoreException {
        CustomerServiceWorkker worker = store.getFirstCustomerServiceWorker();

        if (worker == null)
            throw new StoreException("No customer service worker found");

        Chat chat = new Chat(customer, worker);

        while (true) {
            int choice = readInt(
                    // Allows a service worker to open chat with a selected customer.
                    "1. Customer message\n" +
                            "2. End chat");

            if (choice == 2)
                break;

            String msg = readString("Message:");
            chat.sendCustomerMessage(msg);
        }

        store.addChat(chat);
        show(chat.printChatForCustomerOrWorker(customer.getName()));
    }

    public static void openSupportChatByWorker() throws StoreException {
        String customerName = readString("Customer name:");
        Customer customer = store.searchCustomer(customerName);

        if (customer == null)
            throw new StoreException("Customer not found");

        CustomerServiceWorkker worker = (CustomerServiceWorkker) currentUser;

        Chat chat = new Chat(customer, worker);

        while (true) {
            int choice = readInt(
                    "1. Customer message\n" +
                            "2. Customer service reply\n" +
                            "3. End chat");

            if (choice == 3)
                break;
            // Displays all chats for viewing by a manager.

            String msg = readString("Message:");

            if (choice == 1)
                chat.sendCustomerMessage(msg);
            else if (choice == 2)
                chat.sendWorkerMessage(msg);
            else
                show("Invalid choice");
        }

        store.addChat(chat);
        show(chat.printChatForCustomerOrWorker(worker.getName()));
    }

    public static void managerViewAllChats() throws StoreException {
        Manager manager = store.getFirstManager();

        if (manager == null)
            // Returns the most recent chat from the store's chat collection.
            throw new StoreException("No manager found");

        String result = "";

        for (Chat c : store.getChats()) {
            result += c.printChatForManager(manager);
            result += "\n----------------\n";
            // Forwards the last chat to a manager with a specified reason.
        }

        if (result.isEmpty())
            result = "No chats saved";

        show(result);
    }

    public static Chat getLastChat() throws StoreException {
        if (store.getChats().isEmpty())
            throw new StoreException("No chats found");

        return store.getChats().get(store.getChats().size() - 1);
    }

    // Submits a refund request in the last chat.
    public static void forwardLastChatToManager() throws StoreException {
        Chat chat = getLastChat();

        Manager manager = store.getFirstManager();

        if (manager == null)
            throw new StoreException("No manager found");

        String reason = readString("Reason for forwarding:");

        // Manager approves a full refund in the last chat.
        chat.forwardToManager(manager, reason);

        show("Chat forwarded to manager");
    }

    public static void requestRefund() throws StoreException {
        Chat chat = getLastChat();

        double amount = readDouble("Refund amount:");

        // Manager approves a half refund (50%) in the last chat.
        chat.requestRefund(amount);

        show("Refund request added to chat");
    }

    // Calculates and records salary for a worker with hours and bonus.
    public static void approveRefund() throws StoreException {
        Chat chat = getLastChat();

        Manager manager = store.getFirstManager();

        chat.approveRefund(manager);

        show("Full refund approved");
    }

    public static void approveHalfRefund() throws StoreException {
        Chat chat = getLastChat();

        Manager manager = store.getFirstManager();

        chat.approveHalfRefund(manager);

        show("Half refund approved");
    }

    // SALARY
    // Displays salary records for the logged-in manager.

    public static void calculateSalary() throws StoreException {
        GeneralManager generalManager = store.getFirstGeneralManager();

        if (generalManager == null)
            throw new StoreException("Only general manager can calculate salaries");

        String workerName = readString("Worker name:");

        Worker worker = store.searchWorkerByName(workerName);

        if (worker == null)
            throw new StoreException("Worker not found");

        int hours = readInt("Hours worked:");
        double bonus = readDouble("Bonus:");
        // Prompts user for integer input with error handling.

        store.getSalaryDepartment().calculateSalary(worker, hours, bonus);

        show("Salary calculated");
    }

    public static void viewSalaryRecords() throws StoreException {
        String viewerName = currentUser.getName();

        Worker viewer = store.searchWorkerByName(viewerName);

        if (viewer == null)
            throw new StoreException("Manager not found");

        String result =
                // Prompts user for decimal number input with error handling.
                store.getSalaryDepartment().printSalaryRecords(viewer);

        show(result);
    }

    // INPUT

    public static int readInt(String msg) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(msg);

                if (input == null)
                    return -1;

                // Prompts user for text input with non-empty validation.
                return Integer.parseInt(input);
            } catch (Exception e) {
                show("Please enter a valid integer");
            }
        }
    }

    public static double readDouble(String msg) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(msg);

                if (input == null)
                    return 0;
                // Displays a message dialog to the user.

                return Double.parseDouble(input);
            } catch (Exception e) {
                // Adds a product review from a customer.
                show("Please enter a valid number");
            }
        }
    }

    public static String readString(String msg) {
        while (true) {
            String input = JOptionPane.showInputDialog(msg);

            if (input == null)
                return "";

            if (!input.trim().isEmpty())
                return input;

            // Saves the store state to a data file for persistence.
            show("Input cannot be empty");
        }
    }

    public static void show(String msg) {
        // Loads a previously saved store from a data file.
        JOptionPane.showMessageDialog(null, msg);
    }

    public static void addReview(Customer customer) throws StoreException {
        String productName = readString("Product name:");
        Product product = store.searchProduct(productName);

        if (product == null)
            throw new StoreException("Product not found");

        int rating = readInt("Rating 1-5:");
        String comment = readString("Comment:");

        Review review = new Review(customer, product, rating, comment);
        store.addReview(review);

        show("Review added");
    }

    public static void saveStore() throws StoreException {
        store.saveToFile("store.dat");
        show("Store saved");
    }

    public static void loadStore() throws StoreException {
        store = Store.loadFromFile("store.dat");
        show("Store loaded");
    }

}
