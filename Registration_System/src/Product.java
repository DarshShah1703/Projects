import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Product extends JFrame{
    private JPanel productpanal;
    private JTable table1;
    private JTextField productIdTextField;
    private JTextField productNameTextField;
    private JTextField categoryTextField;
    private JTextField buyPriceTextField;
    private JTextField sellPriceTextField;
    private JTextField quantityTextField;
    private JTextField profitTextField;
    private JTextField soldQuantityTextField;
    private JTextField searchfield;
    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;
    private JButton searchButton;
    private JButton returnButton;
    private JScrollPane Product_Table;
    private JButton goToCategoryButton;
    private JComboBox Category_box;
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
    public void Table_load()
    {
        try {
            pst=conn.prepareStatement("select * from product");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProduct()
    {
    String P_ID,P_NAME,CATEGORY,BUY_PRICE,SELL_PRICE,QUANTITY,PROFIT,SOLD_QTY;
    P_ID = productIdTextField.getText();
    P_NAME= productNameTextField.getText();
    CATEGORY= categoryTextField.getText();
    BUY_PRICE= buyPriceTextField.getText();
    SELL_PRICE= sellPriceTextField.getText();
    QUANTITY= quantityTextField.getText();
    SOLD_QTY= soldQuantityTextField.getText();
    try {
        //query
        pst = conn.prepareStatement("insert into product(P_ID,P_NAME,CATEGORY,BUY_PRICE,SELL_PRICE,QUANTITY,SOLD_QTY)values(?,?,?,?,?,?,?)");

        pst.setString(1, P_ID);
        pst.setString(2, P_NAME);
        pst.setString(3, CATEGORY);
        pst.setString(4, BUY_PRICE);
        pst.setString(5, SELL_PRICE);
        pst.setString(6, QUANTITY);
        //pst.setString(7, PROFIT);
        pst.setString(7, SOLD_QTY);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record Added!!!!!");

        Table_load();

        productIdTextField.setText("");
        productNameTextField.setText("");
        categoryTextField.setText("");
        buyPriceTextField.setText("");
        sellPriceTextField.setText("");
        quantityTextField.setText("");
        profitTextField.setText("");
        soldQuantityTextField.setText("");
        productIdTextField.requestFocus();
    }

    catch (SQLException e1)
    {

        e1.printStackTrace();
    }

}
    public void editProduct()
    {
        String P_ID,P_NAME,CATEGORY,BUY_PRICE,SELL_PRICE,QUANTITY,SOLD_QTY;
        P_ID = productIdTextField.getText();
        P_NAME= productNameTextField.getText();
        CATEGORY= categoryTextField.getText();
        BUY_PRICE= buyPriceTextField.getText();
        SELL_PRICE= sellPriceTextField.getText();
        QUANTITY= quantityTextField.getText();
        //PROFIT= profitTextField.getText();
        SOLD_QTY= soldQuantityTextField.getText();
        //profitTextField.setText(getProfit(productIdTextField));

        try {
            pst = conn.prepareStatement("update product set P_NAME = ?,CATEGORY = ?,BUY_PRICE = ?,SELL_PRICE = ?,QUANTITY = ?,SOLD_QTY = ? where P_ID = ?");

            pst.setString(7, P_ID);
            pst.setString(1, P_NAME);
            pst.setString(2, CATEGORY);
            pst.setString(3, BUY_PRICE);
            pst.setString(4, SELL_PRICE);
            pst.setString(5, QUANTITY);
            //pst.setString(6, PROFIT);
            pst.setString(6, SOLD_QTY);


            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Update!!!!!");

            Table_load();

            productIdTextField.setText("");
            productNameTextField.setText("");
            categoryTextField.setText("");
            buyPriceTextField.setText("");
            sellPriceTextField.setText("");
            quantityTextField.setText("");
            profitTextField.setText("");
            soldQuantityTextField.setText("");
            productIdTextField.requestFocus();
        }

        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void deleteProduct()
    {
        String P_ID;
        P_ID = productIdTextField.getText();
        try {
            pst = conn.prepareStatement("delete from product  where P_ID = ?");
            pst.setString(1, P_ID);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
            Table_load();
            productIdTextField.setText("");
            productNameTextField.setText("");
            categoryTextField.setText("");
            buyPriceTextField.setText("");
            sellPriceTextField.setText("");
            quantityTextField.setText("");
            profitTextField.setText("");
            soldQuantityTextField.setText("");
            productIdTextField.requestFocus();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void searchProduct()
    {
        try {

            String P_NAME = searchfield.getText();

            pst = conn.prepareStatement("select P_ID,CATEGORY,BUY_PRICE,SELL_PRICE,QUANTITY,PROFIT,SOLD_QTY from product where P_NAME = ?");
            pst.setString(1, P_NAME);
            ResultSet rs = pst.executeQuery();

            if(rs.next())
            {
                String P_ID,CATEGORY,BUY_PRICE,SELL_PRICE,QUANTITY,PROFIT,SOLD_QTY;
                P_ID = rs.getString(1);
                CATEGORY = rs.getString(2);
                BUY_PRICE = rs.getString(3);
                SELL_PRICE= rs.getString(4);
                QUANTITY= rs.getString(5);
                PROFIT= rs.getString(6);
                SOLD_QTY= rs.getString(7);

                productIdTextField.setText(P_ID);
                productNameTextField.setText(P_NAME);
                categoryTextField.setText(CATEGORY);
                buyPriceTextField.setText(BUY_PRICE);
                sellPriceTextField.setText(SELL_PRICE);
                quantityTextField.setText(QUANTITY);
                profitTextField.setText(PROFIT);
                soldQuantityTextField.setText(SOLD_QTY);
            }
            else
            {
                productIdTextField.setText("");
                productNameTextField.setText("");
                categoryTextField.setText("");
                buyPriceTextField.setText("");
                sellPriceTextField.setText("");
                quantityTextField.setText("");
                profitTextField.setText("");
                soldQuantityTextField.setText("");
                JOptionPane.showMessageDialog(null,"Invalid Product Id");
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
            pst= conn.prepareStatement("select category_name from category");
            ResultSet rs= pst.executeQuery();
            while (rs.next())
            {
                String category_name=rs.getString("category_name");
                Category_box.addItem(category_name);
                categoryTextField.setText((String) Category_box.getSelectedItem());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Product() {
        setTitle("Stock Management System");
        setContentPane(productpanal);
        setMinimumSize(new Dimension(450, 474));
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        connect();
        Table_load();
        fillCombo();
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
                Category_box.removeAllItems();
                Category_box.setEnabled(true);
                fillCombo();
            }
        });
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduct();
                Category_box.removeAllItems();
                Category_box.setEnabled(true);
                fillCombo();
            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
                Category_box.removeAllItems();
                Category_box.setEnabled(true);
                fillCombo();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Category_box.removeAllItems();
                Category_box.setEnabled(true);
                fillCombo();
                searchProduct();
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
        Category_box.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                fillCombo();
                Category_box.setEnabled(false);
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
        goToCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Category cat=new Category(categoryTextField);
                cat.setLocationRelativeTo(null);
                cat.setVisible(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Table_load();
                Category_box.removeAllItems();
                fillCombo();
                Category_box.setEnabled(true);
                productIdTextField.setText("");
                productNameTextField.setText("");
                categoryTextField.setText("");
                buyPriceTextField.setText("");
                sellPriceTextField.setText("");
                quantityTextField.setText("");
                profitTextField.setText("");
                soldQuantityTextField.setText("");
                productIdTextField.requestFocus();
            }
        });
    }
}
