import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    private JButton categoriesButton;
    private JButton ordersButton;
    private JButton productsButton;
    private JButton customersButton;
    private JButton usersButton;
    private JButton logoutButton;
    private JPanel adminpanel;
    JLabel lbAdmin;
    private JLabel welcomeToYourShopLabel;
    public Admin()
    {
        setTitle("Stock Management System");
        setContentPane(adminpanel);
        setMinimumSize(new Dimension(450, 474));
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log=new Login();

            }
        });
        categoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Category cat=new Category();
                cat.setLocationRelativeTo(null);
                cat.setVisible(true);
            }
        });
        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Product pro=new Product();
                pro.setVisible(true);
                pro.setLocationRelativeTo(null);
            }
        });
        ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Order ore=new Order();
                ore.setLocationRelativeTo(null);
                ore.setVisible(true);
            }
        });
        customersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Customer cus=new Customer();
                cus.setLocationRelativeTo(null);
                cus.setVisible(true);
            }
        });
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                User use= new User();
                use.setLocationRelativeTo(null);
                use.setVisible(true);
            }
        });
    }
}
