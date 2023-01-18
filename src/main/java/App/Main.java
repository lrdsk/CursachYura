package App;

import Front.MainForm;
import Models.Employee.EmployeeService;
import Models.Employee.EmployeeServiceImpl;
import Models.Shop.ShopService;
import Models.Shop.ShopServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient client = new OkHttpClient();
        ShopService shopService = new ShopServiceImpl(objectMapper,client);
        EmployeeService employeeService = new EmployeeServiceImpl(objectMapper, client);

        new MainForm(employeeService, shopService);
    }
}