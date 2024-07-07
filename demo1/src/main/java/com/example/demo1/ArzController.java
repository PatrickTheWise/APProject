package com.example.demo1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ArzController implements Initializable {
    @FXML
    private ImageView CurrencyIMG;
    @FXML
    private LineChart<Number, Number> LineChart = new LineChart(new NumberAxis(), new NumberAxis());
    @FXML
    private Text currencyName;
    @FXML
    private Text lastDeal;
    @FXML
    private ListView<?> orderLists;
    @FXML
    private Text price;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    XYChart.Series<Number, Number> series = new XYChart.Series();

    public ArzController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.series = new XYChart.Series();
        this.LineChart.getData().add(this.series);
        this.xAxis.setAutoRanging(false);
        this.xAxis.setLowerBound(0.0);
        this.xAxis.setUpperBound(24.0);
        this.xAxis.setTickUnit(1.0);
        this.yAxis.setAutoRanging(true);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    updateChart();
                    updateLabels();
                });
            }
        }, 0, 60000);
    }

    private void updateChart() {
        this.xAxis.setLabel("minute");
        this.updateChartMinutes();
    }

    private void updateChartMinutes() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.series.getData().clear();

        for(int i = 30; i >= 0; --i) {
            LocalDateTime startOfMinute = now.minusMinutes((long)i).withSecond(0).withNano(0);
            LocalDateTime endOfMinute = startOfMinute.plusMinutes(1L);
            String startOfMinuteDate = startOfMinute.format(dateFormatter);
            String startOfMinuteTime = startOfMinute.format(timeFormatter);
            String endOfMinuteDate = endOfMinute.format(dateFormatter);
            String endOfMinuteTime = endOfMinute.format(timeFormatter);
            String query = "SELECT AVG(USD) as avg_usd FROM ag WHERE (DATE = '" + startOfMinuteDate + "' AND TIMA >= '" + startOfMinuteTime + "') OR (DATE = '" + endOfMinuteDate + "' AND TIMA < '" + endOfMinuteTime + "')";

            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exchange", "root", (String)null);

                try {
                    Statement stmt = connect.createStatement();

                    try {
                        ResultSet rs = stmt.executeQuery(query);

                        try {
                            if (rs.next()) {
                                double avgPrice = rs.getDouble("avg_usd");
                                this.series.getData().add(new XYChart.Data(30 - i, avgPrice));
                            }
                        } catch (Throwable var20) {
                            if (rs != null) {
                                try {
                                    rs.close();
                                } catch (Throwable var19) {
                                    var20.addSuppressed(var19);
                                }
                            }

                            throw var20;
                        }

                        if (rs != null) {
                            rs.close();
                        }
                    } catch (Throwable var21) {
                        if (stmt != null) {
                            try {
                                stmt.close();
                            } catch (Throwable var18) {
                                var21.addSuppressed(var18);
                            }
                        }

                        throw var21;
                    }

                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Throwable var22) {
                    if (connect != null) {
                        try {
                            connect.close();
                        } catch (Throwable var17) {
                            var22.addSuppressed(var17);
                        }
                    }

                    throw var22;
                }

                if (connect != null) {
                    connect.close();
                }
            } catch (Exception var23) {
                System.out.println(var23.getMessage());
            }
        }

        this.xAxis.setLowerBound(0.0);
        this.xAxis.setUpperBound(30.0);
        this.xAxis.setTickUnit(1.0);
    }

    public void updateLabels() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exchange", "root", (String)null);

            try {
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();
                String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String query = "SELECT eur, usd, GBP, toman, yen FROM ag WHERE CONCAT(date, ' ', tima) <= ? ORDER BY CONCAT(date, ' ', tima) DESC LIMIT 1";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, formattedDate + " " + formattedTime);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double euro = resultSet.getDouble("eur");
                    double usd = resultSet.getDouble("usd");
                    double gpb = resultSet.getDouble("GBP");
                    double toman = resultSet.getDouble("toman");
                    double yen = resultSet.getDouble("yen");
                    this.price.setText(String.valueOf(usd));
                }
            } catch (Throwable var20) {
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (Throwable var19) {
                        var20.addSuppressed(var19);
                    }
                }

                throw var20;
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException var21) {
            var21.printStackTrace();
        }

    }
}
