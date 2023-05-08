package View;

import Database.PackageData;
import Model.User;

import javax.swing.*;
import java.awt.*;

public class ReportMenu extends Container {
    public static JTextArea text;
    private JButton backButton;
    private JButton reportButton;

    public ReportMenu(User user) {
        setSize(600, 500);
        setLayout(null);
        setBackground(new Color(230, 230, 230));

        // Report Button
        reportButton = new JButton("Generate Report");
        reportButton.setBounds(90, 400, 150, 30);
        reportButton.addActionListener(e -> {
            // Add the code to generate the report and update the text area
            PackageData pd = new PackageData("REPORT");
            Main.connect(pd);
        });
        add(reportButton);

        // Text Area
        text = new JTextArea();
        text.setBounds(50, 50, 500, 300);
        text.setEditable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 14));
        add(text);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(400, 400, 150, 30);
        backButton.addActionListener(e -> {
            // Close ReportMenu window
            Main.frame.reportMenuWindow.setVisible(false);
            Main.frame.userMenuWindow.setVisible(true);
            // Open UserMenu window
        });
        add(backButton);
    }
}

