package View;

import Database.PackageData;
import Model.Expense;
import Model.Income;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IncomeMenu extends Container {
    private User currentUser;
    private Label incomeAmountLabel;
    private Label incomeCurrencyLabel;
    private Label incomeDateLabel;
    private Label incomeSourceLabel;

    private JTextField incomeAmountText;
    private JTextField incomeCurrencyText;
    private JTextField incomeDateText;
    private String[] sources = {"Salary", "Freelance", "Investments", "Rental Income",
            "Gifts", "Pension", "Interest", "Dividends", "Business", "Other"};

    private JComboBox incomeSourceBox;

    private JButton addIncomeButton;
    private JButton backButton;

    private void addIncome(User user, Income income) {
        user.getIncomes().add(income);
        PackageData pd = new PackageData(user.getIncomes(), "ADD");
        Main.connect(pd);
    }

    public IncomeMenu(User user) {
        currentUser = user;
        setSize(600, 500);
        setLayout(null);
        setBackground(new Color(230, 230, 230));

        Font labelFont = new Font("Arial", Font.BOLD, 16);

        // Income Amount
        incomeAmountLabel = new Label("Amount:");
        incomeAmountLabel.setBounds(90, 100, 80, 30);
        incomeAmountLabel.setFont(labelFont);
        add(incomeAmountLabel);

        incomeAmountText = new JTextField();
        incomeAmountText.setBounds(170, 100, 150, 30);
        add(incomeAmountText);

        // Income Currency
        incomeCurrencyLabel = new Label("Currency:");
        incomeCurrencyLabel.setBounds(90, 140, 150, 30);
        incomeCurrencyLabel.setFont(labelFont);
        add(incomeCurrencyLabel);

        incomeCurrencyText = new JTextField();
        incomeCurrencyText.setBounds(170, 140, 150, 30);
        add(incomeCurrencyText);

        // Income Date
        incomeDateLabel = new Label("Date:");
        incomeDateLabel.setBounds(90, 180, 80, 30);
        incomeDateLabel.setFont(labelFont);
        add(incomeDateLabel);

        incomeDateText = new JTextField();
        incomeDateText.setBounds(170, 180, 150, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String todayDate = LocalDate.now().format(formatter);
        incomeDateText.setText(todayDate);
        add(incomeDateText);

        // Income Source
        incomeSourceLabel = new Label("Source:");
        incomeSourceLabel.setBounds(90, 220, 80, 30);
        incomeSourceLabel.setFont(labelFont);
        add(incomeSourceLabel);

        incomeSourceBox = new JComboBox(sources);
        incomeSourceBox.setBounds(170, 220, 150, 30);
        add(incomeSourceBox);

        // Add Income Button
        addIncomeButton = new JButton("Add Income");
        addIncomeButton.setBounds(90, 350, 150, 30);
        addIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double incomeAmount;
                try {
                    incomeAmount = Double.parseDouble(incomeAmountText.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String incomeCurrency = incomeCurrencyText.getText();
                String incomeDate = incomeDateText.getText();
                String incomeSource = (String) incomeSourceBox.getSelectedItem();

                Income incomes = new Income(incomeAmount, incomeCurrency, incomeSource, incomeDate);
                // Add the method to add income
                addIncome(currentUser, incomes);
            }
        });
        add(addIncomeButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(280, 350, 150, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close IncomeMenu window
                Main.frame.incomeMenuWindow.setVisible(false);
                // Open UserMenu window
                Main.frame.userMenuWindow.setVisible(true);
            }
        });
        add(backButton);
    }
}


