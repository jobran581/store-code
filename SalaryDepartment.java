package finalproject;

import java.io.*;
import java.util.ArrayList;

public class SalaryDepartment implements Serializable {

    private ArrayList<SalaryRecord> salaryRecords;

    // Constructor: Initializes the salary department with an empty records list.
    public SalaryDepartment() {
        salaryRecords = new ArrayList<SalaryRecord>();
    }

    // Calculates and records salary for a worker based on hours worked and bonus.
    public void calculateSalary(Worker worker, int hours, double bonus)
            throws StoreException {

        SalaryRecord record = new SalaryRecord(worker, hours, bonus);

        salaryRecords.add(record);
    }

    // Returns a formatted report of salary records, accessible only to managers.
    public String printSalaryRecords(Worker viewer)
            throws StoreException {

        if (!(viewer instanceof DepartmentManager)
                && !(viewer instanceof GeneralManager)) {
            throw new StoreException(
                    "Only managers can view salary records");
        }

        if (salaryRecords.isEmpty())
            return "No salary records";

        String result = "Salary Records\n";

        for (SalaryRecord r : salaryRecords) {
            result += r + "\n-------------------------------------------\n";
        }

        return result;
    }
}
