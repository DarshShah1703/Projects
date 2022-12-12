import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Order extends JFrame{
    private JTable Order_table;
    private JTable Product_table;
    private JButton addOrderButton;
    private JButton editOrderButton;
    private JButton deleteOrderButton;
    private JButton searchButton;
    private JTextField orderIdTextField;
    private JTextField orderDateTextField;
    private JTextField customerIdTextField;
    private JTextField dueDateTextField;
    private JLabel ordersLabel;
    private JTextField statusTextField;
    private JTextField productNameTextField;
    private JTextField quantityTextField;
    private JTextField priceTextField;
    private JLabel statusLabel;
    private JLabel orderIdLabel;
    private JLabel orderDateLabel;
    private JLabel customerIdLabel;
    private JLabel dueDateLabel;
    private JLabel productNameLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JTextField searchField;
    private JPanel orderPanal;
    private JButton returnButton;
    private JButton generateBillButton;
    private JComboBox Products_box;
    private JButton goToCustomersButton;
    private JButton refreshButton;

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
    public void TableO_load()
    {
        try {
            pst=conn.prepareStatement("select * from orders");
            ResultSet rs=pst.executeQuery();
            Order_table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void TableP_load()
    {
        try {
            pst=conn.prepareStatement("select * from Product");
            ResultSet rs=pst.executeQuery();
            Product_table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addOrder()
    {
        String O_ID,C_ID,PRODUCT_NAME,QUANTITY,PRICE,O_DATE,DUE_DATE,O_STATUS;
        O_ID = orderIdTextField.getText();
        C_ID=customerIdTextField.getText();
        PRODUCT_NAME= productNameTextField.getText();
        QUANTITY=quantityTextField.getText();
        PRICE=priceTextField.getText();
        O_DATE=orderDateTextField.getText();
        DUE_DATE=dueDateTextField.getText();
        O_STATUS=statusTextField.getText();

        try {
            //query
            pst = conn.prepareStatement("insert into ORDERS(O_ID,C_ID,PRODUCT_NAME,QUANTITY,PRICE,O_DATE,DUE_DATE,O_STATUS)values(?,?,?,?,?,?,?,?)");

            pst.setString(1, O_ID);
            pst.setString(2, C_ID);
            pst.setString(3, PRODUCT_NAME);
            pst.setString(4, QUANTITY);
            pst.setString(5, PRICE);
            pst.setString(6, O_DATE);
            pst.setString(7, DUE_DATE);
            pst.setString(8, O_STATUS);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!!!!!");

            TableO_load();

            orderIdTextField.setText("");
            customerIdTextField.setText("");
            productNameTextField.setText("");
            quantityTextField.setText("");
            priceTextField.setText("");
            dueDateTextField.setText("");
            statusTextField.setText("");
            orderIdTextField.requestFocus();
        }
        catch (SQLException e1)
        {

            e1.printStackTrace();
        }
    }
    public void editOrder()
    {
        String O_ID,C_ID,PRODUCT_NAME,QUANTITY,PRICE,O_DATE,DUE_DATE,O_STATUS;
        O_ID = orderIdTextField.getText();
        C_ID=customerIdTextField.getText();
        PRODUCT_NAME= productNameTextField.getText();
        QUANTITY=quantityTextField.getText();
        PRICE=priceTextField.getText();
        O_DATE=orderDateTextField.getText();
        DUE_DATE=dueDateTextField.getText();
        O_STATUS=statusTextField.getText();

        try {
            pst = conn.prepareStatement("update ORDERS set C_ID=?,PRODUCT_NAME=?,QUANTITY=?,PRICE=?,O_DATE=?,DUE_DATE=?,O_STATUS=? where O_ID = ?");

            pst.setString(8, O_ID);
            pst.setString(1, C_ID);
            pst.setString(2, PRODUCT_NAME);
            pst.setString(3,QUANTITY);
            pst.setString(4,PRICE);
            pst.setString(5, O_DATE);
            pst.setString(6, DUE_DATE);
            pst.setString(7,O_STATUS);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Update!!!!!");

            TableO_load();

            orderIdTextField.setText("");
            customerIdTextField.setText("");
            productNameTextField.setText("");
            quantityTextField.setText("");
            priceTextField.setText("");
            dueDateTextField.setText("");
            statusTextField.setText("");
            orderIdTextField.requestFocus();
        }

        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void deleteOrder()
    {
        String O_ID;
        O_ID = orderIdTextField.getText();

        try {
            pst = conn.prepareStatement("delete from ORDERS where O_ID = ?");

            pst.setString(1, O_ID);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");

            TableO_load();

            orderIdTextField.setText("");
            customerIdTextField.setText("");
            productNameTextField.setText("");
            quantityTextField.setText("");
            priceTextField.setText("");
            dueDateTextField.setText("");
            statusTextField.setText("");
            orderIdTextField.requestFocus();

        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void searchOrder()
    {
        try {
            String O_ID = searchField.getText();
            pst = conn.prepareStatement("select C_ID,PRODUCT_NAME,QUANTITY,PRICE,O_DATE,DUE_DATE,O_STATUS from ORDERS where O_ID = ?");
            pst.setString(1, O_ID);
            ResultSet rs = pst.executeQuery();

            if(rs.next())
            {
                String C_ID,PRODUCT_NAME,QUANTITY,PRICE,O_DATE,DUE_DATE,O_STATUS;
                C_ID=rs.getString(1);
                PRODUCT_NAME = rs.getString(2);
                QUANTITY=rs.getString(3);
                PRICE=rs.getString(4);
                O_DATE=rs.getString(5);
                DUE_DATE= rs.getString(6);
                O_STATUS=rs.getString(7);
                orderIdTextField.setText(O_ID);
                customerIdTextField.setText(C_ID);
                productNameTextField.setText(PRODUCT_NAME);
                quantityTextField.setText(QUANTITY);
                priceTextField.setText(PRICE);
                orderDateTextField.setText(O_DATE);
                dueDateTextField.setText(DUE_DATE);
                statusTextField.setText(O_STATUS);
            }
            else
            {
                orderIdTextField.setText("");
                customerIdTextField.setText("");
                productNameTextField.setText("");
                quantityTextField.setText("");
                priceTextField.setText("");
                orderDateTextField.setText("");
                dueDateTextField.setText("");
                statusTextField.setText("");
                orderIdTextField.requestFocus();

                JOptionPane.showMessageDialog(null,"Invalid ORDERS Id");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    private void fillCombo()
    {
        try {
            pst= conn.prepareStatement("select p_name from product");
            ResultSet rs= pst.executeQuery();
            while (rs.next())
            {
                String p_name=rs.getString("p_name");
                Products_box.addItem(p_name);
                productNameTextField.setText((String) Products_box.getSelectedItem());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Order(JTextField customerId) {
        this.customerIdTextField.setText(customerId.getText());
        this.helper();
    }

    public void helper() {
        setTitle("Stock Management System");
        setContentPane(orderPanal);
        setMinimumSize(new Dimension(450, 474));
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        orderDateTextField.setText(String.valueOf(java.time.LocalDate.now()));
        connect();
        TableO_load();
        TableP_load();
        fillCombo();
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrder();
                Products_box.removeAllItems();
                Products_box.setEnabled(true);
                fillCombo();
            }
        });
        editOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editOrder();
                Products_box.removeAllItems();
                Products_box.setEnabled(true);
                fillCombo();
            }
        });
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
                Products_box.removeAllItems();
                Products_box.setEnabled(true);
                fillCombo();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Products_box.removeAllItems();
                Products_box.setEnabled(true);
                fillCombo();
                searchOrder();
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
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BillingSystem bls=new BillingSystem(orderIdTextField,customerIdTextField,productNameTextField,quantityTextField,priceTextField);
                bls.setLocationRelativeTo(null);
                bls.setVisible(true);
            }
        });
        Products_box.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                fillCombo();
                Products_box.setEnabled(false);
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
        goToCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Customer cus=new Customer();
                cus.setLocationRelativeTo(null);
                cus.setVisible(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableO_load();
                TableP_load();
                Products_box.removeAllItems();
                fillCombo();
                Products_box.setEnabled(true);
                orderIdTextField.setText("");
                customerIdTextField.setText("");
                productNameTextField.setText("");
                quantityTextField.setText("");
                priceTextField.setText("");
                dueDateTextField.setText("");
                statusTextField.setText("");
                orderIdTextField.requestFocus();
            }
        });
    }
    public Order() {
        this.helper();

    }
}
