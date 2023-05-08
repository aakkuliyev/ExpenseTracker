package View;

import Database.PackageData;
import Model.Expense;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ExpenseMenu extends Container {
    private User currentUser;
    private Label expenseAmountLabel;
    private Label expenseCurrencyLabel;
    private Label expenseDateLabel;
    private Label expenseCategoryLabel;


    private JTextField expenseAmountText;
    private JTextField expenseCurrencyText;
    private JTextField expenseDateText;
    private String[] categories = {"Groceries", "Utilities", "Transportation", "Entertainment",
            "Clothing", "Personal care", "Dining out", "Gifts", "Travel", "Other"};

    private JComboBox expenseCategoryBox;

    private JButton addExpenseButton;
    private JButton backButton;
    private JButton saveExpensesButton;

    private void addExpense(User user, Expense expense) {
        user.getExpenses().add(expense);
        PackageData pd = new PackageData("ADD", user.getExpenses());
        Main.connect(pd);
    }

    public ExpenseMenu(User user) {
        currentUser = user;
        setSize(600, 500);
        setLayout(null);
        setBackground(new Color(230, 230, 230));

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Expense Amount
        expenseAmountLabel = new Label("Amount:");
        expenseAmountLabel.setBounds(90, 100, 80, 30);
        add(expenseAmountLabel);

        expenseAmountText = new JTextField();
        expenseAmountText.setBounds(170, 100, 150, 30);
        add(expenseAmountText);

        // Expense Currency
        expenseCurrencyLabel = new Label("Currency:");
        expenseCurrencyLabel.setBounds(90, 140, 150, 30);
        add(expenseCurrencyLabel);

        expenseCurrencyText = new JTextField();
        expenseCurrencyText.setBounds(170, 140, 150, 30);
        add(expenseCurrencyText);

        // Expense Date
        expenseDateLabel = new Label("Date:");
        expenseDateLabel.setBounds(90, 180, 80, 30);
        add(expenseDateLabel);

        expenseDateText = new JTextField();
        expenseDateText.setBounds(170, 180, 150, 30);
        add(expenseDateText);

        // Expense Category
        expenseCategoryLabel = new Label("Category:");
        expenseCategoryLabel.setBounds(90, 220, 80, 30);
        add(expenseCategoryLabel);

        expenseCategoryBox = new JComboBox(categories);
        expenseCategoryBox.setBounds(170, 220, 150, 30);
        add(expenseCategoryBox);

        // Add Expense Button
        addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setBounds(90, 350, 150, 40);
        addExpenseButton.setBackground(new Color(59, 89, 182));
        addExpenseButton.setForeground(Color.WHITE);
        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double expenseAmount;
                try {
                    expenseAmount = Double.parseDouble(expenseAmountText.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String expenseCurrency = expenseCurrencyText.getText();
                String expenseDate = expenseDateText.getText();
                String expenseCategory = (String) expenseCategoryBox.getSelectedItem();

                Expense expenses = new Expense(expenseAmount, expenseCurrency, expenseCategory, expenseDate);
                addExpense(currentUser, expenses);
            }
        });
        add(addExpenseButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(280, 350, 150, 40);
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close ExpenseMenu window
                Main.frame.expenseMenuWindow.setVisible(false);
                // Open UserMenu window
                Main.frame.userMenuWindow.setVisible(true);
            }
        });
        add(backButton);

        // set the default value for the expenseDateText field to the current date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);
        expenseDateText.setText(formattedDate);
    }
}
