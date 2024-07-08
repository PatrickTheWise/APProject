package com.example.demo1;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Database {

    public static Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exchange", "root", null);
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean loginCheck(String username, String pass) throws SQLException {
        Statement stmn = connection().createStatement();
        ResultSet rs = stmn.executeQuery("select * from users");
        while (rs.next()){
            if (username.equals(rs.getString(2)) && pass.equals(rs.getString(3))){

                ///inja etelato dakhel class user mirizim  ****************************
                return true;
            }
        }
        return false;
    }

    public static void signedUp(String username, String pass, String firstname, String lastname, String email, String phone) throws SQLException {
        Statement stmn = connection().createStatement();
        boolean isUnique = true;
        ResultSet rs = stmn.executeQuery("select * from users");
        while (rs.next()){
            if (username.equals(rs.getString(2)) || email.equals(rs.getString(5)) || phone.equals(rs.getString(4))){
                isUnique = false;
            }
        }
        if (isUnique){
            int count = 0;
            rs = stmn.executeQuery("select walletID from wallet");
            while (rs.next()){
                count = rs.getInt(1);
            }
            String sql = ("insert into users (walletID, username, pass, phone, mail, firstname, lastname)" + "values(?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement preparedStmt = connection().prepareStatement(sql);
            preparedStmt.setInt (1, ++count);
            preparedStmt.setString (2, username);
            preparedStmt.setString (3, pass);
            preparedStmt.setString (4, phone);
            preparedStmt.setString (5, email);
            preparedStmt.setString (6, firstname);
            preparedStmt.setString (7, lastname);
            preparedStmt.execute();
            sql = ("insert into Wallet (walletID, USD, EUR, Toman, YEN, GBP, Akshe)" + "values(?, ?, ?, ?, ?, ?, ?)");
            preparedStmt = connection().prepareStatement(sql);
            preparedStmt.setInt (1, ++count);
            preparedStmt.setDouble(2, 0);
            preparedStmt.setDouble(3, 0);
            preparedStmt.setDouble(4, 0);
            preparedStmt.setDouble(5, 0);
            preparedStmt.setDouble(6, 0);
            preparedStmt.setDouble(7, 1000);
            preparedStmt.execute();
        }
    }
    public static String changePercentage(String columnname) {
        try{
        // Create a statement
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String query = "SELECT " + columnname + " FROM ag WHERE CONCAT(date, ' ', tima) <= ? ORDER BY CONCAT(date, ' ', tima) DESC LIMIT 2";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setString(1, formattedDate + " " + formattedTime);
            ResultSet resultSet = preparedStatement.executeQuery();
        double previousPrice = 0.0;
        while (resultSet.next()) {
            double currentPrice = resultSet.getDouble(columnname);
            if (previousPrice != 0.0) {
                double percentChange = ((currentPrice - previousPrice) / previousPrice) * 100.0;
                return (percentChange + "%");
            }
            previousPrice = currentPrice;
        }

        // Close resources
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }
}

