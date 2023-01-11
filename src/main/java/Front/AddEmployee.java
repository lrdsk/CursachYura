package Front;

import Models.Employee.Employee;
import Models.Employee.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddEmployee{
    private JPanel panel1;
    private JButton AddButton;
    private EmployeeService employeeService;
    private int idEmployeeInt;
    private int salaryEmployeeInt;
    private String nameEmployeeString;
    private JFrame frame;
    private JTextField ID;
    private JTextField Salary;
    private JTextField Name;

    public AddEmployee(EmployeeService employeeService) throws SQLException {
        this.employeeService = employeeService;
        frame=new JFrame("Add employee");
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        EmployeeForm employeeForm=new EmployeeForm(this.employeeService);
        AddButton.addActionListener(employeeForm);
        AddButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salaryEmployeeInt = Integer.parseInt(Salary.getText());
                    idEmployeeInt = Integer.parseInt(ID.getText());
                    nameEmployeeString = Name.getText();
                    Employee employee = new Employee(idEmployeeInt,nameEmployeeString,salaryEmployeeInt);
                    employeeService.setInfoAboutEmployeeToDB(employee);

                }catch (NumberFormatException exception){
                    System.out.println("Неправильно введено значение ID или Salary." +
                            "\nПоля должны быть заполнены в формате целых чисел.");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setVisible(false);
            }
        });
    }
}
