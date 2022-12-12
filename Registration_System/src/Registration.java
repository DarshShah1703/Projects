import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registration extends JDialog{
    private JButton registerButton;
    private JButton clearButton;
    private JButton returnButton;
    private JTextField userIdTextField;
    private JTextField userNameTextField;
    private JPasswordField passwordPasswordField;
    private JPasswordField confirmPasswordPasswordField;
    private JTextField emailIdTextField;
    private JTextField moblieNoTextField;
    private JTextField addressTextField;
    private JPanel registrationPane;

    public Registration(Login Rframe)
    {
        super(Rframe);
        setTitle("Create a new account");
        setContentPane(registrationPane);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(Rframe);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
                userIdTextField.setText("");
                userNameTextField.setText("");
                passwordPasswordField.setText("");
                confirmPasswordPasswordField.setText("");
                emailIdTextField.setText("");
                moblieNoTextField.setText("");
                addressTextField.setText("");
                userIdTextField.requestFocus();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userIdTextField.setText("");
                userNameTextField.setText("");
                passwordPasswordField.setText("");
                confirmPasswordPasswordField.setText("");
                emailIdTextField.setText("");
                moblieNoTextField.setText("");
                addressTextField.setText("");
                userIdTextField.requestFocus();
            }
        });
    }

    private void registerUser() {
        String u_id=userIdTextField.getText();
        String u_name=userNameTextField.getText();
        String password=String.valueOf(passwordPasswordField.getPassword());
        String conformPassword=String.valueOf(confirmPasswordPasswordField.getPassword());
        String email=emailIdTextField.getText();
        String ph_no=moblieNoTextField.getText();
        String address=addressTextField.getText();

        if (u_id.isEmpty() ||u_name.isEmpty() || password.isEmpty() ||conformPassword.isEmpty() ||email.isEmpty() || ph_no.isEmpty() || address.isEmpty() ) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(conformPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
         user=addUserToDatabase(u_id,u_name,password,email, ph_no, address);

        if (user!=null)
        {
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public UserR user;
    private UserR addUserToDatabase(String u_id, String u_name, String password, String email, String ph_no, String address)
    {
        UserR user=null;

            final String DB_URL = "jdbc:mysql://localhost:3306/stock_management_system";
            final String USERNAME = "root";
            final String PASSWORD = "D&mysql170803";
            try
            {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);;
                Statement stmt = conn.createStatement();
                String sql="insert into user(U_ID,U_NAME,PASSWORD,EMAIL_ID,ADDRESS,MOBILE_NO)values(?,?,?,?,?,?)";
                PreparedStatement pst=conn.prepareStatement(sql);

                pst.setString(1,u_id);
                pst.setString(2,u_name);
                pst.setString(3,password);
                pst.setString(4,email);
                pst.setString(5,address);
                pst.setString(6,ph_no);

                //Insert row into the table.
                int addedRows=pst.executeUpdate();
                if(addedRows>0)
                {
                    user=new UserR();
                    user.U_ID=u_id;
                    user.U_NAME=u_name;
                    user.PASSWORD=password;
                    user.EMAIL_ID=email;
                    user.MOBILE_NO=ph_no;
                    user.ADDRESS=address;
                }
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return user;
    }
}
