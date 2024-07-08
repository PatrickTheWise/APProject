package com.example.demo1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HomeController implements Initializable {

    @FXML
    private Button Eur;

    @FXML
    private Button Gb;

    @FXML
    private Button Toman;

    @FXML
    private Button Usd;

    @FXML
    private Button yen;

    @FXML
    private Button home;

    @FXML
    private Button prof;

    @FXML
    private Button swap;

    @FXML
    private Button transfer;

    @FXML
    private TableView<tableview> table;

    @FXML
    private TableColumn<tableview,Double> changecol;

    @FXML
    private TableColumn<tableview, String> marketcol;

    @FXML
    private TableColumn<tableview,Double> minpricecol;

    @FXML
    private TableColumn<tableview,Double> mostpricecol;

    @FXML
    private TableColumn<tableview,Double> pricecol;

    @FXML
    private Label time;
    private boolean  st = false;
    private String[] now = new String[7];
    public static   ArrayList<ArzHa> arzha = new ArrayList<ArzHa>();
    private double [] max = new double[5]; // 0 = usd    1 = eur    2 = toman    3 = yen   4 = gbp
    private double [] min = {1000000.0 , 1000000.0 , 1000000.0 , 1000000.0 , 1000000.0};
    private int lastMinute = -1;

    ObservableList<tableview> tableviews = FXCollections.observableArrayList(
            new tableview(),
            new tableview(),
            new tableview(),
            new tableview(),
            new tableview()
    );



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        marketcol.setCellValueFactory(new PropertyValueFactory<tableview , String>("market"));
        pricecol.setCellValueFactory(new PropertyValueFactory<tableview , Double>("price"));
        changecol.setCellValueFactory(new PropertyValueFactory<tableview , Double>("changes"));
        minpricecol.setCellValueFactory(new PropertyValueFactory<tableview , Double>("mp"));
        mostpricecol.setCellValueFactory(new PropertyValueFactory<tableview,Double>("mx"));

        table.setItems(tableviews);


        readData();
        showTime();

    }

    public void showTime(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            readData();
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
                        //readData();
                        updatetable();
                        lastMinute = currentMinute;
                    }
                });
            }
        });
        thread.start();
    }
    public void readData()  {
        File file = new File("demo1\\src\\main\\resources\\com\\example\\demo1\\currency_prices (3) (2).csv");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("read data problem");
            throw new RuntimeException(e);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss'.985667'");
        scanner.nextLine();
        scanner.nextLine();
        int count = 1;
        while(count <= 1439){
            String t = scanner.nextLine();
            now = t.split(",");
            LocalTime clock = LocalTime.parse(now[1],formatter);
            int hour = clock.getHour();
            int min = clock.getMinute();
            arzha.add(new ArzHa(Double.parseDouble(now[2]),Double.parseDouble(now[5]),Double.parseDouble(now[4]),Double.parseDouble(now[6]),Double.parseDouble(now[3]),hour,min));
            scanner.nextLine();
            count++;
        }
        scanner.close();
    }

    public void updatetable() {
        LocalTime time = LocalTime.now();
        int minute = time.getMinute();
        int hour = time.getHour();
        double yenprice = 0;
        double gbpprice = 0;
        double tmnprice = 0;
        double usdprice = 0;
        double eurprice = 0;
        double lastyenprice = 0;
        double lastgbpprice = 0;
        double lasttmnprice = 0;
        double lastusdprice = 0;
        double lasteurprice = 0;
        double changeyenprice = 0;
        double changegbpprice = 0;
        double changetmnprice = 0;
        double changeusdprice = 0;
        double changeeurprice = 0;
        find_max();
        find_min();
        for (int i = 1; i < arzha.size(); i++) {
            if (arzha.get(i).min == minute && arzha.get(i).hour == hour) {
                yenprice = arzha.get(i).yen;
                gbpprice = arzha.get(i).gbp;
                tmnprice = arzha.get(i).tmn;
                usdprice = arzha.get(i).usd;
                eurprice = arzha.get(i).eur;
                lastyenprice = arzha.get(i - 1).yen;
                lastgbpprice = arzha.get(i - 1).gbp;
                lasttmnprice = arzha.get(i - 1).tmn;
                lastusdprice = arzha.get(i - 1).usd;
                lasteurprice = arzha.get(i - 1).eur;
                //break;
            }

            Data.GBPPrice = gbpprice;
            Data.TMNPrice = tmnprice;
            Data.YENPrice = yenprice;
            Data.EURPrice = eurprice;
            Data.USDPrice = usdprice;

            DecimalFormat df = new DecimalFormat("#.##");


            changeusdprice = lastusdprice - usdprice;
            changeusdprice = changeusdprice / lastusdprice * 100;
            //changeusdprice = Double.parseDouble(df.format(changeusdprice));

            changetmnprice = lasttmnprice - tmnprice;
            changetmnprice = changetmnprice / lasttmnprice * 100;
            //changetmnprice = Double.parseDouble(df.format(changetmnprice));

            changeeurprice = lasteurprice - eurprice;
            changeeurprice = changeeurprice / lasteurprice * 100;
            //changeeurprice = Double.parseDouble(df.format(changeeurprice));

            changegbpprice = lastgbpprice - gbpprice;
            changegbpprice = changegbpprice / lastgbpprice * 100;
            //changegbpprice = Double.parseDouble(df.format(changegbpprice));

            changeyenprice = lastyenprice - yenprice;
            changeyenprice = changeyenprice / lastyenprice * 100;
            //changeyenprice = Double.parseDouble(df.format(changeyenprice));

            // 0 = usd    1 = eur    2 = toman    3 = yen   4 = gbp
            tableview t1 = new tableview(max[0],min[0], "USD", changeusdprice, usdprice);
            tableview t2 = new tableview(max[3], min[3], "YEN", changeyenprice, yenprice);
            tableview t3 = new tableview(max[1], min[1], "EUR", changeeurprice, eurprice);
            tableview t4 = new tableview(max[4], min[4], "GBP", changegbpprice, gbpprice);
            tableview t5 = new tableview(max[2], min[2], "TOOMAN", changetmnprice, tmnprice);
            table.getItems().remove(0, 5);

            tableviews.add(t1);
            tableviews.add(t2);
            tableviews.add(t3);
            tableviews.add(t4);
            tableviews.add(t5);
            table.setItems(tableviews);
        }
    }
    // 0 = usd    1 = eur    2 = toman    3 = yen   4 = gbp
    public void find_min(){
        for(int i = 0 ; i < arzha.size() ;i++){
            if (arzha.get(i).usd <= min[0]){
                min[0] = arzha.get(i).usd;
                Data.USDMIN = min[0];
            }
            if (arzha.get(i).eur <= min[1]){
                min[1] = arzha.get(i).eur;
                Data.EURMIN = min[1];
            }
            if (arzha.get(i).tmn <= min[2]){
                min[2] = arzha.get(i).tmn;
                Data.TMNMIN = min[2];
            }
            if (arzha.get(i).yen <= min[3]){
                min[3] = arzha.get(i).yen;
                Data.YENMIN = min[3];
            }
            if (arzha.get(i).gbp <= min[4]){
                min[4] = arzha.get(i).gbp;
                Data.GBPMIN = min[4];
            }
        }
    }
    public void find_max(){
        for(int i = 0 ; i < arzha.size() ;i++){
            if (arzha.get(i).usd >= max[0]){
                max[0] = arzha.get(i).usd;
                Data.USDMAX = max[0];
            }
            if (arzha.get(i).eur >= max[1]){
                max[1] = arzha.get(i).eur;
                Data.EURMAX = min[1];
            }
            if (arzha.get(i).tmn >= max[2]){
                max[2] = arzha.get(i).tmn;
                Data.TMNMAX = min[2];
            }
            if (arzha.get(i).yen >= max[3]){
                max[3] = arzha.get(i).yen;
                Data.YENMAX = max[3];
            }
            if (arzha.get(i).gbp >= max[4]){
                max[4] = arzha.get(i).gbp;
                Data.GBPMAX = max[4];
            }
        }
    }

    public void totransfer(ActionEvent actionEvent) {
    }

    public void toswap(ActionEvent actionEvent) throws IOException {
        Stage newStage = (Stage)this.time.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("swap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    public void toprof(ActionEvent actionEvent) {
    }

    public void tohome(ActionEvent actionEvent) {
    }

    public void toYen(ActionEvent actionEvent) throws IOException {
        Stage newStage = (Stage)this.time.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("Yen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    public void toEur(ActionEvent actionEvent) throws IOException {
        Stage newStage = (Stage)this.time.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("EUR.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    public void toGb(ActionEvent actionEvent) throws IOException {
        Stage newStage = (Stage)this.time.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("GBP.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    public void toToman(ActionEvent actionEvent) throws IOException {
        Stage newStage = (Stage)this.time.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("Toman.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }

    public void toUsd(ActionEvent actionEvent) throws IOException {
        Stage newStage = (Stage)this.time.getScene().getWindow();
        newStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("arz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.setScene(scene);
        newStage.show();
    }
}
