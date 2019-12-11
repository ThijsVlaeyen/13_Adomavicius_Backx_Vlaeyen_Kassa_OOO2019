package controllers;

import database.ProductDB;
import model.Log;
import view.panels.LogPane;

public class LogController implements Observer {
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
