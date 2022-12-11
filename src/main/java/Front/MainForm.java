package Front;

import Models.Employee.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainForm extends JFrame{

    private EmployeeService employeeService;
    private JPanel panel1;
    private JButton ShopButton;
    private JButton EmployeeButton;

    private JFrame frame;

    public MainForm(EmployeeService employeeService) throws SQLException {
        this.employeeService = employeeService;
        frame=new JFrame("Main Page");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        EmployeeForm employeeForm=new EmployeeForm(this.employeeService);
        EmployeeButton.addActionListener(employeeForm);

        EmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

}
