import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame {
    private JPanel mainPanel;
    private JButton ENTERButton;
    private JButton EXITButton;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JButton CLEARButton;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a8Button;
    private JButton a7Button;
    private JButton a0Button;
    private JButton a9Button;
    private JTextField Output;

    public login(){
        add(mainPanel);
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setVisible(true);
        setLocationRelativeTo(null);

        ButtonGroup optionButton = new ButtonGroup();

        optionButton.add(userRadioButton);
        optionButton.add(adminRadioButton);

        optionButton.setSelected(userRadioButton.getModel(), true);

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
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output.setText("");
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ENTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pin_code = Output.getText();

                if (pin_code.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Insert PIN!", "System Notice", JOptionPane.ERROR_MESSAGE);
                }

                if (userRadioButton.isSelected()) {
                    users = usersDBConn(pin_code);
                    if (users != null) {
                        JOptionPane.showMessageDialog(null, "Succesfully Login!", "System Notice", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        home Home = new home(pin_code);
                        Home.show();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid PIN!", "System Notice", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    admin = adminDBConn(pin_code);
                    if (admin != null) {
                        JOptionPane.showMessageDialog(null, "Succesfully Login!", "System Notice", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        adminLogin admin = new adminLogin(pin_code);
                        admin.show();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid PIN!", "System Notice", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public Users users;
    public Users usersDBConn(String pin_code) {
        Users users = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/atm_db";
        final String USERNAME = "root";
        final String PASSWORD = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM tbl_users WHERE pin_code=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, pin_code);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                users = new Users();
                users.pin_code = resultSet.getString("pin_code");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public Admin admin;
    public Admin adminDBConn(String pin_code) {
        Admin admin = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/atm_db";
        final String USERNAME = "root";
        final String PASSWORD = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM tbl_admin WHERE pin_code=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, pin_code);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                admin = new Admin();
                admin.pin_code = resultSet.getString("pin_code");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    public static void main(String[] args) {
        new login();
    }
}
