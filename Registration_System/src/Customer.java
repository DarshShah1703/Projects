import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Customer extends JFrame{
    private JButton returnButton;
    private JTable table1;
    private JTextField searchField;
    private JButton searchButton;
    private JButton deleteCustomerButton;
    private JButton editCustomerButton;
    private JButton addCustomerButton;
    private JTextField customerIdTextField;
    private JTextField customerNameTextField;
    private JTextField contectNoTextField;
    private JTextField emailIdTextField;
    private JTextField addressTextField;
    private JLabel customerIdLabel;
    private JLabel customerNameLabel;
    private JLabel contectNoLabel;
    private JLabel emailIdLabel;
    private JLabel addressLabel;
    private JPanel CustomerPanal;
    private JButton goToOrderButton;
    Connection conn;
    PreparedStatement pst;
    public void connect()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_management_system", "root",
                    "D&mysql170803");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void Table_load()
    {
        try {
            pst=conn.prepareStatement("select * from Customer");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addCustomer()
    {
        String C_ID,C_NAME,C_NO,C_EMAIL,C_ADDRESS;
        C_ID = customerIdTextField.getText();
        C_NAME= customerNameTextField.getText();
        C_NO=contectNoTextField.getText();
        C_EMAIL=emailIdTextField.getText();
        C_ADDRESS=addressTextField.getText();

        try {
            //query
            pst = conn.prepareStatement("insert into customer(C_ID,C_NAME,C_NO,C_EMAIL,C_ADDRESS)values(?,?,?,?,?)");

            pst.setString(1, C_ID);
            pst.setString(2, C_NAME);
            pst.setString(3, C_NO);
            pst.setString(4, C_EMAIL);
            pst.setString(5, C_ADDRESS);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!!!!!");

            Table_load();

            customerIdTextField.setText("");
            customerNameTextField.setText("");
            contectNoTextField.setText("");
            emailIdTextField.setText("");
            addressTextField.setText("");
            customerIdTextField.requestFocus();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void editCustomer()
    {
        String C_ID,C_NAME,C_NO,C_EMAIL,C_ADDRESS;
        C_ID = customerIdTextField.getText();
        C_NAME= customerNameTextField.getText();
        C_NO=contectNoTextField.getText();
        C_EMAIL=emailIdTextField.getText();
        C_ADDRESS=addressTextField.getText();

        try {
            pst = conn.prepareStatement("update customer set C_NAME=?,C_NO=?,C_EMAIL=?,C_ADDRESS=? where C_ID = ?");

            pst.setString(5, C_ID);
            pst.setString(1, C_NAME);
            pst.setString(2, C_NO);
            pst.setString(3, C_EMAIL);
            pst.setString(4, C_ADDRESS);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Update!!!!!");

            Table_load();

            customerIdTextField.setText("");
            customerNameTextField.setText("");
            contectNoTextField.setText("");
            emailIdTextField.setText("");
            addressTextField.setText("");
            customerIdTextField.requestFocus();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void deleteCustomer()
    {
        String C_ID;
        C_ID = customerIdTextField.getText();

        try {
            pst = conn.prepareStatement("delete from customer where C_ID = ?");

            pst.setString(1, C_ID);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");

            Table_load();

            customerIdTextField.setText("");
            //orderIdTextField.setText("");
            customerNameTextField.setText("");
            contectNoTextField.setText("");
            emailIdTextField.setText("");
            addressTextField.setText("");
            customerIdTextField.requestFocus();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void searchCustomer()
    {
        try {
            String C_NAME = searchField.getText();
            pst = conn.prepareStatement("select C_ID,C_NO,C_EMAIL,C_ADDRESS from Customer where C_NAME = ?");
            pst.setString(1, C_NAME);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String C_ID,C_NO,C_EMAIL,C_ADDRESS;
                C_ID = rs.getString(1);
                C_NO=rs.getString(2);
                C_EMAIL= rs.getString(3);
                C_ADDRESS=rs.getString(4);
                customerIdTextField.setText(C_ID);
                customerNameTextField.setText(C_NAME);
                contectNoTextField.setText(C_NO);
                emailIdTextField.setText(C_EMAIL);
                addressTextField.setText(C_ADDRESS);
            }
            else
            {
                customerIdTextField.setText("");
                customerNameTextField.setText("");
                contectNoTextField.setText("");
                emailIdTextField.setText("");
                addressTextField.setText("");
                customerIdTextField.requestFocus();
                JOptionPane.showMessageDialog(null,"Invalid Customer Id");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public Customer() {
        setTitle("Stock Management System");
        setContentPane(CustomerPanal);
        setMinimumSize(new Dimension(450, 474));
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        connect();
        Table_load();
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });
        editCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCustomer();
            }
        });
        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomer();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin adm=new Admin();
                adm.setLocationRelativeTo(null);
                adm.setVisible(true);
            }
        });
        goToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Order ord=new Order(customerIdTextField);
                ord.setLocationRelativeTo(null);
                ord.setVisible(true);
            }
        });
    }
}
