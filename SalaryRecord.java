package finalproject;

import java.io.*;

public class SalaryRecord implements Serializable {

    private Worker worker;
    private int hoursWorked;
    private double bonus;
    private double totalSalary;

    // Constructor: Creates salary record by calculating total from hours, rate, and
    // bonus.
    public SalaryRecord(Worker worker, int hoursWorked, double bonus)
            throws StoreException {

        if (worker == null)
            throw new StoreException("Worker cannot be null");

        if (hoursWorked < 0)
            throw new StoreException("Hours cannot be negative");

        if (bonus < 0)
            throw new StoreException("Bonus cannot be negative");

        this.worker = worker;
        this.hoursWorked = hoursWorked;
        this.bonus = bonus;
        this.totalSalary = worker.getSalaryPerHour() * hoursWorked + bonus;
    }

    // Returns a formatted salary report with worker details, hours, rate, bonus,
    // and total.
    public String toString() {
        return "Worker: " + worker.getName()
                + "\nRole: " + worker.getRole()
                + "\nHours: " + hoursWorked
                + "\nSalary per hour: " + worker.getSalaryPerHour()
                + "\nBonus: " + bonus
                + "\nTotal salary: " + totalSalary;
    }
}
