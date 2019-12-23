package model.states;

import database.ProductDB;
import model.EventType;
import model.Product;
import model.ShoppingCart;

import java.util.List;

/**
 @Author Tomas Adomavicius
 */

public class ClosedState extends State {
    private ShoppingCart shoppingCart;
    private ProductDB db;

    public ClosedState(ShoppingCart shoppingCart, ProductDB db) {
        this.shoppingCart = shoppingCart;
        this.db = db;
    }

    @Override
    public void payment() {
        db.payment(shoppingCart);
        shoppingCart.setState(shoppingCart.getOpenState());
        shoppingCart.clear();
        this.db.updateObservers(EventType.PRODUCTSCHANGED,shoppingCart);
    }
}
