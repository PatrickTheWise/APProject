package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class swapcontroller implements Initializable {
    private double YENPRICE;
    private double TMNPRICE;
    private double GBPPRICE;
    private double USDPRICE;
    private double EURPRICE;

    @FXML
    private MenuItem EURswap;

    @FXML
    private MenuItem EURto;

    @FXML
    private MenuItem GBPswap;

    @FXML
    private MenuItem GBPto;

    @FXML
    private MenuItem TOMANswap;

    @FXML
    private MenuItem TOMANto;

    @FXML
    private MenuItem USDswap;

    @FXML
    private MenuItem USDto;

    @FXML
    private MenuItem YENswap;

    @FXML
    private MenuItem YENto;

    @FXML
    private Button home;

    @FXML
    private MenuButton menuswap;
    @FXML
    private MenuButton menuto;

    @FXML
    private Button prof;

    @FXML
    private Button swap;

    @FXML
    private Button swapbutton;

    @FXML
    private Label time;
    @FXML
    private TextField khorooji;

    @FXML
    private Button transfer;

    @FXML
    private TextField vorrodi;

    @FXML
    void menuto(ActionEvent event) {}

    @FXML
    void swapEUR(ActionEvent event) {
        menuswap.setText("EUR");
    }

    @FXML
    void swapGBP(ActionEvent event) {
        menuswap.setText("GBP");
    }

    @FXML
    void swapTOMAN(ActionEvent event) {
        menuswap.setText("TOMAN");
    }

    @FXML
    void swapUSD(ActionEvent event) {
        menuswap.setText("USD");
    }

    @FXML
    void swapYEN(ActionEvent event) {
        menuswap.setText("YEN");
    }
    double payevorrodi;
    double payetabdil;
    @FXML
    void swaparz(ActionEvent event) throws SQLException {
        if (menuswap.getText().equals("TOMAN") && User.TMN >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("YEN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / TMNPRICE;
                payetabdil = payevorrodi * YENPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.TMN -= Double.parseDouble(vorrodi.getText());
                User.YEN += payetabdil;
                Database.currencySwap("Toman", "YEN", User.TMN, User.YEN, User.walletID);
            }
            else if (menuto.getText().equals("USD")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / TMNPRICE;
                payetabdil = payevorrodi * USDPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.TMN -= Double.parseDouble(vorrodi.getText());
                User.USD += payetabdil;
                Database.currencySwap("Toman", "USD", User.TMN, User.USD, User.walletID);
            }
            else if (menuto.getText().equals("EUR")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / TMNPRICE;
                payetabdil = payevorrodi * EURPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.TMN -= Double.parseDouble(vorrodi.getText());
                User.EUR += payetabdil;
                Database.currencySwap("Toman", "EUR", User.TMN, User.EUR, User.walletID);
            }
            else if (menuto.getText().equals("GBP")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / TMNPRICE;
                payetabdil = payevorrodi * GBPPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.TMN -= Double.parseDouble(vorrodi.getText());
                User.GBP += payetabdil;
                Database.currencySwap("Toman", "GBP", User.TMN, User.GBP, User.walletID);
            }
        }
        else if (menuswap.getText().equals("USD") && User.USD >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("GBP")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / USDPRICE;
                payetabdil = payevorrodi * GBPPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.USD -= Double.parseDouble(vorrodi.getText());
                User.GBP += payetabdil;
                Database.currencySwap("USD", "GBP", User.USD, User.GBP, User.walletID);
            }
            else if (menuto.getText().equals("YEN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / USDPRICE;
                payetabdil = payevorrodi * YENPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.USD -= Double.parseDouble(vorrodi.getText());
                User.YEN += payetabdil;
                Database.currencySwap("USD", "YEN", User.USD, User.YEN, User.walletID);
            }
            else if (menuto.getText().equals("TOMAN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / USDPRICE;
                payetabdil = payevorrodi * TMNPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.USD -= Double.parseDouble(vorrodi.getText());
                User.TMN += payetabdil;
                Database.currencySwap("USD", "Toman", User.USD, User.TMN, User.walletID);
            }
            else if (menuto.getText().equals("EUR")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / USDPRICE;
                payetabdil = payevorrodi * EURPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.USD -= Double.parseDouble(vorrodi.getText());
                User.EUR += payetabdil;
                Database.currencySwap("USD", "EUR", User.USD, User.EUR, User.walletID);
            }
        }
        else if (menuswap.getText().equals("GBP") && User.GBP >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("TOMAN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / GBPPRICE;
                payetabdil = payevorrodi * TMNPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.GBP -= Double.parseDouble(vorrodi.getText());
                User.TMN += payetabdil;
                Database.currencySwap("GBP", "Toman", User.GBP, User.TMN, User.walletID);
            }
            else if (menuto.getText().equals("USD")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / GBPPRICE;
                payetabdil = payevorrodi * USDPRICE ;
                khorooji.setText(String.valueOf(payetabdil));
                User.GBP -= Double.parseDouble(vorrodi.getText());
                User.USD += payetabdil;
                Database.currencySwap("GBP", "USD", User.TMN, User.USD, User.walletID);
            }
            else if (menuto.getText().equals("EUR")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / GBPPRICE;
                payetabdil = payevorrodi * EURPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.GBP -= Double.parseDouble(vorrodi.getText());
                User.EUR += payetabdil;
                Database.currencySwap("GBP", "EUR", User.GBP, User.EUR, User.walletID);
            }
            else if (menuto.getText().equals("YEN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / GBPPRICE;
                payetabdil = payevorrodi * YENPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.GBP -= Double.parseDouble(vorrodi.getText());
                User.YEN += payetabdil;
                Database.currencySwap("GBP", "YEN", User.GBP, User.YEN, User.walletID);
            }
        }
        else if (menuswap.getText().equals("EUR") && User.EUR >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("USD")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / EURPRICE;
                payetabdil = payevorrodi * USDPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.EUR -= Double.parseDouble(vorrodi.getText());
                User.USD += payetabdil;
                Database.currencySwap("EUR", "USD", User.EUR, User.USD, User.walletID);
            }
            else if (menuto.getText().equals("YEN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / EURPRICE;
                payetabdil = payevorrodi * YENPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.EUR -= Double.parseDouble(vorrodi.getText());
                User.YEN += payetabdil;
                Database.currencySwap("EUR", "YEN", User.EUR, User.YEN, User.walletID);
            }
            else if (menuto.getText().equals("TOMAN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / EURPRICE;
                payetabdil = payevorrodi * TMNPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.EUR -= Double.parseDouble(vorrodi.getText());
                User.TMN += payetabdil;
                Database.currencySwap("EUR", "TOMAN", User.EUR, User.TMN, User.walletID);
            }
            else if (menuto.getText().equals("GBP")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / EURPRICE;
                payetabdil = payevorrodi * GBPPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.EUR -= Double.parseDouble(vorrodi.getText());
                User.GBP += payetabdil;
                Database.currencySwap("EUR", "GBP", User.EUR, User.GBP, User.walletID);
            }
        }
        else if (menuswap.getText().equals("YEN") && User.YEN >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("USD")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / YENPRICE;
                payetabdil = payevorrodi * USDPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.YEN -= Double.parseDouble(vorrodi.getText());
                User.USD += payetabdil;
                Database.currencySwap("YEN", "USD", User.YEN, User.USD, User.walletID);
            }
            else if (menuto.getText().equals("TOMAN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / YENPRICE;
                payetabdil = payevorrodi * TMNPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.YEN -= Double.parseDouble(vorrodi.getText());
                User.TMN += payetabdil;
                Database.currencySwap("YEN", "TOMAN", User.YEN, User.TMN, User.walletID);
            }
            else if (menuto.getText().equals("GBP")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / YENPRICE;
                payetabdil = payevorrodi * GBPPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.YEN -= Double.parseDouble(vorrodi.getText());
                User.GBP += payetabdil;
                Database.currencySwap("YEN", "GBP", User.YEN, User.GBP, User.walletID);
            }
            else if (menuto.getText().equals("EUR")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / YENPRICE;
                payetabdil = payevorrodi * EURPRICE;
                khorooji.setText(String.valueOf(payetabdil));
                User.YEN -= Double.parseDouble(vorrodi.getText());
                User.EUR += payetabdil;
                Database.currencySwap("YEN", "EUR", User.YEN, User.EUR, User.walletID);
            }
        }
    }

    @FXML
    void toEUR(ActionEvent event) {
        menuto.setText("EUR");
    }

    @FXML
    void toGBP(ActionEvent event) {
        menuto.setText("GBP");
    }

    @FXML
    void toTOMAN(ActionEvent event) {
        menuto.setText("TOMAN");
    }

    @FXML
    void toUSD(ActionEvent event) {
        menuto.setText("USD");
    }

    @FXML
    void toYEN(ActionEvent event) {
        menuto.setText("YEN");
    }

    @FXML
    void tohome(ActionEvent event) throws IOException {
        Stage newStage = (Stage)this.time.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void toprof(ActionEvent event) throws IOException {
        Stage newStage = (Stage)this.time.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void toswap(ActionEvent event) {}

    @FXML
    void totransfer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vorrodi.textProperty().addListener((observable, oldValue, newValue) -> {
            double convertedAmount = payetabdil;
            upto();
            khorooji.setText(String.valueOf(convertedAmount));
        });
        showTime();
    }

    public void tomenu(ActionEvent actionEvent) {

    }
    private String[] now = new String[7];
    private boolean st = false;

    private int lastMinute = -1;
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
                        update_price();
                    }
                });
            }
        });
        thread.start();
    }
    private void update_price(){
        LocalTime time = LocalTime.now();
        int minute = time.getMinute();
        int hour = time.getHour();

        for(int i = 0 ; i < HomeController.arzha.size();i++){
            if (HomeController.arzha.get(i).hour == hour && HomeController.arzha.get(i).min == minute){
                YENPRICE = HomeController.arzha.get(i).yen;
                GBPPRICE = HomeController.arzha.get(i).gbp;
                TMNPRICE = HomeController.arzha.get(i).tmn;
                USDPRICE = HomeController.arzha.get(i).usd;
                EURPRICE = HomeController.arzha.get(i).eur;
            }
        }
    }
    void upto() {
        if (menuswap.getText().equals("TOMAN") && User.TMN >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("YEN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / TMNPRICE;
                payetabdil = payevorrodi * YENPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("USD")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / TMNPRICE;
                payetabdil = payevorrodi * USDPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("EUR")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / TMNPRICE;
                payetabdil = payevorrodi * EURPRICE;
                khorooji.setText(String.valueOf(payetabdil));

            }
            else if (menuto.getText().equals("GBP")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / TMNPRICE;
                payetabdil = payevorrodi * GBPPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
        }
        else if (menuswap.getText().equals("USD") && User.USD >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("GBP")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / USDPRICE;
                payetabdil = payevorrodi * GBPPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("YEN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / USDPRICE;
                payetabdil = payevorrodi * YENPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("TOMAN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / USDPRICE;
                payetabdil = payevorrodi * TMNPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("EUR")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / USDPRICE;
                payetabdil = payevorrodi * EURPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
        }
        else if (menuswap.getText().equals("GBP") && User.GBP >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("TOMAN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / GBPPRICE;
                payetabdil = payevorrodi * TMNPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("USD")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / GBPPRICE;
                payetabdil = payevorrodi * USDPRICE ;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("EUR")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / GBPPRICE;
                payetabdil = payevorrodi * EURPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("YEN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / GBPPRICE;
                payetabdil = payevorrodi * YENPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
        }
        else if (menuswap.getText().equals("EUR") && User.EUR >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("USD")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / EURPRICE;
                payetabdil = payevorrodi * USDPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("YEN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / EURPRICE;
                payetabdil = payevorrodi * YENPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("TOMAN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / EURPRICE;
                payetabdil = payevorrodi * TMNPRICE;
                khorooji.setText(String.valueOf(payetabdil));
            }
            else if (menuto.getText().equals("GBP")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / EURPRICE;
                payetabdil = payevorrodi * GBPPRICE;
                khorooji.setText(String.valueOf(payetabdil));

            }
        }
        else if (menuswap.getText().equals("YEN") && User.YEN >= Double.parseDouble(vorrodi.getText())){
            if (menuto.getText().equals("USD")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / YENPRICE;
                payetabdil = payevorrodi * USDPRICE;
                khorooji.setText(String.valueOf(payetabdil));

            }
            else if (menuto.getText().equals("TOMAN")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / YENPRICE;
                payetabdil = payevorrodi * TMNPRICE;
                khorooji.setText(String.valueOf(payetabdil));

            }
            else if (menuto.getText().equals("GBP")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / YENPRICE;
                payetabdil = payevorrodi * GBPPRICE;
                khorooji.setText(String.valueOf(payetabdil));

            }
            else if (menuto.getText().equals("EUR")){
                payevorrodi = Double.parseDouble(vorrodi.getText()) / YENPRICE;
                payetabdil = payevorrodi * EURPRICE;
                khorooji.setText(String.valueOf(payetabdil));

            }
        }
    }
}
