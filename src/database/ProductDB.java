package database;

import controllers.Observable;
import controllers.Observer;
import model.IO.LoadSaveStrategy;
import model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDB implements Observable {
    private Map<Integer, Product> productsMap;
    private LoadSaveStrategy loadSaveStrategy;
    private ArrayList<Observer> observers;

    public ProductDB() {
        this.productsMap = new HashMap<>();
        observers = new ArrayList<Observer>();
        productsMap = new HashMap<Integer, Product>();
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public void save(){
        loadSaveStrategy.save(new ArrayList<Product>(this.productsMap.values()));
    }

    public Product getProduct(int code){
        return this.productsMap.get(code);
    }

    public void load(){
        for (Product i : loadSaveStrategy.load()) productsMap.put(i.getId(), i);
    }

    public void add(Product p){
        if (p != null){
            this.productsMap.put(p.getId(),p);
        }
    }

    public void remove(Product p){
        if (p != null){
            this.productsMap.remove(p);
        }
    }

    public ArrayList<Product> getProducts(){
        this.load();
        return new ArrayList<>(this.productsMap.values());
    }

    public boolean isProductExist(int code) {
        return this.productsMap.containsKey(code);
    }

    public void updateStocks(List<Product> products) {
        for(Product p : products) {
            decreaseStock(p);
        }
        save();
        load();
        updateObservers(observers);
    }

    public void decreaseStock(Product product) {
        productsMap.get(product.getId()).decreaseStock();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void updateObservers(Object obj) {
        List<Observer> list = (List<Observer>)obj;
        for (Observer o : list) {
            o.update((new ArrayList<Product>(productsMap.values())));
        }
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }
}