package Front;

import Models.Employee.Employee;
import Models.Employee.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

public class RedactEmployee {
    private CompletableFuture<Void> completableFuture = new CompletableFuture<>();
    private Employee employee;
    private JButton RedactButton;
    private EmployeeService employeeService;
    private int idInt;
    private JLabel ID;
    private JTextField Name;
    private int salaryInt;
    private JTextField Salary;
    private JPanel panel1;

    private JFrame frame;

    public RedactEmployee(EmployeeService employeeService, Employee employee){
        this.employeeService = employeeService;
        this.employee = employee;
        frame=new JFrame("Patch employee");
        frame.setPreferredSize(new Dimension(500, 300));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        RedactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    idInt = Integer.parseInt(ID.getText());
                    salaryInt = Integer.parseInt(Salary.getText());
                    Employee currentEmployee = new Employee(idInt, Name.getText(), salaryInt);
                    employeeService.redactEmployee(idInt, Name.getText(), salaryInt).whenComplete((unused, throwable) -> {
                        if(throwable != null){
                            throwable.printStackTrace();
                            completableFuture.completeExceptionally(throwable);
                            return;
                        }
                        frame.dispose();
                        completableFuture.complete(null);
                    });

                }catch(Exception exception){
                    System.out.println("Неправильно введены данные.");
                    new AlarmWindowPatch();
                }

            }
        });
        String dataId = Integer.toString(employee.getId());
        String dataSalary = Integer.toString(employee.getSalaryEmployee());
        ID.setText(dataId);// из строки
        Name.setText(employee.getNameEmployee());
        Salary.setText(dataSalary);
    }

    public CompletableFuture<Void> getCompletableFuture() {
        return completableFuture;
    }
}

