package finalproject;

import java.io.*;

public class Category implements Serializable {
    private String name;

    // Constructor: Initializes a product category with validation that name is not
    // empty.
    public Category(String name) throws StoreException {
        if (name == null || name.trim().isEmpty())
            throw new StoreException("Category name cannot be empty");

        this.name = name;
    }

    // Returns the name of this category.
    public String getName() {
        return name;
    }

    // Returns the category name as a string.
    public String toString() {
        return name;
    }
}
