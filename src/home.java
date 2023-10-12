import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class home extends JFrame {
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
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

    public home(String pin_code) {
        add(mainPanel);
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HOME");
        setVisible(true);
        setLocationRelativeTo(null);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                balance Bal = new balance(pin_code);
                Bal.show();
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
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                withdraw wd = new withdraw(pin_code);
                wd.show();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                deposit dp = new deposit(pin_code);
                dp.show();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                changePIN pin = new changePIN(pin_code);
                pin.show();
            }
        });
    }

    public static void main(String[] args) {
        String pin_code = null;
        new home(pin_code);
    }
}
