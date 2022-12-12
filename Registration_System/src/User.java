import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class User extends JFrame{
    private JPanel UserPanal;
    private JTable table1;
    private JTextField userIdTextField;
    private JTextField userNameTextField;
    private JTextField emailIdTextField;
    private JTextField addressTextField;
    private JTextField contectNoTextField;
    private JButton addUserButton;
    private JButton editUserButton;
    private JButton deleteUserButton;
    private JButton searchButton;
    private JTextField textField7;
    private JButton returnButton;
    private JPasswordField passwordPasswordField;
    private JLabel userIdLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel emailIdLabel;
    private JLabel addressLabel;
    private JLabel contactNoLabel;
    private JTextField searchfield;
    private JScrollPane User_Table;
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
            pst=conn.prepareStatement("select * from user");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addUser()
    {
        String U_ID,U_NAME,PASSWORD,EMAIL_ID,ADDRESS,MOBILE_NO;
        U_ID = userIdTextField.getText();
        U_NAME=userNameTextField.getText();
        PASSWORD= passwordPasswordField.getText();
        EMAIL_ID=emailIdTextField.getText();
        ADDRESS=addressTextField.getText();
        MOBILE_NO=contectNoTextField.getText();

        try {
            //query
            pst = conn.prepareStatement("insert into user(U_ID,U_NAME,PASSWORD,EMAIL_ID,ADDRESS,MOBILE_NO)values(?,?,?,?,?,?)");
            pst.setString(1, U_ID);
            pst.setString(2, U_NAME);
            pst.setString(3, PASSWORD);
            pst.setString(4, EMAIL_ID);
            pst.setString(5, ADDRESS);
            pst.setString(6, MOBILE_NO);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!!!!!");

            Table_load();

            userIdTextField.setText("");
            userNameTextField.setText("");
            passwordPasswordField.setText("");
            emailIdTextField.setText("");
            addressTextField.setText("");
            contectNoTextField.setText("");
            userIdTextField.requestFocus();
        }
        catch (SQLException e1)
        {

            e1.printStackTrace();
        }
    }
    public void editUser()
    {
        String U_ID,U_NAME,PASSWORD,EMAIL_ID,ADDRESS,MOBILE_NO;
        U_ID = userIdTextField.getText();
        U_NAME=userNameTextField.getText();
        PASSWORD= passwordPasswordField.getText();
        EMAIL_ID=emailIdTextField.getText();
        ADDRESS=addressTextField.getText();
        MOBILE_NO=contectNoTextField.getText();

        try {
            pst = conn.prepareStatement("update user set U_NAME=?,PASSWORD=?,EMAIL_ID=?,ADDRESS=?,MOBILE_NO=? where U_ID = ?");

            pst.setString(6, U_ID);
            pst.setString(1, U_NAME);
            pst.setString(2, PASSWORD);
            pst.setString(3, EMAIL_ID);
            pst.setString(4, ADDRESS);
            pst.setString(5, MOBILE_NO);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Update!!!!!");

            Table_load();

            userIdTextField.setText("");
            userNameTextField.setText("");
            passwordPasswordField.setText("");
            emailIdTextField.setText("");
            addressTextField.setText("");
            contectNoTextField.setText("");
            userIdTextField.requestFocus();
        }

        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    public void deleteUser()
    {
        String U_ID;
        U_ID = userIdTextField.getText();

        try {
            pst = conn.prepareStatement("delete from user where U_ID = ?");

            pst.setString(1, U_ID);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");

            Table_load();

            userIdTextField.setText("");
            userNameTextField.setText("");
            passwordPasswordField.setText("");
            emailIdTextField.setText("");
            addressTextField.setText("");
            contectNoTextField.setText("");
            userIdTextField.requestFocus();

        }

        catch (SQLException e1)
        {

            e1.printStackTrace();
        }
    }
    public void searchUser()
    {
        try {

            String U_NAME = searchfield.getText();

            pst = conn.prepareStatement("select U_ID,PASSWORD,EMAIL_ID,ADDRESS,MOBILE_NO from user where U_NAME = ?");
            pst.setString(1, U_NAME);
            ResultSet rs = pst.executeQuery();

            if(rs.next())
            {
                String U_ID,PASSWORD,EMAIL_ID,ADDRESS,MOBILE_NO;
                U_ID=rs.getString(1);
                PASSWORD = rs.getString(2);
                EMAIL_ID=rs.getString(3);
                ADDRESS= rs.getString(4);
                MOBILE_NO=rs.getString(5);

                userIdTextField.setText(U_ID);
                userNameTextField.setText(U_NAME);
                passwordPasswordField.setText(PASSWORD);
                emailIdTextField.setText(EMAIL_ID);
                addressTextField.setText(ADDRESS);
                contectNoTextField.setText(MOBILE_NO);

            }
            else
            {
                userIdTextField.setText("");
                userNameTextField.setText("");
                passwordPasswordField.setText("");
                emailIdTextField.setText("");
                addressTextField.setText("");
                userIdTextField.requestFocus();
                contectNoTextField.setText("");

                JOptionPane.showMessageDialog(null,"Invalid UserR Id");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public User() {
        setTitle("Stock Management System");
        setContentPane(UserPanal);
        setMinimumSize(new Dimension(450, 474));
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        connect();
        Table_load();
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUser();
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUser();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin adm = new Admin();
                adm.setLocationRelativeTo(null);
                adm.setVisible(true);
            }
        });
    }
}