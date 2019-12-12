package model.states;

import database.ProductDB;
import model.EventType;
import model.Product;
import model.ShoppingCart;

import java.util.List;

public class ClosedState extends State {
    ShoppingCart shoppingCart;
    ProductDB db;

    public ClosedState(ShoppingCart shoppingCart, ProductDB db) {
        this.shoppingCart = shoppingCart;
        this.db = db;
    }


    @Override
    public void payment() {
        db.payment(shoppingCart);
        shoppingCart.setState(shoppingCart.getPaidState());
        shoppingCart.clear();
    }
}
