package Models.Employee;

import java.util.HashMap;

public class Employee {
   private String nameEmployee;
   private int salaryEmployee;
   private int id;

   public Employee(int id, String nameEmployee, int salaryEmployee) {
        this.nameEmployee = nameEmployee;
        this.salaryEmployee = salaryEmployee;
        this.id = id;
   }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public int getSalaryEmployee() {
        return salaryEmployee;
    }

    public void setSalaryEmployee(int salaryEmployee) {
        this.salaryEmployee = salaryEmployee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
       return EmployeeFormatter.formatEmployee(this);
    }
}
