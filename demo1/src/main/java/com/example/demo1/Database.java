package com.example.demo1;

import java.sql.*;
import java.text.DecimalFormat;
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
                User.walletID = rs.getInt(1);
                User.Username = rs.getString(2);
                User.Password = rs.getString(3);
                User.PhoneNumber = rs.getString(4);
                User.Email = rs.getString(5);
                User.Firstname = rs.getString(6);
                User.Lastname = rs.getString(7);
                return true;
            }
        }
        return false;
    }
    public static boolean Wallet_exist(int Wallet) throws SQLException {
        Statement stmn = connection().createStatement();
        ResultSet rs = stmn.executeQuery("select * from users");
        while (rs.next()){
            if (Wallet == rs.getInt(1)){
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
            DecimalFormat df = new DecimalFormat("#.00");

        try{
        // Create a statement
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String query = "SELECT " + columnname + " FROM ag WHERE date <= ? ORDER BY date DESC LIMIT 2";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setString(1, formattedDate + " " + formattedTime);
            ResultSet resultSet = preparedStatement.executeQuery();
        double previousPrice = 0.0;
        while (resultSet.next()) {
            double currentPrice = resultSet.getDouble(columnname);
            if (previousPrice != 0.0) {
                double percentChange = ((currentPrice - previousPrice) / previousPrice) * 100.0;
                return (df.format(percentChange) + "%");
            }
            previousPrice = currentPrice;
        }
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }

    public static void currencySwap(String givenC, String takenC, double givenM, double takenM, int walletID) throws SQLException {
        String query = "update wallet set ? = ? , ? = ? where walletID = ?";
        PreparedStatement preparedStatement = connection().prepareStatement(query);
        preparedStatement.setString(1, givenC);
        preparedStatement.setDouble(2, givenM);
        preparedStatement.setString(3, takenC);
        preparedStatement.setDouble(4, takenM);
        preparedStatement.setInt(5, walletID);
        preparedStatement.execute();
    }

    public static boolean WalletCheck(int Wallet) throws SQLException {
        Statement stmn = connection().createStatement();
        ResultSet rs = stmn.executeQuery("select * from users");
        while (rs.next()) {
            if (Wallet == rs.getInt(1)) {
                return true;
            }
        }
        return false;
    }
}

