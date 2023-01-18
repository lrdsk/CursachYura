package Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFormatter {
    private EmployeeFormatter() {
    }

    public static List<String> formatEmployee(List<Employee> employeeList) {
        final StringBuilder sb = new StringBuilder();
        List<String> currentListEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            sb
                    .append(employee.getNameEmployee())
                    .append("     ")
                    .append(employee.getId())
                    .append("     ")
                    .append(employee.getSalaryEmployee());
            currentListEmployees.add(sb.toString());
        }
        return currentListEmployees;
        /*final StringBuilder sb = new StringBuilder();
        sb
                .append("Name = '")
                .append(e.getNameEmployee())
                .append('\'')
                .append(", Salary = ")
                .append(e.getSalaryEmployee())
                .append(", id = ")
                .append(e.getId());
        return sb.toString();
           }
         */

    }
}