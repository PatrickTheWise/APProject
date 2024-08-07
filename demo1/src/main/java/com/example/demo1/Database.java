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
        while (rs.next()) {
            if (username.equals(rs.getString(2)) && pass.equals(rs.getString(3))) {
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
        while (rs.next()) {
            if (Wallet == rs.getInt(1)) {
                return true;
            }
        }
        return false;
    }

    public static void signedUp(String username, String pass, String firstname, String lastname, String email, String phone) throws SQLException {
        Statement stmn = connection().createStatement();
        boolean isUnique = true;
        ResultSet rs = stmn.executeQuery("select * from users");
        while (rs.next()) {
            if (username.equals(rs.getString(2)) || email.equals(rs.getString(5)) || phone.equals(rs.getString(4))) {
                isUnique = false;
            }
        }
        if (isUnique) {
            int count = 0;
            rs = stmn.executeQuery("select walletID from wallet");
            while (rs.next()) {
                count = rs.getInt(1);
            }
            String sql = ("insert into users (walletID, username, pass, phone, mail, firstname, lastname)" + "values(?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement preparedStmt = connection().prepareStatement(sql);
            preparedStmt.setInt(1, ++count);
            preparedStmt.setString(2, username);
            preparedStmt.setString(3, pass);
            preparedStmt.setString(4, phone);
            preparedStmt.setString(5, email);
            preparedStmt.setString(6, firstname);
            preparedStmt.setString(7, lastname);
            preparedStmt.execute();
            sql = ("insert into Wallet (walletID, USD, EUR, Toman, YEN, GBP, Akshe)" + "values(?, ?, ?, ?, ?, ?, ?)");
            preparedStmt = connection().prepareStatement(sql);
            preparedStmt.setInt(1, ++count);
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

        try {
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
        if (givenC.equals("USD") && takenC.equals("YEN")) {
            String query = "update wallet set USD = ? , YEN = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("USD") && takenC.equals("Toman")) {
            String query = "update wallet set USD = ? , Toman = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("USD") && takenC.equals("GBP")) {
            String query = "update wallet set USD = ? , GBP = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("USD") && takenC.equals("EUR")) {
            String query = "update wallet set USD = ? , EUR = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("GBP") && takenC.equals("YEN")) {
            String query = "update wallet set GBP = ? , YEN = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("GBP") && takenC.equals("EUR")) {
            String query = "update wallet set GBP = ? , EUR = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("GBP") && takenC.equals("Toman")) {
            String query = "update wallet set GBP = ? , Toman = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("GBP") && takenC.equals("USD")) {
            String query = "update wallet set GBP = ? , USD = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("YEN") && takenC.equals("USD")) {
            String query = "update wallet set YEN = ? , USD = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("YEN") && takenC.equals("Toman")) {
            String query = "update wallet set YEN = ? , Toman = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("Yen") && takenC.equals("GBP")) {
            String query = "update wallet set YEN = ? , GBP = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("YEN") && takenC.equals("EUR")) {
            String query = "update wallet set YEN = ? , EUR = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("EUR") && takenC.equals("USD")) {
            String query = "update wallet set EUR = ? , USD = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("EUR") && takenC.equals("YEN")) {
            String query = "update wallet set EUR = ? , YEN = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("EUR") && takenC.equals("GBP")) {
            String query = "update wallet set EUR = ? , GBP = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("EUR") && takenC.equals("Toman")) {
            String query = "update wallet set EUR = ? , Toman = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("Toman") && takenC.equals("EUR")) {
            String query = "update wallet set Toman = ? , EUR = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("Toman") && takenC.equals("USD")) {
            String query = "update wallet set Toman = ? , USD = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("Toman") && takenC.equals("GBP")) {
            String query = "update wallet set Toman = ? , GBP = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        } else if (givenC.equals("Toman") && takenC.equals("YEN")) {
            String query = "update wallet set Toman = ? , YEN = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, givenM);
            preparedStatement.setDouble(2, takenM);
            preparedStatement.setInt(3, walletID);
            preparedStatement.execute();
        }
    }

    public static void profileUpdater(String field, String updated, int walletID) throws SQLException {
        if (field.equals("phone")) {
            String query = "update users set phone = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setString(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.executeUpdate();
        } else if (field.equals("mail")) {
            String query = "update users set mail = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setString(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.executeUpdate();
        } else if (field.equals("firstname")) {
            String query = "update users set firstname = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setString(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.executeUpdate();
        } else if (field.equals("lastname")) {
            String query = "update users set lastname = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setString(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.executeUpdate();
        } else if (field.equals("pass")) {
            String query = "update users set pass = ? where walletID = ?";
            PreparedStatement preparedStatement = connection().prepareStatement(query);
            preparedStatement.setString(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.executeUpdate();
        }

    }

    public static void TransferConfirmed(String field, double updated, int walletID, int walletID2) throws SQLException {
        if (field.equals("USD")) {
            String query = "update wallet set USD = USD - ? where walletID = ?";
            connection().prepareStatement(query);
            PreparedStatement preparedStatement;
            preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.execute();
            String query2 = "update wallet set USD = USD + ? where walletID = ?";
            preparedStatement = connection().prepareStatement(query2);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID2);
            preparedStatement.execute();
        }
        else if (field.equals("GBP")) {
            String query = "update wallet set GBP = GBP - ? where walletID = ?";
            connection().prepareStatement(query);
            PreparedStatement preparedStatement;
            preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.execute();
            String query2 = "update wallet set GBP = GBP + ? where walletID = ?";
            preparedStatement = connection().prepareStatement(query2);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID2);
            preparedStatement.execute();
        }
        else if (field.equals("EUR")) {
            String query = "update wallet set EUR = EUR - ? where walletID = ?";
            connection().prepareStatement(query);
            PreparedStatement preparedStatement;
            preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.execute();
            String query2 = "update wallet set EUR = EUR + ? where walletID = ?";
            preparedStatement = connection().prepareStatement(query2);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID2);
            preparedStatement.execute();
        }
        else if (field.equals("YEN")) {
            String query = "update wallet set YEN = YEN - ? where walletID = ?";
            connection().prepareStatement(query);
            PreparedStatement preparedStatement;
            preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.execute();
            String query2 = "update wallet set YEN = YEN + ? where walletID = ?";
            preparedStatement = connection().prepareStatement(query2);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID2);
            preparedStatement.execute();
        }
        else if (field.equals("TOMAN")) {
            String query = "update wallet set TOMAN = TOMAN - ? where walletID = ?";
            connection().prepareStatement(query);
            PreparedStatement preparedStatement;
            preparedStatement = connection().prepareStatement(query);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID);
            preparedStatement.execute();
            String query2 = "update wallet set TOMAN = TOMAN + ? where walletID = ?";
            preparedStatement = connection().prepareStatement(query2);
            preparedStatement.setDouble(1, updated);
            preparedStatement.setInt(2, walletID2);
            preparedStatement.execute();
        }
    }

    public static void updateWallet() throws SQLException {
        String query = "select * from wallet where walletID = ?";
        PreparedStatement preparedStatement = connection().prepareStatement(query);
        preparedStatement.setInt(1, User.walletID);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            User.GBP = rs.getDouble(6);
            User.AKSHE = rs.getDouble(7);
            User.USD = rs.getDouble(2);
            User.YEN = rs.getDouble(5);
            User.TMN = rs.getDouble(4);
            User.EUR = rs.getDouble(3);
        }
    }

    public static void exchangeSQL(Boolean sorb, String user, String currency, double amount) throws SQLException {
        int orderID = 0, walletID = 0;
        boolean exchangeDone = false;
        Statement stmt = connection().createStatement();
        int count = 0;
        ResultSet RS = stmt.executeQuery("select orderID from orders");
        while (RS.next()) {
            count = RS.getInt(1);
        }
        String sql = "insert into orders(sorb, username, dealuser, currency, amount, orderID, walletID) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection().prepareStatement(sql);
        preparedStatement.setBoolean(1, sorb);
        preparedStatement.setString(2, user);
        preparedStatement.setString(3, null);
        preparedStatement.setString(4, currency);
        preparedStatement.setDouble(5, amount);
        preparedStatement.setInt(6, ++count);
        preparedStatement.setInt(7, User.walletID);
        preparedStatement.execute();
        String query = "select * from orders";
        Statement statement = connection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            exchangeDone = false;
            if (rs.getDouble(5) == amount && rs.getString(4).equals(currency) && sorb == !rs.getBoolean(1) && rs.getString(3).equals(null)){
                orderID = rs.getInt(6);
                exchangeDone = true;
                walletID = rs.getInt(7);
            }
            if (exchangeDone){
                String sql2 = "update orders set dealuser = ? where orderID = ?";
                PreparedStatement ps = connection().prepareStatement(sql2);
                ps.setString(1, user);
                ps.setInt(2, orderID);
                ps.execute();
                String sql3 = "update orders set dealuser = ? where orderID = ?";
                ps = connection().prepareStatement(sql3);
                ps.setString(1, rs.getString(2));
                ps.setInt(2, ++count);
                ps.execute();


                if (currency.equals("USD")) {
                    String sql4 = "update wallet set USD = USD + ?, Akshe = Akshe + ? where walletID = ?";
                    ps = connection().prepareStatement(sql4);
                    if (sorb) {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -7.9 * amount);
                    } else {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 7.9 * amount);
                    }
                    ps.setInt(3, User.walletID);
                    ps.execute();
                    if (sorb) {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 7.9 * amount);
                    } else {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -7.9 * amount);
                    }
                    ps.setInt(3, walletID);
                    ps.execute();
                }

                else if (currency.equals("EUR")) {
                    String sql4 = "update wallet set EUR = EUR + ?, Akshe = Akshe + ? where walletID = ?";
                    ps = connection().prepareStatement(sql4);
                    if (sorb) {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -0.07 * amount);
                    } else {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 0.07 * amount);
                    }
                    ps.setInt(3, User.walletID);
                    ps.execute();
                    if (sorb) {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 0.07 * amount);
                    } else {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -0.07 * amount);
                    }
                    ps.setInt(3, walletID);
                    ps.execute();
                }

                else if (currency.equals("TMN")) {
                    String sql4 = "update wallet set Toman = Toman + ?, Akshe = Akshe + ? where walletID = ?";
                    ps = connection().prepareStatement(sql4);
                    if (sorb) {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -590.678 * amount);
                    } else {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 590.678 * amount);
                    }
                    ps.setInt(3, User.walletID);
                    ps.execute();
                    if (sorb) {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 590.687 * amount);
                    } else {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -590.678 * amount);
                    }
                    ps.setInt(3, walletID);
                    ps.execute();
                }

                else if (currency.equals("YEN")) {
                    String sql4 = "update wallet set YEN = YEN + ?, Akshe = Akshe + ? where walletID = ?";
                    ps = connection().prepareStatement(sql4);
                    if (sorb) {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -354.34 * amount);
                    } else {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 354.34 * amount);
                    }
                    ps.setInt(3, User.walletID);
                    ps.execute();
                    if (sorb) {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 354.34 * amount);
                    } else {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -354.34 * amount);
                    }
                    ps.setInt(3, walletID);
                    ps.execute();
                }

                else if (currency.equals("GBP")) {
                    String sql4 = "update wallet set GBP = GBP + ?, Akshe = Akshe + ? where walletID = ?";
                    ps = connection().prepareStatement(sql4);
                    if (sorb) {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -4.82 * amount);
                    } else {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 4.82 * amount);
                    }
                    ps.setInt(3, User.walletID);
                    ps.execute();
                    if (sorb) {
                        ps.setDouble(1, (-1 * amount));
                        ps.setDouble(2, 4.82 * amount);
                    } else {
                        ps.setDouble(1, amount);
                        ps.setDouble(2, -4.82 * amount);
                    }
                    ps.setInt(3, walletID);
                    ps.execute();
                }
            }
        }

    }
}

