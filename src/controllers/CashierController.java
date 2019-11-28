package controllers;

import database.ProductDB;
import view.CashierView;
import view.panels.CashierSalesPane;

public class CashierController implements Observer {
    private CashierSalesPane view;
    private ProductDB model;

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
    }
}