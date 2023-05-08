package View;

import Model.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public ExpenseMenu expenseMenuWindow;
    public IncomeMenu incomeMenuWindow;
    public LoginWindow loginWindow;
    public MainMenu mainMenuWindow;
    public ReportMenu reportMenuWindow;
    public SignupWindow signupWindow;
    public UserMenu userMenuWindow;

    public MainFrame() {
        setSize(700, 500);
        setTitle("Expense Tracker");

        mainMenuWindow = new MainMenu();
        add(mainMenuWindow);

        signupWindow = new SignupWindow();
        add(signupWindow);
        signupWindow.setVisible(false);

        loginWindow = new LoginWindow();
        loginWindow.setBackground(new Color(144, 238, 144));
        loginWindow.repaint();
        add(loginWindow);
        loginWindow.setVisible(false);

        // userMenuWindow and expenseMenuWindow, incomeMenuWindow and reportMenuWindow will be created after successful login

    }
}
