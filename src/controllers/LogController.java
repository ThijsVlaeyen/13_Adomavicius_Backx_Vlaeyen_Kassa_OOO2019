package controllers;

import database.ProductDB;
import model.EventType;
import model.Log;
import view.panels.LogPane;

public class LogController implements Observer {
    private LogPane view;

    public LogController(ProductDB db) {
        db.addObserver(EventType.LOG, this);
    }

    public void setView(LogPane view){
        this.view = view;
    }

    @Override
    public void update(Object object) {
        view.update((Log) object);
    }
}
