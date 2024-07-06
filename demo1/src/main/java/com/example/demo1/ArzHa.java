package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;

public class ArzHa {
    //double[][] arz = new double[5][5]; // 1: tooman , 2: usd , 3: GBP , 4: UER , 5: CY ;
    public  double usd;
    public  double yen;
    public  double tmn;
    public  double gbp;
    public  double eur;
    public  int hour;
    public  int min;
    public ArzHa(double usd,double yen,double tmn,double gbp,double eur,int hour,int min){
        this.usd = usd;
        this.yen = yen;
        this.tmn = tmn;
        this.gbp = gbp;
        this.eur = eur;
        this.hour = hour;
        this.min = min;
    }
}
