package controller;

import database.ProductDB;
import model.EventType;
import model.IO.*;
import model.Product;
import view.panels.ProductOverviewPane;

import java.util.ArrayList;
import java.util.Collections;

public class ProductOverviewController implements Observer {
private ProductDB db;
private ProductOverviewPane view;
private LoadSaveProperties properties;

public ProductOverviewController(ProductDB db) {
   this.db = db;
   db.addObserver(EventType.PRODUCTSCHANGED, this);
   properties = new LoadSaveProperties();
}

public ArrayList<Product> getProducts() {
   LoadSaveStrategyFactory factory = new LoadSaveStrategyFactory();
   LoadSaveStrategy loadSaveStrategy = factory.createObject(LoadSaveProperties.getLoadSave());
   db.setLoadSaveStrategy(loadSaveStrategy);
   ArrayList<Product> products = db.load();
   Collections.sort(products);
   return products;
}

public void setView(ProductOverviewPane view) {
   this.view = view;
}

@Override
public void update(Object object) {
   view.update(getProducts());
}
}
