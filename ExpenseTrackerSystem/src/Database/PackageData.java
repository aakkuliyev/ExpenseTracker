package Database;

import Model.Expense;
import Model.Income;
import Model.User;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private User user;
    private String operationType;
    private ArrayList<Expense> expenses;
    private ArrayList<Income> incomes;

    public PackageData(User user, String operationType) {//send to server info about user
        this.user = user;
        this.operationType = operationType;
    }

    public PackageData(String operationType) {//get a report
        this.operationType = operationType;
    }

    public PackageData(String operationType, ArrayList<Expense> expenses) {
        this.operationType = operationType;
        this.expenses = new ArrayList<>();
    }

    public PackageData(ArrayList<Income> incomes, String operationType) {
        this.operationType = operationType;
        this.incomes = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
