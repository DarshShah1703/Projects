import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JTextField userNameTextField;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;
    private JButton clearButton;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JButton registerButton;
    private JPanel loginpane;
    public Login() {
        setTitle("Stock Management System");
        setContentPane(loginpane);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration reg=new Registration(null);
                UserR user = reg.user;
                if(user!=null)
                {
                    JOptionPane.showMessageDialog(Login.this,
                            "New user: " + user.U_NAME,
                            "Successful Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(Login.this,
                            "New user: " + user.U_NAME,
                            "Registration canceled",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u_name=userNameTextField.getText();
                String password=String.valueOf(passwordPasswordField.getPassword());
                user=getAuthonticateduser(u_name,password);
                if (user!=null)
                {
                    Admin adm=new Admin();
                    if(user!=null)
                    {
                        dispose();
                        adm.lbAdmin.setText(user.U_NAME);
                        System.out.println(user);
                        adm.setVisible(true);
                        adm.setLocationRelativeTo(null);
                    }
                    else
                    {
                        dispose();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Failed to login ",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNameTextField.setText("");
                passwordPasswordField.setText("");
                userNameTextField.requestFocus();
            }
        });
        setVisible(true);
    }
    public UserR user;
    private UserR getAuthonticateduser(String u_name, String password)
    {
        UserR user=null;
        final String DB_URL = "jdbc:mysql://localhost:3306/stock_management_system";
        final String USERNAME = "root";
        final String PASSWORD = "D&mysql170803";

        try
        {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql="SELECT * FROM USER WHERE U_NAME=? AND PASSWORD=?";
            PreparedStatement pst=conn.prepareStatement(sql);

            pst.setString(1,u_name);
            pst.setString(2,password);

            //Insert row into the table.
            ResultSet rst=pst.executeQuery();
            if(rst.next())
            {
                user=new UserR();
                user.U_ID=rst.getString("U_ID");
                user.U_NAME=rst.getString("U_NAME");
                user.PASSWORD=rst.getString("PASSWORD");
                user.EMAIL_ID=rst.getString("EMAIL_ID");
                user.ADDRESS=rst.getString("ADDRESS");
                user.MOBILE_NO=rst.getString("MOBILE_NO");
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
class ProgreeBar extends JFrame implements Runnable
{
    private JProgressBar progressBar1;
    private JPanel ProgressPanal;
    private JLabel LodingCount;
    @Override
    public void run() {

        for(int i=0;i<=100;i++)
        {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LodingCount.setText(i+"%");
            progressBar1.setValue(i);
        }
        Login log =new Login();
        dispose();
    }
    public ProgreeBar()
    {
        setTitle("Stock Management System");
        setContentPane(ProgressPanal);
        setMinimumSize(new Dimension(450, 474));
        setSize(450, 420);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Thread t=new Thread(this);
        t.start();
    }
    public static void main(String[] args) {
        ProgreeBar pro=new ProgreeBar();
        pro.setVisible(true);
    }
}
