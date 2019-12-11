package controllers;

import database.ProductDB;
import model.Log;
import model.Product;
import view.panels.LogPane;

import java.util.List;

public class LogController implements ClientViewObserver {
    private ProductDB db;
    private LogPane view;

    public LogController(ProductDB db) {
        this.db = db;
    }

    public void setView(LogPane view){
        this.view = view;
    }

    @Override
    public void update(Object object) {
        view.update((Log) object);
    }
}
