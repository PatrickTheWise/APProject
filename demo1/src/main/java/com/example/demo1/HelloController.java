package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    String cap;

    @FXML
    private ImageView bar;
    @FXML
    private Button login;
    @FXML
    private TextField userField, passField, captchaField;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        cap = CaptchaGenerator.generateCaptchaString();
        try {
            CaptchaGenerator.captcha(cap, 77, 255,  82);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File("D://JavaProjects//demo1//src//main//resources//com//example//demo1//captcha.png");
        Image image = new Image(file.toURI().toString());
        bar.setImage(image);

    }

    @FXML
    public void OpenSignUp() throws IOException {
        Stage newStage = (Stage)login.getScene().getWindow();
        newStage.close();
        String cap = CaptchaGenerator.generateCaptchaString();
        CaptchaGenerator.captcha(cap, 231, 62,  234);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("sign.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 658, 440);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    public void OpenForgetPassword() throws IOException {
        Stage newStage = (Stage)login.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("a.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    public void OpenHome() throws IOException {
        // must add excel for user check
        if (captchaField.getText().equals(cap)) {
            Stage newStage = (Stage) login.getScene().getWindow();
            newStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 540);
            newStage.setScene(scene);
            newStage.show();
        }
        else captchaField.setStyle("-fx-border-color: red;");
    }
}