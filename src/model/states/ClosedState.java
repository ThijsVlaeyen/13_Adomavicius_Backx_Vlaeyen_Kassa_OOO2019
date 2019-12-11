package model.states;

import database.ProductDB;
import model.Product;
import model.ShoppingCart;

import java.util.List;

public class ClosedState implements State {
    ShoppingCart shoppingCart;
    ProductDB db;

    public ClosedState(ShoppingCart shoppingCart, ProductDB db) {
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
        db.payment(shoppingCart);
        shoppingCart.setState(shoppingCart.getPaidState());
        shoppingCart.clear();
    }
}
