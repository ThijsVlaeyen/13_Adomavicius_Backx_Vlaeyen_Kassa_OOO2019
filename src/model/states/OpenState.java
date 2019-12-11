package model.states;

import database.ProductDB;
import model.EventType;
import model.Product;
import model.ShoppingCart;

import java.util.List;

public class OpenState implements State {
    ShoppingCart shoppingCart;
    ProductDB db;

    public OpenState(ShoppingCart shoppingCart, ProductDB db) {
        this.shoppingCart = shoppingCart;
        this.db = db;
    }

    @Override
    public void add(int code) {
        if (db.isProductExist(code)){
            shoppingCart.addProduct(db.getProduct(code));
            //view.setNotExistingCode(false);
        }else{
            //view.setNotExistingCode(true);
        }
        shoppingCart.calculateDiscount();
        db.updateObservers(EventType.PRODUCTSCHANGED, shoppingCart);
    }

    @Override
    public void addOnHold() {
        //holdingShoppingCart = (ShoppingCart) shoppingCart.clone();
        //shoppingCart.clear();
        //db.updateObservers(EventType.PRODUCTSCHANGED, shoppingCart);
        //
        //else {
        //    view.showAlert("Invalid operation");
        //}
        //shoppingCart.setState(shoppingCart.getOnHoldState());
    }

    @Override
    public void takeFromHold() {
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
    }

    @Override
    public void payment() {

    }
}
