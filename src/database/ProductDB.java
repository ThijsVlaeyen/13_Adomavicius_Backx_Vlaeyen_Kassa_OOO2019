package database;

import controllers.Observer;
import model.IO.LoadSaveStrategy;
import model.Product;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDB implements Observable {
    private String path;
    private List<Product> products;//todo maybe delete in the future
    private List<Product> scanedProducts;
    private int totalScanedAmount;
    private Map<Integer, Product> productsMap;
    private Map<Integer,Product> products;
    private LoadSaveStrategy loadSaveStrategy;
    private ArrayList<Observer> observers;

    public ProductDB() {
        observers = new ArrayList<Observer>();
        productsMap = new HashMap<Integer, Product>();
        scanedProducts = new ArrayList<Product>();
        totalScanedAmount = 0;
        productsMap.put(1, new Product(1, "test", "group", 1.0, 3));//todo just temporary testing data
    }

    public ProductDB(String path){
        this();
        this.path = path;
    public ProductDB(){
        this.products = new HashMap<>();
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public void save(){
        loadSaveStrategy.save(new ArrayList<>(this.products.values()));
    }

    public void load(){
        this.products = loadSaveStrategy.load();
        for (Product i : products) productsMap.put(i.getId(), i); 
    }

    public void add(Product p){
        if (p != null){
            this.products.put(p.getId(),p);
        }
    }

    public void remove(Product p){
        if (p != null){
            this.products.remove(p);
        }
    }

    public ArrayList<Product> getProducts(){
        this.load();
        return new ArrayList<>(this.products.values());
    }

    public boolean isProductExist(int code) {
        return this.productsMap.containsKey(code);
    }

    public void addScannedArticle(int code) {
        scanedProducts.add(productsMap.get(code));
        totalScanedAmount += productsMap.get(code).getStock();
    }

    public int getTotalAmount() {
        return totalScanedAmount;
    }

    public List<Product> getScanedProducts() {
        return scanedProducts;
    }

    @Override
    public void updateObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }
}