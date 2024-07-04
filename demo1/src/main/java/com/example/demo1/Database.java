package com.example.demo1;

import java.sql.*;

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
            if (username.equals(rs.getString(1)) && pass.equals(rs.getString(2))){
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
            if (username.equals(rs.getString(1)) || email.equals(rs.getString(4)) || phone.equals(rs.getString(3))){
                isUnique = false;
            }
        }
        if (isUnique){
            String sql = ("insert into users (username, password, phone, mail, firstname, lastname)" + "values( ?, ?, ?, ?, ?, ?)");
            PreparedStatement preparedStmt = connection().prepareStatement(sql);
            preparedStmt.setString (1, username);
            preparedStmt.setString (2, pass);
            preparedStmt.setString (3, phone);
            preparedStmt.setString (4, email);
            preparedStmt.setString (5, firstname);
            preparedStmt.setString (6, lastname);
            preparedStmt.execute();
        }
    }
}
