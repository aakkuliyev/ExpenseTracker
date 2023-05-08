package View;

import Model.User;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class UserMenu extends Container {
    private JLabel usernameLabel;
    private JButton addExpenseButton;
    private JButton addIncomesButton;
    private JButton reportButton;
    private JButton closeButton;

    public UserMenu(User user) {
        setSize(600, 900);
        setLayout(null);
         // Light green background color

        // Username Label
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(20, 20, 200, 30);
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        usernameLabel.setForeground(new Color(25, 25, 112)); // Midnight blue text color
        add(usernameLabel);

        // Add Expenses Button
        addExpenseButton = new JButton("Add Expenses");
        addExpenseButton.setBounds(150, 100, 300, 60);
        addExpenseButton.setBackground(new Color(255, 99, 71)); // Tomato background color
        addExpenseButton.setIcon(new ImageIcon("path/to/expense_icon.png")); // Set the path to the expense icon
        addExpenseButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Add Expenses window
                Main.frame.expenseMenuWindow.setVisible(true);
                // Close the UserMenu window
                Main.frame.userMenuWindow.setVisible(false);
            }
        });
        add(addExpenseButton);

        // Add Incomes Button
        addIncomesButton = new JButton("Add Incomes");
        addIncomesButton.setBounds(150, 180, 300, 60);
        addIncomesButton.setBackground(new Color(50, 205, 50)); // Lime green background color
        addIncomesButton.setIcon(new ImageIcon("path/to/income_icon.png")); // Set the path to the income icon
        addIncomesButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        addIncomesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Add Incomes window
                Main.frame.incomeMenuWindow.setVisible(true);
                // Close the UserMenu window
                Main.frame.userMenuWindow.setVisible(false);
            }
        });
        add(addIncomesButton);

        // Report Button
        reportButton = new JButton("Report");
        reportButton.setBounds(150, 260, 300, 60);
        reportButton.setBackground(new Color(30, 144, 255)); // Dodger blue background color
        reportButton.setIcon(new ImageIcon("path/to/report_icon.png")); // Set the path to the report icon
        reportButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Report window
                Main.frame.reportMenuWindow.setVisible(true);
                // Close the UserMenu window
                Main.frame.userMenuWindow.setVisible(false);
            }
        });
        add(reportButton);

        // Close Button
        closeButton = new JButton("Close");
        closeButton.setBounds(250, 450, 100, 20);
        closeButton.setBackground(new Color(139, 0, 139)); // Medium purple background color
        closeButton.setForeground(Color.WHITE); // White text color
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the UserMenu window
                Main.frame.userMenuWindow.setVisible(false);
            }
        });
        add(closeButton);
    }

}
