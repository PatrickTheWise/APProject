package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable{

    String cap;

    @FXML
    private ImageView bar;
    @FXML
    private TextField username, firstname, lastname, pass, repeatPass, email, phone, captchaField;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        cap = CaptchaGenerator.generateCaptchaString();
        try {
            CaptchaGenerator.captcha(cap, 231, 62,  234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File("captcha.png");
        Image image = new Image(file.toURI().toString());
        bar.setImage(image);

    }

    @FXML
    protected void OpenHome() throws IOException, SQLException {
        boolean allowedTo = true;

        if (!regex.isValidName(firstname.getText())){
            firstname.setStyle("-fx-border-color: red;");
            allowedTo = false;
        }

        if (!regex.isValidName(lastname.getText())){
            lastname.setStyle("-fx-border-color: red;");
            allowedTo = false;
        }

        if (!regex.isValidUsername(username.getText())){
            username.setStyle("-fx-border-color: red;");
            allowedTo = false;
        }

        if (!regex.isValidPassword(pass.getText()) ){
            pass.setStyle("-fx-border-color: red;");
            allowedTo = false;
        }

        if (!pass.getText().equals(repeatPass.getText())){
            repeatPass.setStyle("-fx-border-color: red;");
            allowedTo = false;
        }

        if (!regex.isValidPhone(phone.getText())){
            phone.setStyle("-fx-border-color: red;");
            allowedTo = false;
        }

        if (!regex.isValidEmail(email.getText())){
            email.setStyle("-fx-border-color: red;");
            allowedTo = false;
        }

        if (!captchaField.getText().equals(cap)){
            captchaField.setStyle("-fx-border-color: red;");
            allowedTo = false;
        }

        if (allowedTo) {
            User.Username = username.getText();
            User.Email = email.getText();
            User.Password = pass.getText();
            User.Firstname = firstname.getText();
            User.Lastname = lastname.getText();
            User.PhoneNumber = phone.getText();
            Database.signedUp(username.getText(),pass.getText(),firstname.getText(),lastname.getText(),email.getText(),phone.getText());
            Stage newStage = (Stage) bar.getScene().getWindow();
            newStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 540);
            newStage.setScene(scene);
            newStage.show();
        }
    }

    @FXML
    public void openLogin() throws IOException {
        String cap = CaptchaGenerator.generateCaptchaString();
        CaptchaGenerator.captcha(cap, 77, 255,  82);
        Stage newStage = (Stage)bar.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("a.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
        newStage.setScene(scene);
        newStage.show();
    }
}