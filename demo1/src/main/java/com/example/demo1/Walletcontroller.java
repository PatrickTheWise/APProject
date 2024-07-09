package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Walletcontroller implements Initializable{
    @FXML
    private Button Back;


    @FXML
    private TextField eur;

    @FXML
    private TextField gbp;

    @FXML
    private TextField paye;

    @FXML
    private TextField toman;

    @FXML
    private TextField usd;

    @FXML
    private TextField yen;
    @FXML
    private Label wallet;

    @FXML
    void Backtoprofile(ActionEvent event) throws IOException {
        Stage newStage = (Stage)wallet.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void eurtext(ActionEvent event) {

    }

    @FXML
    void gbptext(ActionEvent event) {

    }

    @FXML
    void payetext(ActionEvent event) {

    }

    @FXML
    void tomantext(ActionEvent event) {

    }

    @FXML
    void usdtext(ActionEvent event) {

    }

    @FXML
    void yentext(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            Database.updateWallet();
//        } catch (SQLException e) {
//            System.out.println("blabla");
//            throw new RuntimeException(e);
//        }
        eur.setText(String.valueOf(User.EUR));
        gbp.setText(String.valueOf(User.GBP));
        usd.setText(String.valueOf(User.USD));
        toman.setText(String.valueOf(User.TMN));
        yen.setText(String.valueOf(User.YEN));
        paye.setText(String.valueOf(User.AKSHE));

    }

}
