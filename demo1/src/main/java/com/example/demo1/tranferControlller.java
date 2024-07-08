package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    void confirm(ActionEvent event) throws SQLException {
        if (Database.Wallet_exist(Integer.parseInt(wallettext.getText()))){
            if (enough_mojoodi(User.EUR,Double.parseDouble(moneytext.getText())) && moneytext.getText().equals("EUR")){
                User.EUR -= Double.parseDouble(moneytext.getText());
                moneytext.setText("");
                wallettext.setText("");
                /// inja bayad sql bezanim
            }
            else if (enough_mojoodi(User.TMN,Double.parseDouble(moneytext.getText())) && moneytext.getText().equals("TMN")){
                User.TMN -= Double.parseDouble(moneytext.getText());
                moneytext.setText("");
                wallettext.setText("");
            }
            else if (enough_mojoodi(User.USD,Double.parseDouble(moneytext.getText())) && moneytext.getText().equals("USD")){
                User.USD -= Double.parseDouble(moneytext.getText());
                moneytext.setText("");
                wallettext.setText("");
            }
            else if (enough_mojoodi(User.YEN,Double.parseDouble(moneytext.getText())) && moneytext.getText().equals("YEN")){
                User.YEN -= Double.parseDouble(moneytext.getText());
                moneytext.setText("");
                wallettext.setText("");
            }
            else if (enough_mojoodi(User.GBP,Double.parseDouble(moneytext.getText())) && moneytext.getText().equals("GBP")){
                User.GBP -= Double.parseDouble(moneytext.getText());
                moneytext.setText("");
                wallettext.setText("");
            }
        }
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
        exchangemenu.setText("TMN");
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
        showTime();
    }
    public static boolean enough_mojoodi(double moojodi,double send){
        return moojodi >= send;
    }
    private int lastMinute = -1;
    private boolean st = false;

    public void showTime(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!st){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                final Date[] now = {new Date()};
                final String timenow = simpleDateFormat.format(now[0]);

                Platform.runLater(() -> {
                    time.setText(timenow);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(now[0]);
                    int currentMinute = calendar.get(Calendar.MINUTE);
                    if (currentMinute != lastMinute) {
                        lastMinute = currentMinute;
                    }
                });
            }
        });
        thread.start();
    }
}
