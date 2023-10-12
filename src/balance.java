import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class balance extends JFrame{
    private JPanel mainPanel;
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
    private JLabel cBal;

    public balance(String pin_code) {
        add(mainPanel);
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HOME");
        setVisible(true);
        setLocationRelativeTo(null);

        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","");
                    Statement stmt = null;
                    ResultSet rs = null;

                    String SQL = "SELECT * FROM tbl_users WHERE pin_code like '%"+(pin_code)+"%'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(SQL);

                    if (rs.next()!= rs.isAfterLast()) {
                        String pin_code = rs.getString("pin_code");
                        String balance = rs.getString("balance");
                        if(pin_code.equals(pin_code))
                        {
                            cBal.setText(""+balance);
                        }
                    }
                }
                catch(Exception ae){
                    JOptionPane.showMessageDialog(null, ae.getMessage());

                }
            }
        });
    }

    public static void main(String args[]) {
        String pin_code = null;
        new balance(pin_code);
    }

}
