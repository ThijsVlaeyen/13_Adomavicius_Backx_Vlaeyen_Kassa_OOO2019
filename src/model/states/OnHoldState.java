package model.states;

import database.ProductDB;
import model.EventType;
import model.Product;
import model.ShoppingCart;

import java.util.List;

public class OnHoldState extends State {
    ShoppingCart shoppingCart;
    ProductDB db;

    public OnHoldState(ShoppingCart shoppingCart, ProductDB db) {
        this.shoppingCart = shoppingCart;
        this.db = db;
    }

    @Override
    public void takeFromHold() {
        //cart = (ShoppingCart) holdingShoppingCart.clone();
        //holdingShoppingCart.clear();
        //db.updateObservers(EventType.PRODUCTSCHANGED, cart);
    }
}
