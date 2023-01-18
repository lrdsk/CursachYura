package Front;

import Models.Employee.Employee;
import Models.Employee.EmployeeService;
import Models.Shop.ShopService;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class AddEmployee{
    private JPanel panel1;
    private JButton AddButton;
    private CompletableFuture<Void> completableFuture = new CompletableFuture<>();
    private EmployeeService employeeService;
    private ShopService shopService;
    private int idEmployeeInt;
    private int salaryEmployeeInt;
    private String nameEmployeeString;
    private JFrame frame;
    private JTextField ID;
    private JTextField Salary;
    private JTextField Name;

    public AddEmployee(EmployeeService employeeService, ShopService shopService) throws SQLException {
        this.employeeService = employeeService;
        this.shopService = shopService;
        frame=new JFrame("Add employee");
        frame.setPreferredSize(new Dimension(500, 300));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        EmployeeForm employeeForm=new EmployeeForm(this.employeeService, this.shopService);
        AddButton.addActionListener(employeeForm);
        AddButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salaryEmployeeInt = Integer.parseInt(Salary.getText());
                    idEmployeeInt = Integer.parseInt(ID.getText());
                    nameEmployeeString = Name.getText();
                    Employee employee = new Employee(idEmployeeInt,nameEmployeeString,salaryEmployeeInt);
                    employeeService.createEmployee(employee).whenComplete((unused, throwable) -> {
                        if(throwable != null){
                            throwable.printStackTrace();
                            completableFuture.completeExceptionally(throwable);
                            return;
                        }
                        frame.dispose();
                        completableFuture.complete(null);
                    });

                }catch (NumberFormatException | JsonProcessingException exception) {
                    System.out.println("Неправильно введено значение ID или Salary." +
                            "\nПоля должны быть заполнены в формате целых чисел.");
                }
                frame.setVisible(false);
            }
        });
    }
    public CompletableFuture<Void> getCompletableFuture() {
        return completableFuture;
    }
}
