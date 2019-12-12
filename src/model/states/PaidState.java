package model.states;

import database.ProductDB;
import model.Product;
import model.ShoppingCart;

import java.util.List;

public class PaidState extends State {
    ShoppingCart shoppingCart;
    ProductDB db;

    public PaidState(ShoppingCart shoppingCart, ProductDB db) {
        this.shoppingCart = shoppingCart;
        this.db = db;
    }

    @Override
    public void add(int code) {

    }

    @Override
    public void addOnHold() {

    }

    @Override
    public void takeFromHold() {

    }

    @Override
    public void remove(List<Product> products) {

    }

    @Override
    public void closeSale() {

    }

    @Override
    public void payment() {

    }
}
