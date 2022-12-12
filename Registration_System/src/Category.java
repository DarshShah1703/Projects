import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Category extends JFrame {
    private JTable table1;
    private JTextField categoryIdTextField;
    private JTextField categoryNameTextField;
    private JButton addCategoryButton;
    private JButton editCategoryButton;
    private JButton returnButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton deleteCategoryButton;
    private JLabel categoryLabel;
    private JLabel categoryIdLabel;
    private JLabel categoryNameLabel;
    private JPanel Categorypanal;
    private JScrollPane Category_table;
    private JButton goToProductsButton;
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
            pst=conn.prepareStatement("select * from category");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addCategory()
    {
        String CATEGORY_ID,CATEGORY_NAME;
        CATEGORY_ID = categoryIdTextField.getText();
        CATEGORY_NAME= categoryNameTextField.getText();
        try {
            //query
            pst = conn.prepareStatement("insert into category(CATEGORY_ID,CATEGORY_NAME)values(?,?)");

            pst.setString(1, CATEGORY_ID);
            pst.setString(2, CATEGORY_NAME);
           
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!!!!!");

            Table_load();

            categoryIdTextField.setText("");
            categoryNameTextField.setText("");
            categoryIdTextField.requestFocus();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void editCategory()
    {
        String CATEGORY_ID,CATEGORY_NAME;
        CATEGORY_ID = categoryIdTextField.getText();
        CATEGORY_NAME= categoryNameTextField.getText();
        try {
            pst = conn.prepareStatement("update category set CATEGORY_NAME = ? where CATEGORY_ID = ?");

            pst.setString(2, CATEGORY_ID);
            pst.setString(1, CATEGORY_NAME);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Update!!!!!");

            Table_load();

            categoryIdTextField.setText("");
            categoryNameTextField.setText("");
        }

        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void deleteCategory()
    {
        String CATEGORY_ID;
        CATEGORY_ID = categoryIdTextField.getText();
        try {
            pst = conn.prepareStatement("delete from category  where CATEGORY_ID = ?");

            pst.setString(1, CATEGORY_ID);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");

            Table_load();

            categoryIdTextField.setText("");
            categoryNameTextField.setText("");
            categoryIdTextField.requestFocus();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void searchCategory()
    {
        try {
            String CATEGORY_NAME = searchField.getText();
            pst = conn.prepareStatement("select CATEGORY_ID from category where CATEGORY_NAME = ?");
            pst.setString(1, CATEGORY_NAME);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String CATEGORY_ID;
                CATEGORY_ID = rs.getString(1);
                categoryIdTextField.setText(CATEGORY_ID);
                categoryNameTextField.setText(CATEGORY_NAME);
            }
            else
            {
                categoryIdTextField.setText("");
                categoryNameTextField.setText("");
                JOptionPane.showMessageDialog(null,"Invalid Category Id");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public Category(JTextField categoryNameTextField)
    {
        this.categoryNameTextField.setText(categoryNameTextField.getText());
        this.helper();
    }
    public void helper()
    {
        setTitle("Stock Management System");
        setContentPane(Categorypanal);
        setMinimumSize(new Dimension(450, 474));
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        connect();
        Table_load();
        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCategory();
            }
        });
        editCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCategory();
            }
        });
        deleteCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCategory();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCategory();
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
        goToProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Product pro=new Product();
                pro.setLocationRelativeTo(null);
                pro.setVisible(true);
            }
        });
    }
    public Category() {
    this.helper();
    }
}