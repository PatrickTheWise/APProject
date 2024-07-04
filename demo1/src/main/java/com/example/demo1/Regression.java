package com.example.demo1;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;

public class Regression {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        double[] historicalPrices = new double[1440];
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exchange", "root", null);
        Statement stmn = connect.createStatement();
        int j = 3;
        while (j < 8){
            int i = 0;
            ResultSet rs = stmn.executeQuery("select * from ag");
            while (rs.next()) {
                historicalPrices[i] = rs.getDouble(j);
                i++;
            }
            // Historical prices (USD/JPY) for demonstration purposes


            // Assuming linear regression for simplicity
            double slope = calculateSlope(historicalPrices);
            double intercept = calculateIntercept(historicalPrices);
            double predictedPrice = predictPrice(historicalPrices, slope, intercept);
            System.out.printf("Predicted USD/JPY price in 1 day(s): %.2f%n", predictedPrice);
            j++;
        }
    }

    // Calculate the slope (regression coefficient)
    private static double calculateSlope(double[] prices) {
        int n = prices.length;
        double sumXY = 0.0;
        double sumX = 0.0;
        double sumY = 0.0;

        for (int i = 1; i <= n; i++) {
            sumXY += i * prices[i-1];
            sumX += i;
            sumY += prices[i-1];
        }

        double numerator = n * sumXY - sumX * sumY;
        double denominator = n * sumX * sumX - sumX * sumX;
        return (numerator / denominator);
    }

    private static double calculateIntercept(double[] prices) {
        int n = prices.length;
        double sum = Arrays.stream(prices).sum();
        double slope = calculateSlope(prices);

        return (sum - slope * sum) / n;
    }

    private static double predictPrice(double[] prices, double slope, double intercept) {
        int n = prices.length;
        Random rand = new Random();
        return (rand.nextInt(2) * 2 - 1) * slope * (n + 1) + intercept;
    }
}