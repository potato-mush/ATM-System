import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class addUser extends JFrame {
    private JPanel mainPanel;
    private JButton REGISTERButton;
    private JButton CANCELButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JLabel col1;
    private JLabel col2;
    private JLabel col3;
    private JLabel dash1;
    private JLabel dash2;
    private JLabel zero;
    private JPasswordField passwordField1;
    Random ran = new Random();
    int first = ran.nextInt(999-100)+100;
    int mid = ran.nextInt(9999-1000)+1000;
    int last = ran.nextInt(999-100)+100;

    public addUser(String pin_code) {
        add(mainPanel);
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("REGISTER");
        setVisible(true);
        setLocationRelativeTo(null);

        col1.setText("" + String.format("%3d", first));
        col2.setText("" + String.format("%4d", mid));
        col3.setText("" + String.format("%3d", last));

        textField5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int length = String.valueOf(textField5.getText()).length();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    textField5.setEditable(true);
                    if (length == 0) {
                        textField5.setEditable(true);
                    } else if (length == 9){
                        textField5.setEditable(false);
                    }
                } else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    textField5.setEditable(true);
                } else {
                    textField5.setEditable(false);
                }
            }
        });

        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int length = String.valueOf(passwordField1.getText()).length();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    passwordField1.setEditable(true);
                    if (length < 6) {
                        passwordField1.setEditable(true);
                    } else if (length == 6){
                        passwordField1.setEditable(false);
                    }
                } else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    passwordField1.setEditable(true);
                } else {
                    passwordField1.setEditable(false);
                }
            }
        });

        textField7.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int length = String.valueOf(textField7.getText()).length();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    textField7.setEditable(true);
                    if (length < 10) {
                        textField7.setEditable(true);
                    } else if (length == 10){
                        textField7.setEditable(false);
                    }
                } else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    textField7.setEditable(true);
                } else {
                    textField7.setEditable(false);
                }
            }
        });


        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastname = textField1.getText();
                String firstname = textField2.getText();
                String mi = textField3.getText();
                String email = textField4.getText();
                String contact = zero.getText() + textField5.getText();
                String address = textField6.getText();
                String card_no = col1.getText() + dash1.getText() + col2.getText() + dash2.getText() + col3.getText();
                Integer pin_code = Integer.valueOf(passwordField1.getText());
                Double balance = Double.valueOf(textField7.getText());

                users = regUser(lastname, firstname, mi, email, contact, address, card_no, pin_code, balance);

                if (lastname.isEmpty() || firstname.isEmpty() || mi.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || pin_code.equals(null) || balance.isNaN()){
                    JOptionPane.showMessageDialog(null, "Fill up all fields", "System Notice", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (users != null){
                    JOptionPane.showMessageDialog(null, "Account successfully created!", "System Notice", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create account!", "System Notice", JOptionPane.ERROR_MESSAGE);
                }

                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                passwordField1.setText("");
            }
        });
        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                adminLogin al = new adminLogin(pin_code);
                al.show();
            }
        });
    }
    public Users users;
    public Users regUser(String lastname, String firstname, String mi, String email, String contact, String address, String card_no, Integer pin_code, Double balance){
        Users users = null;
        final String url = "jdbc:mysql://localhost:3306/atm_db";
        final String username = "root";
        final String password = "";

        try {
            Connection conn = DriverManager.getConnection(url , username, password);
            String sql = "INSERT INTO tbl_users (lastname, firstname, mi, email, contact, address, card_no, pin_code, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, mi);
            ps.setString(4, email);
            ps.setString(5, contact);
            ps.setString(6, address);
            ps.setString(7, card_no);
            ps.setInt(8, pin_code);
            ps.setDouble(9, balance);

            int addData = ps.executeUpdate();
            if (addData > 0){
                users = new Users();
                users.lastname = lastname;
                users.firstname = firstname;
                users.mi = mi;
                users.email = email;
                users.contact = contact;
                users.address = address;
                users.card_no = card_no;
                users.pin_code = String.valueOf(pin_code);
                users.balance = balance;
            }

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void main(String[] args) {
        String pin_code = null;
        new addUser(pin_code);
    }
}
