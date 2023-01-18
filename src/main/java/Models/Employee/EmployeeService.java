package Models.Employee;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeService {

    CompletableFuture<Void> createEmployee(Employee employee) throws JsonProcessingException;

    CompletableFuture<Employee> getEmployee(int id);

    CompletableFuture<List<Employee>> getAllEmployees();

    CompletableFuture<Void> deleteEmployee(int id) throws JsonProcessingException;

    CompletableFuture<Void> redactEmployee(int id, String name, int salary) throws JsonProcessingException;
}
