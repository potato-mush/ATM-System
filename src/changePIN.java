import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class changePIN extends JFrame{
    private JPanel mainPanel;
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
    private JButton BACKButton;
    private JButton ENTERButton;
    private JButton CLEARButton;
    private JPasswordField confirmPin;
    private JPasswordField oldPin;
    private JPasswordField newPin;

    public changePIN(String pin_code) {
        add(mainPanel);
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HOME");
        setVisible(true);
        setLocationRelativeTo(null);

        oldPin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int length = String.valueOf(oldPin.getText()).length();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    oldPin.setEditable(true);
                    if (length == 0) {
                        oldPin.setEditable(true);
                    } else if (length == 6){
                        oldPin.setEditable(false);
                    }
                } else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    oldPin.setEditable(true);
                } else {
                    oldPin.setEditable(false);
                }
            }
        });
        newPin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ae) {
                int length = String.valueOf(newPin.getText()).length();
                if (ae.getKeyChar() >= '0' && ae.getKeyChar() <= '9') {
                    newPin.setEditable(true);
                    if (length == 0) {
                        newPin.setEditable(true);
                    } else if (length == 6){
                        newPin.setEditable(false);
                    }
                } else if (ae.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    newPin.setEditable(true);
                } else {
                    newPin.setEditable(false);
                }
            }
        });
        confirmPin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ae) {
                int length = String.valueOf(confirmPin.getText()).length();
                if (ae.getKeyChar() >= '0' && ae.getKeyChar() <= '9') {
                    confirmPin.setEditable(true);
                    if (length == 0) {
                        confirmPin.setEditable(true);
                    } else if (length == 6){
                        confirmPin.setEditable(false);
                    }
                } else if (ae.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    confirmPin.setEditable(true);
                } else {
                    confirmPin.setEditable(false);
                }
            }
        });
        ENTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String old_pass=new String(oldPin.getPassword());
                String new_pass=new String(newPin.getPassword());
                String re_new_pass=new String(confirmPin.getPassword());

                if(old_pass.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"ENTER OLD PASSWORD");
                }
                else if(new_pass.isEmpty() && re_new_pass.isEmpty())
                {

                    JOptionPane.showMessageDialog(null,"ENTER NEW PASSWORD and ReType NEW PASSWORD");
                }
                else if(new_pass.equals(re_new_pass))
                {
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","");
                        Statement stmt = null;
                        ResultSet rs = null;

                        String SQL = "SELECT * FROM tbl_users WHERE pin_code like '%"+(old_pass)+"%'";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(SQL);

                        if (rs.next()!= rs.isAfterLast()) {
                            String id = rs.getString("id");
                            String lastname = rs.getString("lastname");
                            String firstname = rs.getString("firstname");
                            String mi = rs.getString("mi");
                            String email = rs.getString("email");
                            String contact = rs.getString("contact");
                            String address = rs.getString("address");
                            String card_no = rs.getString("card_no");
                            String pin_code = rs.getString("pin_code");
                            String balance = rs.getString("balance");
                            if(pin_code.equals(old_pass))
                            {
                                String SQL1 = "SELECT * FROM tbl_users";
                                stmt = con.createStatement();
                                rs = stmt.executeQuery(SQL1);



                                String strSQL = "Update tbl_users set pin_code = "+(new_pass)+" where pin_code = " + (old_pass);
                                int rowsEffected = stmt.executeUpdate(strSQL);
                                if (rowsEffected == 0){
                                    JOptionPane.showMessageDialog(null, "Password Is Not Changed ");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"Password Changed Successfully");
                                }

                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"PASSWORD DOESN'T MATCHED");
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
                else
                {
                    JOptionPane.showMessageDialog(null,"New Password Doesn't Matched With RE-Type Password");
                }
                oldPin.setText("");
                newPin.setText("");
                confirmPin.setText("");
            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oldPin.setText("");
                newPin.setText("");
                confirmPin.setText("");
            }
        });
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                home Home = new home(pin_code);
                Home.show();
            }
        });
    }

    public static void main(String[] args) {
        String pin_code = null;
        new changePIN(pin_code);
    }
}
