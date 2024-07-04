package com.example.demo1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

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



    private boolean st = false;

    public String[] past = new String[6];

    public String[] now = new String[6];

    ObservableList<tableview> TS = FXCollections.observableArrayList(
            new tableview(),
            new tableview(),
            new tableview(),
            new tableview(),
            new tableview()
    );


    @FXML
    void tohome(ActionEvent event) {
        //Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }

    @FXML
    void toprofile(ActionEvent event) {
        //Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }

    @FXML
    void toswap(ActionEvent event) {
        //Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }

    @FXML
    void totransfer(ActionEvent event) {
        //Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }
    @FXML
    void toEur(ActionEvent event) {
        //Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }

    @FXML
    void toGb(ActionEvent event) {
        //Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }

    @FXML
    void toToman(ActionEvent event) {
        //Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }

    @FXML
    void toUsd(ActionEvent event) {
        //Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }

    @FXML
    void toYen(ActionEvent event) throws IOException {
//        Stage newStage = (Stage) time.getScene().getWindow();
//        newStage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource(""));
//        Scene scene = new Scene(fxmlLoader.load(), 390, 440);
//        newStage.setScene(scene);
//        newStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //table.getStyleClass().add("my_table");
        //init();
        showTime();
    }

    public void showTime(){
        Thread t = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!st){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String timenow = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    time.setText(timenow);
                });
            }
        });
        t.start();
    }
    public void init(){
        changecol.setCellValueFactory(new PropertyValueFactory<tableview , Double>("change"));
        marketcol.setCellValueFactory(new PropertyValueFactory<tableview , String>("market"));
        minpricecol.setCellValueFactory(new PropertyValueFactory<tableview , Double>("minprice"));
        mostpricecol.setCellValueFactory(new PropertyValueFactory<tableview , Double>("mostprice"));
        pricecol.setCellValueFactory(new PropertyValueFactory<tableview , Double>("price"));
        //table.setItems(TS);
    }

    public void readData() throws FileNotFoundException {
        File file = new File("com/example/demo1/currency_prices (3) (2).csv");
        Scanner scanner = new Scanner(file);


    }
}
