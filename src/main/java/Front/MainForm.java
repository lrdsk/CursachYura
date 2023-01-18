package Front;

import Models.Employee.EmployeeService;
import Models.Shop.ShopService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainForm extends JFrame{

    private EmployeeService employeeService;
    private ShopService shopService;
    private JPanel panel1;
    private JButton ShopButton;
    private JButton EmployeeButton;
    private JLabel shopName;
    private JLabel specialization;

    private JFrame frame;

    public MainForm(EmployeeService employeeService, ShopService shopService) throws SQLException {
        this.employeeService = employeeService;
        this.shopService = shopService;
        frame=new JFrame("Main Page");
        shopService.getShop().whenComplete((shop, throwable) -> {
            shopName.setText(shop.getNameOfShop());
            specialization.setText(shop.getSpecializationOfShop());
        });
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 500));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        EmployeeForm employeeForm=new EmployeeForm(this.employeeService, this.shopService);
        EmployeeButton.addActionListener(employeeForm);

        EmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        //shopName.setText();
    }

}
