package Models.Employee;

import Database.MyDateBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeService {
    private MyDateBase dateBase = null;

    public EmployeeService(MyDateBase dateBase) {
        this.dateBase = dateBase;
    }

    private Employee mapEmployee(ResultSet result) throws SQLException {
        int employee_id = result.getInt("employee_id");
        String employee_name = result.getString("employee_name");
        int salary = result.getInt("salary");
        return new Employee(employee_id, employee_name, salary);
    }
    public void printAllEmployee(List<Employee> employeeList){
        if(!employeeList.isEmpty()){
            for(Employee employee : employeeList){
                System.out.println(employee);
            }
        }
        else{
            System.out.println("Список работников пустой");
        }
    }

    public void setInfoAboutEmployeeToDB(Employee employee) throws SQLException {
        PreparedStatement statement = dateBase.getConnection().prepareStatement(
                "select exists (Select * from employee where employee_id = ?)");

        statement.setInt(1, employee.getId());
        ResultSet result = statement.executeQuery();

        result.next();
        if (!result.getBoolean("exists")) {
            statement = dateBase.getConnection().prepareStatement("INSERT INTO employee " +
                    "values (?, ?, ?)"
            );

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getNameEmployee());
            statement.setInt(3, employee.getSalaryEmployee());
            statement.executeUpdate();
        } else {
            System.out.println("Неверно, данный id уже занят в таблице.");
        }
    }

    public Employee getInfoAboutEmployeeFromDB(String name) throws SQLException {
        PreparedStatement statement = dateBase.getConnection().prepareStatement(
                "SELECT * FROM employee where employee_name = ? LIMIT 1"
        );
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        result.next();
        return this.mapEmployee(result);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        PreparedStatement statement = dateBase.getConnection().prepareStatement(
                "select count(*) from employee"
        );
        ResultSet result = statement.executeQuery();

        result.next();
            if (result.getInt("count") > 0) {
                statement = dateBase.getConnection().prepareStatement(
                        "SELECT * FROM employee");
                result = statement.executeQuery();

                List<Employee> resultList = new ArrayList<>();
                while (result.next()) {
                    resultList.add(this.mapEmployee(result));
                }
                return resultList;
            }
            else {
                System.out.println("Таблица пуста");
                return Collections.emptyList();
            }
    }

    public void deleteEmployeeByName(String name) throws SQLException {
        PreparedStatement statement = dateBase.getConnection().prepareStatement(
                "select exists (Select * from employee where employee_name = ?)"
        );
        statement.setString(1,name);
        ResultSet result = statement.executeQuery();
        //delete from employee where employee_name = ?
        result.next();
        if(result.getBoolean("exists")){
            System.out.println("Работник найден");
            statement = dateBase.getConnection().prepareStatement(
                    "delete from employee where employee_name = ?"
            );
            statement.setString(1,name);
            statement.executeUpdate();
        }
        else {
            System.out.println("Не найден работник с таким именем.");
        }
    }
}
