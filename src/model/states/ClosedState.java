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
    private int payments;

    public ClosedState(ShoppingCart shoppingCart, ProductDB db) {
        this.shoppingCart = shoppingCart;
        this.db = db;
        payments = 0;
    }

    @Override
    public void payment() {
        db.payment(shoppingCart);
        shoppingCart.setState(shoppingCart.getOpenState());
        shoppingCart.clear();
        this.db.updateObservers(EventType.PRODUCTSCHANGED,shoppingCart);
        if(payments < 3) {
            payments++;
        }
        else {
            payments = 0;
            db.takeFromHold();
        }
    }
}
