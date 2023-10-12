import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class withdraw extends JFrame{
    private JPanel mainPanel;
    private JLabel withdraw;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button3;
    private JButton button1;
    private JButton button2;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a9Button;
    private JButton a8Button;
    private JButton a7Button;
    private JButton a0Button;
    private JButton EXITButton;
    private JButton ENTERButton;
    private JButton CLEARButton;
    private JTextField Output;
    private JLabel total;
    String pass;
    public withdraw(String pin_code) {
        add(mainPanel);
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HOME");
        setVisible(true);
        setLocationRelativeTo(null);

        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a7Button.getText());
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a8Button.getText());
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a9Button.getText());
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a4Button.getText());
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a5Button.getText());
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a6Button.getText());
            }
        });
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a1Button.getText());
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a2Button.getText());
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a3Button.getText());
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText(Output.getText() + a0Button.getText());
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                login lg = new login();
                lg.show();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                home Home = new home(pin_code);
                Home.show();
            }
        });
        ENTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount=Integer.parseInt(Output.getText());

                if(amount>=100000)
                {
                    JOptionPane.showMessageDialog(null,"YOU CAN'T WITHDRAW MORE THAN 50000");
                }
                else{
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","");
                        Statement stmt = null;
                        ResultSet rs = null;
                        String SQL = "SELECT * FROM tbl_users WHERE pin_code like '%"+(pin_code)+"%'";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(SQL);
                        if (rs.next()!= rs.isAfterLast()) {
                            String pin_code = rs.getString("pin_code");
                            int balance = rs.getInt("balance");
                            if(balance>amount)
                            {
                                int temp=balance-amount;

                                System.out.println(temp);
                                withdraw.setText(""+amount);
                                total.setText(""+temp);

                                String SQL1 = "SELECT * FROM tbl_users";
                                stmt = con.createStatement();
                                rs = stmt.executeQuery(SQL1);
                                String strSQL = "Update tbl_users set balance = "+(temp)+" where pin_code = " + (pin_code);
                                int rowsEffected = stmt.executeUpdate(strSQL);
                                if (rowsEffected == 0){
                                    JOptionPane.showMessageDialog(null, "Transaction Failed");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"Transaction Successful");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Insufficient Balance");
                            }
                            con.close();
                            stmt.close();
                            rs.close();
                        }
                    }
                    catch(Exception ae){
                        JOptionPane.showMessageDialog(null, ae.getMessage());
                    }
                }
            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText("");
            }
        });
    }

    public static void main(String args[]) {
        String pin_code = null;
        new withdraw(pin_code);
    }
}
