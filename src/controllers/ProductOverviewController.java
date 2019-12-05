package controllers;

import database.ProductDB;
import model.IO.LoadSaveProperties;
import model.IO.LoadSaveStrategy;
import model.IO.LoadSaveStrategyFactory;
import model.Product;
import view.panels.ProductOverviewPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductOverviewController implements ClientViewObserver {
    private ProductDB db;
    private ProductOverviewPane view;
    private LoadSaveProperties properties;

    public ProductOverviewController(ProductDB db){
        this.db = db;
        db.addObserver(this);
        properties = new LoadSaveProperties();
    }

    public ArrayList<Product> getProducts(){
        LoadSaveStrategyFactory factory = new LoadSaveStrategyFactory();
        LoadSaveStrategy loadSaveStrategy = factory.createObject(LoadSaveProperties.getLoadSave());
        db.setLoadSaveStrategy(loadSaveStrategy);
        ArrayList<Product> products = db.getProducts();
        Collections.sort(products);
       return products;
    }

    public void setView(ProductOverviewPane view){
        this.view = view;
    }

    @Override
    public void update(List<Product> products) {

    }
}
