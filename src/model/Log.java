package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 @Author Tomas Adomavicius
 */

public class Log {
    private double totalPrice;
    private double discount;
    private double finalPrice;
    private Date date;
    private DateFormat dateFormat;

    public Log(double totalPrice, double discount, double finalPrice) {
        this.totalPrice = totalPrice;
        this.discount = discount;
        this. finalPrice = finalPrice;
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.date = new Date();
    }

    public String toString() {
        return dateFormat.format(date) + " " + totalPrice + " " + discount + " " + finalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public Date getDate() {
        return date;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }
}