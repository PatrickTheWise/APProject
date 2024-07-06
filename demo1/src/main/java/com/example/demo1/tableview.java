package com.example.demo1;

public class tableview {
    private double mx;
    private double mp;
    private String market;
    private double changes;
    private double price;

    public tableview(double mx,double mp,String market,double changes,double price){
        this.changes = changes;
        this.mx = mx;
        this.mp = mp;
        this.market = market;
        this.price = price;
    }
    public tableview(){}

    public double getPrice() {
        return price;
    }

    public double getChanges() {return changes; }

    public String getMarket() {
        return market;
    }

    public double getMp() {
        return mp;
    }

    public double getMx() {
        return mx;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setChanges(double changes) {
        this.changes = changes;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public void setMx(double mx) {
        this.mx = mx;
    }

    public void setMp(double mp) {
        this.mp = mp;
    }
}
