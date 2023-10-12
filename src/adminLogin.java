import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class adminLogin extends JFrame {
    private JPanel mainPanel;
    private JButton ADDUSERButton;
    private JButton EXITButton;
    private JTable table1;

    public adminLogin(String pin_code) {
        add(mainPanel);
        setSize(1200, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ADMIN");
        setVisible(true);
        setLocationRelativeTo(null);

        table1.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "No.", "Last Name", "First Name", "MI", "Email", "Contact", "Address", "Card No.", "PIN Code", "Balance"
                }
        ));
        displayUsers();
        ADDUSERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
    }

    public void displayUsers(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/atm_db?serverTimezone=UTC","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_users");

            while(rs.next()) {
                Integer number = rs.getInt( "id");
                String lastname = rs.getString("firstname");
                String firstname = rs.getString("lastname");
                String mi = rs.getString("mi");
                String email = rs.getString("email");
                String contact = rs.getString("contact");
                String address = rs.getString("address");
                String card_no = rs.getString("card_no");
                Integer pin = rs.getInt("pin_code");
                Double balance = rs.getDouble("balance");

                String tbData [] = {String.valueOf(number), lastname, firstname, mi, email, contact, address, card_no, String.valueOf(pin), String.valueOf(balance)};
                DefaultTableModel tblModel = (DefaultTableModel)table1.getModel();

                tblModel.addRow(tbData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String pin_code = null;
        new adminLogin(pin_code);
    }
}
