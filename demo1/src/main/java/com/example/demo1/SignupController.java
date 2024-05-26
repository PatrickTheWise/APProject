package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable{
    @FXML
    private ImageView bar;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        File file = new File("D://JavaProjects//demo1//src//main//resources//com//example//demo1//captcha.png");
        Image image = new Image(file.toURI().toString());
        bar.setImage(image);

    }
}