package Database;

import Model.Expense;
import Model.Income;
import Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DBManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        connect();
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        try {
            String query = "INSERT INTO users (id, name, password, phoneNumber) VALUES (NULL, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPhoneNumber());
            int result = pstmt.executeUpdate();
            pstmt.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User authenticateUser(String username, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("password"), resultSet.getString("phoneNumber"));
            }
            resultSet.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean addExpense(User user, Expense expense) {
        try {
            String query = "INSERT INTO expenses (id, amount, currency, category, date, user_id) VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, expense.getAmount());
            pstmt.setString(2, expense.getCurrency());
            pstmt.setString(3, expense.getCategory());
            pstmt.setString(4, expense.getDate());
            pstmt.setInt(5, user.getId());
            int result = pstmt.executeUpdate();
            pstmt.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addIncome(User user, Income income) {
        try {
            String query = "INSERT INTO incomes (id, amount, currency, category, date, user_id) VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, income.getAmount());
            pstmt.setString(2, income.getCurrency());
            pstmt.setString(3, income.getCategory());
            pstmt.setString(4, income.getDate());
            pstmt.setInt(5, user.getId());
            int result = pstmt.executeUpdate();
            pstmt.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Expense> getExpenses(User user) {
        List<Expense> expenses = new ArrayList<>();
        try {
            String query = "SELECT * FROM expenses WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, user.getId());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Expense expense = new Expense(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("currency"),
                        resultSet.getString("category"),
                        resultSet.getString("date")
                );
                expenses.add(expense);
            }
            resultSet.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public List<Income> getIncomes(User user) {
        List<Income> incomes = new ArrayList<>();
        try {
            String query = "SELECT * FROM incomes WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, user.getId());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Income income = new Income(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("currency"),
                        resultSet.getString("category"),
                        resultSet.getString("date")
                );
                incomes.add(income);
            }
            resultSet.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomes;
    }

    public String generateReport(User user) {
        StringBuilder report = new StringBuilder();
        List<Expense> expenses = getExpenses(user);
        List<Income> incomes = getIncomes(user);

        double totalExpenses = 0;
        double totalIncomes = 0;

        report.append("Expenses:\n");
        for (Expense expense : expenses) {
            report.append(String.format("%s - %s - %s - %s\n", expense.getDate(), expense.getCategory(), expense.getAmount(), expense.getCurrency()));
            totalExpenses += expense.getAmount();
        }

        report.append("\nIncomes:\n");
        for (Income income : incomes) {
            report.append(String.format("%s - %s - %s - %s\n", income.getDate(), income.getCategory(), income.getAmount(), income.getCurrency()));
            totalIncomes += income.getAmount();
        }

        report.append(String.format("\nTotal Expenses: %s\nTotal Incomes: %s\nNet Income: %s", totalExpenses, totalIncomes, totalIncomes - totalExpenses));
        return report.toString();
    }
}


