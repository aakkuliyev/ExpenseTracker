package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Container {
    private JButton loginButton;
    private JButton signUpButton;
    private JButton closeButton;

    public MainMenu() {

        setSize(600, 700);
        setLayout(null);

        // Улучшение графического интерфейса
        JLabel titleLabel = new JLabel("Registration System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(120, 30, 400, 40);
        add(titleLabel);


        //Кнопка Login
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 120, 400, 40);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open UserRegistrationSystem.Login window
                Main.frame.loginWindow.setVisible(true);
                //Close MainPage.Main window
                Main.frame.mainMenuWindow.setVisible(false);
            }
        });
        add(loginButton);

        //кнопка SignUp
        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 180, 400, 40);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open UserRegistrationSystem.SignUp window
                Main.frame.signupWindow.setVisible(true);
                //Close MainPage.Main window
                Main.frame.mainMenuWindow.setVisible(false);
            }
        });
        add(signUpButton);

        //кнопка Close
        closeButton = new JButton("Close");
        closeButton.setBounds(450, 450, 80, 30);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Close UserRegistrationSystem.MainMenu window
                System.exit(0);
            }
        });
        add(closeButton);
    }
}
