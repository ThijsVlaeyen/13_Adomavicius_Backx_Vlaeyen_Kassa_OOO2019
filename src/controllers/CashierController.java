package controllers;

import database.Observable;
import database.ProductDB;
import model.Product;
import model.ShoppingCart;
import view.CashierView;
import view.ClientView;
import view.panels.CashierSalesPane;

import java.util.ArrayList;

public class CashierController implements Observer, ClientViewObservable {
    private CashierSalesPane view;
    private ProductDB db;
    private ShoppingCart model;
    private ClientViewObserver observer;

    public CashierController(ProductDB db) {
        this.db = db;
        model = new ShoppingCart();
        db.addObserver(this);
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
        if (p != null){
            model.remove(p);
            view.updateTable(model.getItemsList());
        }
        updateObservers();
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