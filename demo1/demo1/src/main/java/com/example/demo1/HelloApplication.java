package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String cap = CaptchaGenerator.generateCaptchaString();
        CaptchaGenerator.captcha(cap);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("a.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}