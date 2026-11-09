package finalproject;

import java.io.*;

public abstract class Worker extends user implements Serializable {
    protected int workerId;
    protected double salaryPerHour;
    protected String username;
    protected String encryptedPassword;

    // Constructor: Initializes worker with credentials and salary, validates all
    // inputs.
    public Worker(String name, String phone, int workerId, double salaryPerHour, String username, String password)
            throws StoreException {

        super(name, phone);

        if (workerId <= 0)
            throw new StoreException("Worker ID must be positive");

        if (salaryPerHour <= 0)
            throw new StoreException("Salary must be positive");

        if (username == null || username.trim().isEmpty())
            throw new StoreException("Username cannot be empty");

        if (password == null || password.trim().isEmpty())
            throw new StoreException("Password cannot be empty");

        this.workerId = workerId;
        this.salaryPerHour = salaryPerHour;
        this.username = username;
        this.encryptedPassword = CryptoUtil.encrypt(password);
    }

    // Returns the worker's ID.
    public int getWorkerId() {
        return workerId;
    }

    // Returns the worker's hourly salary rate.
    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    // Authenticates worker by checking username and decrypted password match.
    public boolean login(String username, String password) {
        return this.username.equals(username) && CryptoUtil.matches(password, encryptedPassword);
    }

    // Abstract method: Executes the worker's job (implemented by subclasses).
    public abstract void work();
}