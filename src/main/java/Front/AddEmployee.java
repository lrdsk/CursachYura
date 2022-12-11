package Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployee{
    private JPanel panel1;
    private JButton AddButton;

    private JFrame frame;
    private JTextField ID;
    private JTextField Salary;
    private JTextField Name;

    public AddEmployee() {
        frame=new JFrame("Add employee");
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }
}
