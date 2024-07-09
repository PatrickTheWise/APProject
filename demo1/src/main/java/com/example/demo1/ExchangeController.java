package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ExchangeController {

    @FXML
    private TextArea aksheAmount;

    @FXML
    private TextField amount;

    @FXML
    private MenuItem eur;

    @FXML
    private MenuItem gbp;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem tmn;

    @FXML
    private MenuItem usd;

    @FXML
    private MenuItem yen;

    @FXML
    public static Button SELL, BUY;

    @FXML
    void buy(MouseEvent event) throws SQLException {
        Database.exchangeSQL(true, User.Username, menu.getText(), Double.parseDouble(amount.getText()));
    }

    @FXML
    void sell(MouseEvent event) throws SQLException {
        Database.exchangeSQL(false, User.Username, menu.getText(), Double.parseDouble(amount.getText()));
    }

    @FXML
    void choseEUR(ActionEvent event) {
        menu.setText("EUR");
    }

    @FXML
    void choseGBP(ActionEvent event) {
        menu.setText("GBP");
    }

    @FXML
    void choseTMN(ActionEvent event) {
        menu.setText("TMN");
    }

    @FXML
    void choseUSD(ActionEvent event) {
        menu.setText("USD");
    }

    @FXML
    void choseYEN(ActionEvent event) {
        menu.setText("YEN");
    }
}
