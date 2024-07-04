package com.example.demo1;

public class tableview {
    public double maxprice;
    public double minprice;
    public String market;
    public double changes;
    public double price;

    public tableview(double maxprice,double minprice,String market,double changes,double price){
        this.changes = changes;
        this.maxprice = maxprice;
        this.minprice = minprice;
        this.market = market;
        this.price = price;
    }
    public tableview(){

    }

}
