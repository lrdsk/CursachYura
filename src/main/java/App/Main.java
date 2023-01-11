package App;

import Database.MyDateBase;
import Front.MainForm;
import Models.Employee.Employee;
import Models.Employee.EmployeeService;
import Models.Shop.Shop;
import Models.Shop.ShopService;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        MyDateBase dateBase = new MyDateBase();

        Shop shop = new Shop("Adidas", "Chern", "Wear", "Yura");
        Employee employee = new Employee(11, "Arturik", 5500);
        Employee employee1 = new Employee(11, "Arturik1", 55001);

        ShopService shopService = new ShopService(dateBase, shop);
        EmployeeService employeeService = new EmployeeService(dateBase);

        ArrayList<Employee> listEmployee = new ArrayList<>(employeeService.getAllEmployees());

        employeeService.setInfoAboutEmployeeToDB(employee);
        employeeService.setInfoAboutEmployeeToDB(employee1);

        shopService.setInfoAboutShopToBase(shop.getNameOfShop(), shop.getAddress(),
                shop.getSpecializationOfShop(), shop.getNameOfDirector());

        System.out.println(shopService.getInfoAboutShopFromBase());

        System.out.println(employeeService.getInfoAboutEmployeeFromDB("Arturik"));

        employeeService.printAllEmployee(listEmployee);
        //employeeService.deleteEmployeeByName("Arturik");
        new MainForm(employeeService);


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                dateBase.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}