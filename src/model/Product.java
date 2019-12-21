package model;

import java.net.PortUnreachableException;
import java.util.Objects;
/**
@Author Rafael Backx
*/

public class Product implements Comparable<Product> {
    private int id;
    private double price;
    private String name;
    private int stock;
    private String group;

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public String getGroup() {
        return group;
    }

    public Product(double price, String name, int stock, int id, String group) {
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.id = id;
        this.group = group;
    }

    public Product(int id,String name,String group,double price,int stock){
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.id = id;
        this.group = group;
    }


    public int getId() {
        return id;
    }

    public void decreaseStock() {
        this.stock--;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", id='" + id + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.name);
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Product){
            return this.id == ((Product) obj).id;
        }
        return false;
    }
}
