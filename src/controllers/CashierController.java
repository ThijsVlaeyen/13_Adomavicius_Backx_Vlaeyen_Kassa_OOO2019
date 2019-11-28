package controllers;

import database.Observable;
import database.ProductDB;
import model.Product;
import view.CashierView;
import view.ClientView;
import view.panels.CashierSalesPane;

import java.util.ArrayList;

public class CashierController implements Observer, ClientViewObservable {
    private CashierSalesPane view;
    private ProductDB model;
    private ClientViewObserver observer;

    public CashierController(ProductDB model) {
        this.model = model;
        model.addObserver(this);
    }

    public void setView(CashierSalesPane view) {
        this.view = view;
    }

    @Override
    public void update() {
        view.updateDisplay();
    }

    public void addArticle(int code) {
        if(model.isProductExist(code)) {
            model.addScannedArticle(code);
            view.setNotExistingCode(false);
            view.updateScannedItemsTable(model.getScanedProducts());
            view.updateTotalAmount(model.getTotalAmount());
        }
        else {
            view.setNotExistingCode(true);
        }
        updateObservers();
    }

    @Override
    public void addObserver(ClientViewObserver o) {
        this.observer = o;
    }

    @Override
    public void updateObservers(){
        this.observer.update(model.getScanedProducts());
    }

    @Override
    public void removeObserver(ClientViewObserver o) {

    }

}