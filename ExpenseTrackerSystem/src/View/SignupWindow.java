package View;


import Database.PackageData;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupWindow extends Container {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel repeatPasswordLabel;
    private JLabel phoneNumberLabel;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JPasswordField repeatPasswordText;
    private JTextField phoneNumberText;

    private JButton signUpButton;
    private JButton backButton;

    public SignupWindow() {
        setSize(600, 500);
        setLayout(null);
        setBackground(new Color(173, 216, 230)); // Light blue background color

        //1. Username
        usernameLabel = new JLabel("USERNAME:");
        usernameLabel.setBounds(150, 100, 100, 30);
        add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(250, 100, 200, 30);
        add(usernameText);

        //2. Password
        passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setBounds(150, 150, 100, 30);
        add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(250, 150, 200, 30);
        add(passwordText);

        //3. Repeat of Password
        repeatPasswordLabel = new JLabel("REPEAT:");
        repeatPasswordLabel.setBounds(150, 200, 100, 30);
        add(repeatPasswordLabel);

        repeatPasswordText = new JPasswordField();
        repeatPasswordText.setBounds(250, 200, 200, 30);
        add(repeatPasswordText);

        //4. Phone Number
        phoneNumberLabel = new JLabel("PHONE NUMBER:");
        phoneNumberLabel.setBounds(150, 250, 100, 30);
        add(phoneNumberLabel);

        phoneNumberText = new JTextField();
        phoneNumberText.setBounds(250, 250, 200, 30);
        add(phoneNumberText);

        //5. SignUp Button
        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(200, 300, 120, 40);
        signUpButton.setBackground(new Color(60, 179, 113)); // Medium sea green background color
        signUpButton.setForeground(Color.WHITE); // White text color
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());
                String repeatPassword = new String(repeatPasswordText.getPassword());
                String phoneNumber = phoneNumberText.getText();

                // Register user
                if (password.equals(repeatPassword)) {
                User user = new User(null,username,password,phoneNumber);
                PackageData pd = new PackageData(user, "ADD");
                ///
                Main.connect(pd);

                Main.frame.signupWindow.setVisible(false);
                Main.frame.mainMenuWindow.setVisible(true);
                }
            }
        });
        add(signUpButton);

        //6. Back Button
        backButton = new JButton("Back");
        backButton.setBounds(320, 300, 120, 40);
        backButton.setBackground(new Color(255, 99, 71)); // Tomato background color
        backButton.setForeground(Color.WHITE); // White text color
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close SignUp window
                Main.frame.signupWindow.setVisible(false);
                // Open MainMenu window
                Main.frame.mainMenuWindow.setVisible(true);
            }
        });
        add(backButton);

        // Set font for labels
        Font labelFont = new Font("Verdana", Font.PLAIN, 14);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        repeatPasswordLabel.setFont(labelFont);
        phoneNumberLabel.setFont(labelFont);

        // Set font for buttons
        Font buttonFont = new Font("Verdana", Font.BOLD, 12);
        signUpButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
    }
}




