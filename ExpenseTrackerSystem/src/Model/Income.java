package Model;

import java.io.Serializable;

public class Income implements Serializable {
    private int id;
    private double amount;
    private String currency;
    private String category;
    private String date;

    public Income(int id, double amount, String currency, String category, String date) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.date = date;
    }

    public Income(double amount, String currency, String category, String date) {
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
