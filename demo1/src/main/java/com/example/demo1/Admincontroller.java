package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;


public class Admincontroller implements Initializable {
    @FXML
    private TextField balance;

    @FXML
    private Button close;

    @FXML
    private Button open;

    @FXML
    private TextField status;
    public void openBazzar() throws SQLException {
        String SQL = "update wallet set usd = 1 where walletID = 3";
        PreparedStatement statement = Database.connection().prepareStatement(SQL);
        statement.execute();
        swapcontroller.swap.setVisible(true);
        tranferControlller.transfer.setVisible(true);
        ExchangeController.SELL.setVisible(true);
        ExchangeController.BUY.setVisible(true);
    }

    public void closeBazzar() throws SQLException {
        String SQL = "update wallet set usd = 0 where walletID = 3";
        PreparedStatement statement = Database.connection().prepareStatement(SQL);
        statement.execute();
        swapcontroller.swap.setVisible(false);
        tranferControlller.transfer.setVisible(false);
        ExchangeController.SELL.setVisible(false);
        ExchangeController.BUY.setVisible(false);
    }

    public void doEmbezzlement(ActionEvent actionEvent) throws SQLException {
        double AksheSum = 0;
        String sql = "update wallet set Akshe = ? where walletID = ?";
        String query = "select * from wallet";
        Statement statement = Database.connection().createStatement();
        PreparedStatement preparedStatement = Database.connection().prepareStatement(sql);
        ResultSet RS = statement.executeQuery(query);
        while (RS.next()){
            AksheSum += RS.getDouble(7);
            preparedStatement.setDouble(1, 0);
            preparedStatement.setInt(2, RS.getInt(1));
            preparedStatement.execute();
        }
        preparedStatement.setDouble(1, AksheSum);
        preparedStatement.setInt(2, 3);
        preparedStatement.execute();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = "select Akshe from Wallet where walletID = 3";
        try {
            Statement statement1 = Database.connection().createStatement();
            ResultSet RS = statement1.executeQuery(query);
            while (RS.next()){
                balance.setText(String.valueOf(RS.getDouble(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            openBazzar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        status.setText("Market Is Open");
        String SQL = "select USD from wallet where walletID = 3";
        try {
            Statement statement = Database.connection().createStatement();
            ResultSet RS = statement.executeQuery(SQL);
            while (RS.next()){
                if (RS.getDouble(1) == 0) closeBazzar();
                status.setText("Market Is Closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
