package Models.Employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EmployeeServiceImpl implements EmployeeService {
    private ObjectMapper objectMapper;
    private OkHttpClient client;

    public EmployeeServiceImpl(ObjectMapper objectMapper, OkHttpClient client) {
        this.objectMapper = objectMapper;
        this.client = client;
    }

    @Override
    public CompletableFuture<Void> createEmployee(Employee employee) throws JsonProcessingException {
        CompletableFuture<Void> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/employees")
                .header("Content-Type", "application/json")
                .post(RequestBody.create(objectMapper.writeValueAsString(new Employee(employee.getId(),
                        employee.getNameEmployee(),
                        employee.getSalaryEmployee())).getBytes())).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(!response.isSuccessful()){
                    future.completeExceptionally(new RuntimeException("request failed"));
                }else{
                    future.complete(null);
                }
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<Employee> getEmployee(int id) {
        CompletableFuture<Employee> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/employees/" + id)
                .get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                future.complete(objectMapper.readValue(json,Employee.class));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<List<Employee>> getAllEmployees() {
        CompletableFuture<List<Employee>> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/employees")
                .get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }


            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                future.complete(objectMapper.readValue(json,List.class));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<Void> deleteEmployee(int id) throws JsonProcessingException {
        CompletableFuture<Void> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/employees/" + id)
                .header("Content-Type", "application/json")
                .delete(RequestBody.create(objectMapper.writeValueAsString(new Employee(2,"ff",0)).getBytes())).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(!response.isSuccessful()){
                    System.out.println(call);
                    System.out.println(response);
                    future.completeExceptionally(new RuntimeException("request failed"));
                }else {
                    future.complete(null);
                }
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<Void> redactEmployee(int id, String name, int salary) throws JsonProcessingException {
        CompletableFuture<Void> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/employees/" + id)
                .header("Content-Type", "application/json")
                .patch(RequestBody.create(objectMapper.writeValueAsString(new Employee(id,name,salary)).getBytes())).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }


            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if(!response.isSuccessful()){
                    System.out.println(call);
                    System.out.println(response);
                    future.completeExceptionally(new RuntimeException("request failed"));
                }else {
                    future.complete(null);
                }
            }
        });
        return future;
    }
}
