package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public Hyperlink arz1, arz2, arz3, arz4, arz5;
    @FXML
    public Text price1, price2, price3, price4, price5, change1, change2, change3, change4, change5, least1, least2, least3, least4, least5, most1, most2, most3, most4, most5;
    @FXML
    private void handleMostPriceButton(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
