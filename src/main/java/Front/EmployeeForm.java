package Front;

import Models.Employee.Employee;
import Models.Employee.EmployeeService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeForm implements ActionListener{
    private EmployeeService employeeService;
    private List<Employee> data1;
    private JPanel panel1;
    private JList list;
    private JButton AddButton;
    private JButton RedactButton;
    private JButton DelButton;
    private JButton ExitButton;

    public static JFrame frame;

    private void CreateForm(){
        frame=new JFrame("Employee");
        frame.setPreferredSize(new Dimension(400, 300));
        frame.add(panel1);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        try {
            list.setListData(employeeService.getAllEmployees().toArray());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public EmployeeForm(EmployeeService employeeService) throws SQLException {
        this.employeeService = employeeService;
        this.data1 = new ArrayList<>(employeeService.getAllEmployees());
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainForm mainForm=new MainForm(employeeService);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        AddButton.addActionListener(new ActionListener() {
            @Override//
            public void actionPerformed(ActionEvent e) {
                AddEmployee addEmployee=new AddEmployee();
            }
        });



        final String[] select = {new String()};//это бред чтобы разобраться
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                select[0] = list.getSelectedValue().toString();// в select записывается полностью строка по типу Name = 'Arturik', Salary = 5500, id = 11
                System.out.println(select[0]);// интересный факт: SelectionListener работает при нажатии на элемент и отпускании его, но это ничего страшного
            }
        });
       DelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//это кнопка удаления элемента, но нужно выделить из select только имя, чтобы сработал метод удаления по имени
                try {
                    employeeService.deleteEmployeeByName("Arturik");// вместо артурика нужно закинутб имя из select
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setVisible(false);//для того, чтобы данные изменились в списке
                CreateForm();
            }

       });
        RedactButton.addActionListener(new ActionListener() {
            @Override//
            public void actionPerformed(ActionEvent e) {
                RedactEmployee redactEmployee=new RedactEmployee();//кнопка добавления элемента в нее нужно засунуть инфу про нажатую строчку

            }

        });
    }

    public void actionPerformed(ActionEvent e){
        CreateForm();
    }
}
