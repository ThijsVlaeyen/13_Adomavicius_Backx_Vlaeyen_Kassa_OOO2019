package model.states;

import database.ProductDB;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.EventType;
import model.Product;
import model.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 @Author Tomas Adomavicius
 */

public class OpenState extends State {
    ShoppingCart shoppingCart;
    ProductDB db;

    public OpenState(ShoppingCart shoppingCart, ProductDB db) {
        this.shoppingCart = shoppingCart;
        this.db = db;
    }

    @Override
    public void add(int code) {
        if (db.isProductExist(code)) {
            shoppingCart.addProduct(db.getProduct(code));
            shoppingCart.calculateDiscount();
            db.updateObservers(EventType.PRODUCTSCHANGED, shoppingCart);
        } else {
            Alert empty = new Alert(Alert.AlertType.INFORMATION,"This product is out of stock", ButtonType.OK);
            empty.show();
        }
    }

    @Override
    public void addOnHold() {
        db.addOnHold(shoppingCart);
        shoppingCart.setState(shoppingCart.getOnHoldState());
        db.updateObservers(EventType.PRODUCTSCHANGED, shoppingCart);
    }

    @Override
    public ShoppingCart takeFromHold() {
        shoppingCart = db.takeFromHold();
        if (shoppingCart != null) {
            db.updateObservers(EventType.PRODUCTSCHANGED, shoppingCart);
            shoppingCart.setState(shoppingCart.getOpenState());
        }else{
            shoppingCart = new ShoppingCart(db);
        }
        return shoppingCart;
    }

    @Override
    public void remove(List<Product> products) {
        for (Product p:products){
            shoppingCart.remove(p);
        }
        db.updateObservers(EventType.PRODUCTSCHANGED, shoppingCart);
    }

    @Override
    public void closeSale() {
        db.closeSale(shoppingCart);
        shoppingCart.setState(shoppingCart.getClosedState());
        db.updateObservers(EventType.PRODUCTSCHANGED,shoppingCart);
    }

}
