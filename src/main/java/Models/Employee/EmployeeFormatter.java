package Models.Employee;

public class EmployeeFormatter {
    private EmployeeFormatter() {
    }

    public static String formatEmployee(Employee e) {
        final StringBuilder sb = new StringBuilder();
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
}
