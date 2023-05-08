package View;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends Container {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameText;
    private JPasswordField passwordText;

    private JButton loginButton;
    private JButton backButton;

    public LoginWindow() {
        setSize(600, 500);
        setLayout(null);
        setBackground(new Color(173, 216, 230)); // Light blue background color

        //1. Username
        usernameLabel = new JLabel("USERNAME:");
        usernameLabel.setBounds(150, 150, 100, 30);
        add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(250, 150, 200, 30);
        add(usernameText);

        //2. Password
        passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setBounds(150, 200, 100, 30);
        add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(250, 200, 200, 30);
        add(passwordText);

        //3. Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(200, 250, 120, 40);
        loginButton.setBackground(new Color(60, 179, 113)); // Medium sea green background color
        loginButton.setForeground(Color.WHITE); // White text color
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());

                // Authenticate user
                boolean isAuthenticated = Main.authenticate(username, password);

                if (!isAuthenticated) {
                    User user = new User(username, password);

                    // Create a UserMenu instance and add it to the MainFrame
                    Main.frame.userMenuWindow = new UserMenu(user);
                    Main.frame.add(Main.frame.userMenuWindow);
                    Main.frame.userMenuWindow.setVisible(false);

// Create an ExpenseMenu instance and add it to the MainFrame
                    Main.frame.expenseMenuWindow = new ExpenseMenu(user);
                    Main.frame.add(Main.frame.expenseMenuWindow);
                    Main.frame.expenseMenuWindow.setVisible(false);

// Create an IncomeMenu instance and add it to the MainFrame
                    Main.frame.incomeMenuWindow = new IncomeMenu(user);
                    Main.frame.add(Main.frame.incomeMenuWindow);
                    Main.frame.incomeMenuWindow.setVisible(false);

// Create a ReportMenu instance and add it to the MainFrame
                    Main.frame.reportMenuWindow = new ReportMenu(user);
                    Main.frame.add(Main.frame.reportMenuWindow);
                    Main.frame.reportMenuWindow.setVisible(false);




                    // Close LoginWindow
                    Main.frame.loginWindow.setVisible(false);
                    // Open UserMenuWindow
                    Main.frame.userMenuWindow.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(loginButton);

        //4. Back Button
        backButton = new JButton("Back");
        backButton.setBounds(320, 250, 120, 40);
        backButton.setBackground(new Color(255, 99, 71)); // Tomato background color
        backButton.setForeground(Color.WHITE); // White text color
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close LoginWindow
                Main.frame.loginWindow.setVisible(false);
                // Open MainMenuWindow
                Main.frame.mainMenuWindow.setVisible(true);
            }
        });
        add(backButton);

        // Set font for labels
        Font labelFont = new Font("Verdana", Font.PLAIN, 14);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        // Set font for buttons
        Font buttonFont = new Font("Verdana", Font.BOLD, 12);
        loginButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
    }
}


