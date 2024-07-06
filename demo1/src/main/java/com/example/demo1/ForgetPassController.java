package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgetPassController implements Initializable {

    String codeCode;

    @FXML
    private Text codeText, emailText;

    @FXML
    private TextField email, code;

    @FXML
    private Button getCode;

    @FXML
    private Button getCode1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        code.setVisible(false);
        codeText.setVisible(false);
        getCode1.setVisible(false);
    }

    @FXML
    public void OpenSignUp() throws IOException {
        Stage newStage = (Stage)getCode.getScene().getWindow();
        newStage.close();
        String cap = CaptchaGenerator.generateCaptchaString();
        CaptchaGenerator.captcha(cap, 231, 62,  234);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("sign.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 658, 440);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    public void askForCode() throws IOException {
        codeCode = CaptchaGenerator.CodeGenerator();
        //check database for email and make email textField red if the email wasn't ok
        EmailRecovery.SendEmail(email.getText(), "Your Recovery Code Is: " + codeCode, "Recovery");
        email.setVisible(false);
        emailText.setVisible(false);
        getCode.setVisible(false);
        code.setVisible(true);
        codeText.setVisible(true);
        getCode1.setVisible(true);
    }

    @FXML
    public void askForCode1() throws IOException {
        if (code.getText().equals(codeCode));
        {
            Stage newStage = (Stage)getCode1.getScene().getWindow();
            newStage.close();
            String cap = CaptchaGenerator.generateCaptchaString();
            CaptchaGenerator.captcha(cap, 231, 62,  234);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 540);
            newStage.setScene(scene);
            newStage.show();
        }
    }

}
