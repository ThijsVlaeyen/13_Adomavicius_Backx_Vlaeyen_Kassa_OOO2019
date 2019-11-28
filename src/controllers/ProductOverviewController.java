package controllers;

import database.ProductDB;
import model.IO.LoadSaveProperties;
import model.IO.LoadSaveStrategy;
import model.IO.LoadSaveStrategyFactory;
import model.Product;

import java.util.ArrayList;
import java.util.Collections;

public class ProductOverviewController {
    private ProductDB db;

    public ProductOverviewController(ProductDB db){
        this.db = db;
    }

    public ArrayList<Product> getProducts(){
        LoadSaveStrategyFactory factory = new LoadSaveStrategyFactory();
        LoadSaveProperties properties = new LoadSaveProperties();
        LoadSaveStrategy loadSaveStrategy = factory.createObject(properties.load());
        db.setLoadSaveStrategy(loadSaveStrategy);
        ArrayList<Product> products = db.getProducts();
        Collections.sort(products);
       return products;
    }
}
