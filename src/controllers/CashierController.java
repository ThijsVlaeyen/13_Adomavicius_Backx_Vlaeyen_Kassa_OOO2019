package controllers;

import database.ProductDB;
import model.*;
import view.panels.CashierSalesPane;

import java.util.List;

public class CashierController implements Observer {
    private CashierSalesPane view;
    private ProductDB db;
    private ShoppingCart model;
    private ShoppingCart holdingShoppingCart;

    public CashierController(ProductDB db) {
        this.db = db;
        model = new ShoppingCart();
        holdingShoppingCart = new ShoppingCart();
    }

    public void setView(CashierSalesPane view) {
        this.view = view;
    }

    public void addArticle(int code) {
        if (db.isProductExist(code)){
            model.addProduct(db.getProduct(code));
            view.setNotExistingCode(false);
            view.updateTable(model.getItemsList());
            view.updateTotalAmount(model.getTotalPrice());
        }else{
            view.setNotExistingCode(true);
        }
        model.calculateDiscount();
        view.updateDiscount(model.calculateDiscount());
        db.updateObservers(EventType.TODO, model.getItemsList());
    }

    public void removeArticle(Product p){
        remove(p);
        view.updateTable(model.getItemsList());
        view.updateTotalAmount(model.getTotalPrice());
        db.updateObservers(EventType.TODO, model.getItemsList());
    }

    public void remove(Product p){
        model.remove(p);
    }

    public void removeArticles(List<Product> products){
        for (Product p:products){
            remove(p);
        }
        view.updateTable(model.getItemsList());
        view.updateTotalAmount(model.getTotalPrice());
        db.updateObservers(EventType.TODO, model.getItemsList());
    }

    public void addOnHold() {
        if(model.addOnHold()) {
            holdingShoppingCart = (ShoppingCart) model.clone();
            model.clear();
            view.updateTable(model.getItemsList());
            view.updateTotalAmount(model.getTotalPrice());
            db.updateObservers(EventType.TODO, model.getItemsList());
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
            model = (ShoppingCart) holdingShoppingCart.clone();
            holdingShoppingCart.clear();
            view.updateTable(model.getItemsList());
            view.updateTotalAmount(model.getTotalPrice());
        }
        db.updateObservers(EventType.TODO, model.getItemsList());
    }

    public void payment() {
        if(model.payment()) {
            db.updateStocks(model.getItemsList());
            model.clear();
            view.updateTable(model.getItemsList());
            view.updateTotalAmount(model.getTotalPrice());
            db.updateObservers(EventType.TODO, new Log(model.getTotalPrice(), model.calculateDiscount(), model.getFinalPrice()));
        }
    }

    public void close() {
        view.updateTotalAmount(model.getFinalPrice());
    }

    @Override
    public void update(Object object) {

    }
}