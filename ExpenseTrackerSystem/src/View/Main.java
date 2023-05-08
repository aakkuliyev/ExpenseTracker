package View;

import Database.PackageData;
import Model.Expense;
import Model.Income;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {
    public static void connect(PackageData pd){
        try {
            Socket socket = new Socket("127.0.0.1",888);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Ориентация по OperationType
            if (pd.getOperationType().equals("ADD")){
                outputStream.writeObject(pd);
            }
            else if (pd.getOperationType().equals("REPORT")){
                outputStream.writeObject(pd);
                PackageData infoFromServer = (PackageData) inputStream.readObject();

                ArrayList<Expense> reportExpenseList = infoFromServer.getUser().getExpenses();
                String expenses = "";
                for (int i = 0; i < reportExpenseList.size(); i++){
                    expenses += reportExpenseList.get(i) + "\n";
                }

                ArrayList<Income> reportIncomeList = infoFromServer.getUser().getIncomes();
                String incomes = "";
                for (int i = 0; i < reportIncomeList.size(); i++){
                    incomes += reportIncomeList.get(i) + "\n";
                }

                ReportMenu.text.append(expenses);
                ReportMenu.text.append(incomes);
            }
            inputStream.close();
            outputStream.close();
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean authenticate(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String dbUsername = "your_db_username";
        String dbPassword = "your_db_password";

        try {
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement statement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return password.equals(storedPassword);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static MainFrame frame;
    public static void main(String[] args) {
        frame = new MainFrame();
        frame.setVisible(true);
    }
}