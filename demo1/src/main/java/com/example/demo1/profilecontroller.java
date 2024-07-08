package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class profilecontroller implements Initializable {

    @FXML
    private Button email;

    @FXML
    private TextField emailtextfield;

    @FXML
    private Button firstname;

    @FXML
    private TextField firstnametextfield;

    @FXML
    private Button home;

    @FXML
    private Button lastname;

    @FXML
    private TextField lastnametextfiled;

    @FXML
    private TextField passtextfield;

    @FXML
    private Button password;

    @FXML
    private Button phone;

    @FXML
    private TextField phonetextfiled;

    @FXML
    private Button prof;

    @FXML
    private Button swap;

    @FXML
    private Button transfer;

    @FXML
    private TextField usernametextfiled;
    @FXML
    private MenuButton exchangemenu;

    @FXML
    void changefirstname(ActionEvent event) throws SQLException {
        if (regex.isValidName(firstname.getText())) {
            User.Firstname = firstnametextfield.getText();
            Database.profileUpdater("firstname", User.Firstname, User.walletID);
        }
        else {
            firstnametextfield.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    void changelastname(ActionEvent event) throws SQLException {
        if (regex.isValidName(lastnametextfiled.getText())) {
            User.Lastname = lastnametextfiled.getText();
            Database.profileUpdater("lastname", User.Lastname, User.walletID);
        }
        else {
            lastnametextfiled.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    void changepassword(ActionEvent event) throws SQLException {
        if (regex.isValidPassword(passtextfield.getText())) {
            User.Password = passtextfield.getText();
            Database.profileUpdater("pass", User.Password, User.walletID);
        }
        else {
            passtextfield.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    void changephone(ActionEvent event) throws SQLException {
        if (regex.isValidPhone(phonetextfiled.getText())) {
            User.PhoneNumber = phonetextfiled.getText();
            Database.profileUpdater("phone", User.PhoneNumber, User.walletID);
        }
        else {
            phonetextfiled.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    void chnageemail(ActionEvent event) throws SQLException {
        if (regex.isValidEmail(emailtextfield.getText())) {
            User.Email = emailtextfield.getText();
            Database.profileUpdater("mail", User.Email, User.walletID);
        }
        else {
            emailtextfield.setStyle("-fx-border-color: red;");
        }

    }

    @FXML
    void tohomme(ActionEvent event) throws IOException {
        Stage newStage = (Stage)this.email.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void toprof(ActionEvent event) {}

    @FXML
    void toswap(ActionEvent event) throws IOException {
        Stage newStage = (Stage)this.email.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("swap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void totransfer(ActionEvent event) throws IOException {
        Stage newStage = (Stage)this.email.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("transfer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernametextfiled.setText(User.Username);
        lastnametextfiled.setText(User.Lastname);
        firstnametextfield.setText(User.Firstname);
        emailtextfield.setText(User.Email);
        phonetextfiled.setText(User.PhoneNumber);
        passtextfield.setText(User.Password);
    }
}
