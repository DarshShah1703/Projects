import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;

public class BillingSystem extends JFrame {
    private JTextArea billArea;
    private JButton printButton;
    private JTextField orderIdTextField;
    private JTextField customerIdTextField;
    private JFormattedTextField orderDetailsFormattedTextField;
    private JButton returnButton;
    private JPanel billSystemPanal;
    private JLabel orderIdLabel;
    private JButton generateBillButton;
    private JTextField totalAmountTextField;
    private JTextField quantityTextField;
    private JTextField pricePiceTextField;
    private JLabel quantityLabel;
    private JLabel pricePiceLabel;
    private JLabel totalAmountLabel;
    private JTextField productNameTextField;
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
    private void setBillArea()
    {
        billArea.setText("************************************************************"+"\n"
        +"COMPANY NAME"+"\n"
        +"CONTACT NO-xxxxxxxxxx"+"\n"
        +"ADDRESS-COMPANY ADDRESS"+"\n"
        +"************************************************************"+"\n"
                +"DATE:"+java.time.LocalDate.now()+"\n");
    }
    public void helper()
    {
        setTitle("Inventory Management System");
        setContentPane(billSystemPanal);
        setMinimumSize(new Dimension(450, 474));
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBillArea();
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billArea.setText(billArea.getText()
                        +"PRODUCT NAME -"+productNameTextField.getText()+"\n"
                        +"QUANTITY          -"+quantityTextField.getText()+"\n"
                        +"PRICE/PIECE      -"+pricePiceTextField.getText()+" rupee"+"\n"
                        +"__________________"+"\n"
                        +"TOTAL                -"+totalAmountTextField.getText()+" rupee"+"\n"
                        +"------------------"+"\n"
                );
            }
        });
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    billArea.setText(billArea.getText()
                            +"THANK FOR VISIT OUR SHOP."+"\n"
                            +"HOPE YOU VISIT AGAIN."+"\n"
                            +"************************************************************"+"\n"
                    );

                    billArea.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
                setStatus();
                setQuantity();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Order ord=new Order();
                ord.setLocationRelativeTo(null);
                ord.setVisible(true);
            }
        });
    }
    public String Total_Amu(JTextField orderIdTextField)
    {
        try {
            String O_ID;
            O_ID = orderIdTextField.getText();
            pst = conn.prepareStatement("SELECT (QUANTITY * PRICE) FROM ORDERS WHERE O_ID=?");
            pst.setString(1, O_ID);
            ResultSet rs = pst.executeQuery();
            String Total;
            if (rs.next()) {
                Total = rs.getString(1);
                totalAmountTextField.setText(Total);
            }
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        return totalAmountTextField.getText();
    }
    private void setStatus()
    {
        String O_ID;
        O_ID=orderIdTextField.getText();
        try {
            pst=conn.prepareStatement("UPDATE orders SET O_STATUS='DONE' WHERE O_ID=?");
            pst.setString(1,O_ID);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void setQuantity()
    {
        String P_NAME,Qs,Ss;
        Qs=quantityTextField.getText();
        Ss=quantityTextField.getText();
        int Qn=Integer.parseInt(Qs),Sn=Integer.parseInt(Ss);
        P_NAME=productNameTextField.getText();
        try {
            pst = conn.prepareStatement("SELECT QUANTITY FROM PRODUCT WHERE P_NAME=?");
            pst.setString(1, P_NAME);
            ResultSet rs1 = pst.executeQuery();
            int Quantity;
            if (rs1.next()) {
                Quantity = Integer.parseInt(rs1.getString(1));
                int result_q=Quantity-Qn;
                String RESULT_Q=String.valueOf(result_q);
                try {
                    pst=conn.prepareStatement("UPDATE PRODUCT SET QUANTITY=? WHERE P_NAME=?");
                    pst.setString(2,P_NAME);
                    pst.setString(1,RESULT_Q);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            pst = conn.prepareStatement("SELECT SOLD_QTY FROM PRODUCT WHERE P_NAME=?");
            pst.setString(1, P_NAME);
            ResultSet rs2 = pst.executeQuery();
            int Sold_qty;
            if (rs2.next()) {
                Sold_qty = Integer.parseInt(rs2.getString(1));
                int result_s=Sold_qty+Sn;
                String RESULT_S=String.valueOf(result_s);
                try {
                    pst=conn.prepareStatement("UPDATE PRODUCT SET SOLD_QTY=? WHERE P_NAME=?");
                    pst.setString(2,P_NAME);
                    pst.setString(1,RESULT_S);
                    pst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public BillingSystem(JTextField orderIdTextField,JTextField customerIdTextField,
                         JTextField productNameTextField,JTextField quantityTextField,
                         JTextField pricePiceTextField)
    {
        connect();
        this.orderIdTextField.setText(orderIdTextField.getText()) ;
        this.customerIdTextField.setText(customerIdTextField.getText());
        this.productNameTextField.setText(productNameTextField.getText());
        this.quantityTextField.setText(quantityTextField.getText());
        this.pricePiceTextField.setText(pricePiceTextField.getText());
        this.totalAmountTextField.setText(Total_Amu(this.orderIdTextField));
        this.helper();
    }
}