package controllers;

import database.Observable;
import database.ProductDB;
import model.Product;
import model.ShoppingCart;
import view.CashierView;
import view.ClientView;
import view.panels.CashierSalesPane;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class CashierController implements Observer, ClientViewObservable {
    private CashierSalesPane view;
    private ProductDB db;
    private ShoppingCart model;
    private ClientViewObserver observer;
    private ShoppingCart holdingShoppingCart;

    public CashierController(ProductDB db) {
        this.db = db;
        model = new ShoppingCart();
        db.addObserver(this);
        holdingShoppingCart = new ShoppingCart();
    }

    public void setView(CashierSalesPane view) {
        this.view = view;
    }

    @Override
    public void update() {
        view.updateDisplay();
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
        updateObservers();
    }

    public void removeArticle(Product p){
        remove(p);
        view.updateTable(model.getItemsList());
        view.updateTotalAmount(model.getTotalPrice());
        updateObservers();
    }

    public void remove(Product p){
        if (p != null){
            model.remove(p);
        }
    }

    public void removeArticles(List<Product> products){
        for (Product p:products){
            remove(p);
        }
        view.updateTable(model.getItemsList());
        view.updateTotalAmount(model.getTotalPrice());
        updateObservers();
    }

    public void addOnHold() {
        if(holdingShoppingCart.getItemsList().isEmpty()) {
            holdingShoppingCart = (ShoppingCart) model.clone();
            model.clear();
            view.updateTable(model.getItemsList());
            view.updateTotalAmount(model.getTotalPrice());
        }
        else {
            view.showAlert("there are already items on hold");
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
    }

    @Override
    public void addObserver(ClientViewObserver o) {
        this.observer = o;
    }

    @Override
    public void updateObservers(){
        this.observer.update(model.getItemsList());
    }

    @Override
    public void removeObserver(ClientViewObserver o) {

    }

}