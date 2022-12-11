package Front;

import Models.Employee.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedactEmployee {
    private JButton RedactButton;
    private JTextField ID;
    private JTextField Name;
    private JTextField Salary;
    private JPanel panel1;

    private JFrame frame;

    public RedactEmployee(){
        frame=new JFrame("Add employee");
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        RedactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                EmployeeForm.frame.setVisible(false);
                //нужно вывести окно EmployeeForm, но я чет туплю
            }
        });
        ID.setText("Привет");// из строки
    }
}

