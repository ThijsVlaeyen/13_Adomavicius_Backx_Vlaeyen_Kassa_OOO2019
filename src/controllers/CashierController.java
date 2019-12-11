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
        db.addObserver(EventType.UPDATETABLE, this);
        db.addObserver(EventType.UPDATETABLE, this);
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
            view.updateTable(cart.getItemsList());
            view.updateTotalAmount(cart.getTotalPrice());
        }else{
            view.setNotExistingCode(true);
        }
        cart.calculateDiscount();
        view.updateDiscount(cart.calculateDiscount());
        db.updateObservers(EventType.TODO, cart.getItemsList());
    }

    public void removeArticle(Product p){
        remove(p);
        view.updateTable(cart.getItemsList());
        view.updateTotalAmount(cart.getTotalPrice());
        db.updateObservers(EventType.TODO, cart.getItemsList());
    }

    public void remove(Product p){
        cart.remove(p);
    }

    public void removeArticles(List<Product> products){
        for (Product p:products){
            remove(p);
        }
        view.updateTable(cart.getItemsList());
        view.updateTotalAmount(cart.getTotalPrice());
        db.updateObservers(EventType.TODO, cart.getItemsList());
    }

    public void addOnHold() {
        if(cart.addOnHold()) {
            holdingShoppingCart = (ShoppingCart) cart.clone();
            cart.clear();
            view.updateTable(cart.getItemsList());
            view.updateTotalAmount(cart.getTotalPrice());
            db.updateObservers(EventType.TODO, cart.getItemsList());
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
            view.updateTable(cart.getItemsList());
            view.updateTotalAmount(cart.getTotalPrice());
        }
        db.updateObservers(EventType.TODO, cart.getItemsList());
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
    }
}