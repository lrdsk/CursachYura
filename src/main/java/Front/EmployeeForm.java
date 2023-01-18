package Front;

import Models.Employee.Employee;
import Models.Employee.EmployeeFormatter;
import Models.Employee.EmployeeService;
import Models.Shop.ShopService;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class EmployeeForm implements ActionListener{
    private int employeeId;
    private EmployeeService employeeService;
    private List<String> currentEmployeesList;
    private ShopService shopService;
    private JPanel panel1;
    private JList list;
    private JButton AddButton;
    private JButton RedactButton;
    private JButton DelButton;
    private JButton ExitButton;
    public static JFrame frame;

    private void CreateForm(){
        frame=new JFrame("Employee");
        frame.setPreferredSize(new Dimension(700, 500));
        frame.add(panel1);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        list.setListData(new Object[]{});
        employeeService.getAllEmployees().whenComplete((employeeList, throwable) -> {
            if(throwable != null){
                throwable.printStackTrace();
                return;
            }
            SwingUtilities.invokeLater(() -> list.setListData(employeeList.toArray()));
        });
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private int getNameFromListInfo(String[] select){
        String[] strOfEmployee = select[0].split(",");
        String id = strOfEmployee[2];
        id = id.replace("id=","");
        id = id.replace("}","");
        id = id.trim();
        System.out.println(id);
        this.employeeId = Integer.parseInt(id);
        return employeeId;
    }
    public EmployeeForm(EmployeeService employeeService, ShopService shopService) throws SQLException {
        this.employeeService = employeeService;
        this.shopService = shopService;

        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainForm mainForm = new MainForm(employeeService,shopService);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        AddButton.addActionListener(new ActionListener() {
            @Override//
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    AddEmployee addEmployee = new AddEmployee(employeeService, shopService);
                    addEmployee.getCompletableFuture().whenComplete((unused, throwable) -> {
                        if(throwable != null){
                            throwable.printStackTrace();
                            return;
                        }
                        employeeService.getAllEmployees().whenComplete((employeeList, throwable2) ->{
                            if(throwable2 != null){
                                throwable2.printStackTrace();
                                return;
                            }
                            SwingUtilities.invokeLater(() -> list.setListData(employeeList.toArray()));
                        });
                    });
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        final String[] select = {new String()};
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               if(list.getSelectedValue() != null) {
                   select[0] = list.getSelectedValue().toString();
                   employeeId = getNameFromListInfo(select);
               }
            }
        });

       DelButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                employeeService.getAllEmployees().whenComplete((employeeList, throwable) -> {
                    if(throwable != null){
                        throwable.printStackTrace();
                        return;
                    }
                    try {
                        employeeService.deleteEmployee(employeeId);
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }
                    SwingUtilities.invokeLater(() -> list.setListData(employeeList.toArray()));
                });
            }

       });
        RedactButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                employeeService.getEmployee(employeeId).whenComplete((employee, throwable) -> {
                    //soEmployeeFormatter.formatEmployee(employee);
                    RedactEmployee redactEmployee = new RedactEmployee(employeeService, employee);
                    redactEmployee.getCompletableFuture().whenComplete((unused, throwable2) -> {
                        if(throwable2 != null) {
                            throwable2.printStackTrace();
                            return;
                        }
                        employeeService.getAllEmployees().whenComplete((employeeList, throwable3) -> {
                            if(throwable3 != null){
                                throwable3.printStackTrace();
                                return;
                            }
                            SwingUtilities.invokeLater(() -> list.setListData(employeeList.toArray()));
                        });
                    });
                });
            }

        });
    }


    public void actionPerformed(ActionEvent e){
        CreateForm();
    }
}
