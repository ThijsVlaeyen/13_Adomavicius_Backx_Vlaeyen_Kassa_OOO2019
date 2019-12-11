package controllers;

import database.ProductDB;
import model.*;
import view.panels.CashierSalesPane;

import java.util.List;

public class CashierController implements Observer {
    private CashierSalesPane view;
    private ProductDB db;
    private ShoppingCart cart;
    private ShoppingCart holdingShoppingCart;

    public CashierController(ProductDB db) {
        this.db = db;
        db.addObserver(EventType.PRODUCTSCHANGED, this);
        cart = new ShoppingCart();
        holdingShoppingCart = new ShoppingCart();
    }

    public void setView(CashierSalesPane view) {
        this.view = view;
    }

    public void addArticle(int code) {
        if (db.isProductExist(code)){
            cart.addProduct(db.getProduct(code));
            view.setNotExistingCode(false);
        }else{
            view.setNotExistingCode(true);
        }
        cart.calculateDiscount();
        db.updateObservers(EventType.PRODUCTSCHANGED, cart);
    }

    public void removeArticles(List<Product> products){
        for (Product p:products){
            cart.remove(p);
        }
        db.updateObservers(EventType.PRODUCTSCHANGED, cart);
    }

    public void addOnHold() {
        if(cart.addOnHold()) {
            holdingShoppingCart = (ShoppingCart) cart.clone();
            cart.clear();
            db.updateObservers(EventType.PRODUCTSCHANGED, cart);
        }
        else {
            view.showAlert("Invalid operation");
        }
    }

    public void takeFromHold() {
        if(holdingShoppingCart.getItemsList().isEmpty()) {
            view.showAlert("there are no items to add");
        }
        else {
            cart = (ShoppingCart) holdingShoppingCart.clone();
            holdingShoppingCart.clear();
            db.updateObservers(EventType.PRODUCTSCHANGED, cart);
        }
    }

    public void payment() {
        if(cart.payment()) {
            db.payment(cart);
            cart.clear();
        }
    }

    public void close() {
        if(cart.closeSale()) {
            view.updateTotalAmount(cart.getFinalPrice());
        }
    }

    @Override
    public void update(Object object) {
        ShoppingCart cart = (ShoppingCart) object;
        view.updateTable(cart.getItemsList());
        view.updateTotalAmount(cart.getTotalPrice());
        view.updateDiscount(cart.calculateDiscount());
    }
}