package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class tranferControlller implements Initializable {

    @FXML
    private Button dotransfer;

    @FXML
    private MenuItem exchangeEUR;

    @FXML
    private MenuItem exchangeGBP;

    @FXML
    private MenuItem exchangeTOMAN;

    @FXML
    private MenuItem exchangeUSD;

    @FXML
    private MenuItem exchangeYEN;

    @FXML
    private Button home;

    @FXML
    private TextField moneytext;

    @FXML
    private Button prof;

    @FXML
    private Button swap;

    @FXML
    private Label time;

    @FXML
    private Button transfer;

    @FXML
    private TextField wallettext;
    @FXML
    private MenuButton exchangemenu;

    @FXML
    void confirm(ActionEvent event) {
        //if ()
    }
    @FXML
    void EUR(ActionEvent event) {
        exchangemenu.setText("EUR");
    }

    @FXML
    void GBP(ActionEvent event) {
        exchangemenu.setText("GBP");
    }

    @FXML
    void TOMAN(ActionEvent event) {
        exchangemenu.setText("TOMAN");
    }

    @FXML
    void USD(ActionEvent event) {
        exchangemenu.setText("USD");
    }

    @FXML
    void YEN(ActionEvent event) {
        exchangemenu.setText("YEN");
    }
    @FXML
    void tohome(ActionEvent event) throws IOException {
        Stage newStage = (Stage)this.transfer.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void toprof(ActionEvent event) throws IOException {
        Stage newStage = (Stage)this.transfer.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void toswap(ActionEvent event) throws IOException {
        Stage newStage = (Stage)this.transfer.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("swap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void totransfer(ActionEvent event) {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
