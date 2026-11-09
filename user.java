package finalproject;

import java.io.*;

public abstract class user implements Serializable {
    protected String name;
    protected String phone;

    // Constructor: Initializes user with name and phone, validates non-empty
    // inputs.
    public user(String name, String phone) throws StoreException {
        if (name == null || name.trim().isEmpty())
            throw new StoreException("name cann't be empty");

        if (phone == null || phone.trim().isEmpty())
            throw new StoreException("phone cann't be empty");

        this.name = name;
        this.phone = phone;
    }

    // Returns the user's name.
    public String getName() {
        return name;
    }

    // Returns the user's phone number.
    public String getPhone() {
        return phone;
    }

    // Abstract method: Returns the user's role (implemented by subclasses).
    public abstract String getRole();

    // Returns a formatted string with user role, name, and phone.
    public String toString() {
        return getRole() + ": " + name + ", phone: " + phone;
    }
}
