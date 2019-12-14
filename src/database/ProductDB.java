package database;

import controllers.Observable;
import controllers.Observer;
import model.EventType;
import model.IO.LoadSaveStrategy;
import model.Log;
import model.Product;
import model.ShoppingCart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDB implements Observable {
    private Map<Integer, Product> productsMap;
    private LoadSaveStrategy loadSaveStrategy;
    private Map<EventType, List<Observer>> observers;
    private ShoppingCart holdingShoppingCart;

    public ProductDB() {
        this.productsMap = new HashMap<>();
        observers = new HashMap<>();
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
    }

    public void decreaseStock(Product product) {
        productsMap.get(product.getId()).decreaseStock();
    }

    public void payment(ShoppingCart cart) {
        updateStocks(cart.getItemsList());
        updateObservers(EventType.LOG, cart);
        updateObservers(EventType.PRODUCTSCHANGED, cart);
    }

    public void closeSale(ShoppingCart cart) {
        updateObservers(EventType.PRODUCTSCHANGED, cart);
    }

    public void addOnHold(ShoppingCart cart) {
        holdingShoppingCart = (ShoppingCart) cart.clone();
        cart.clear();
    }

    public ShoppingCart takeFromHold() {
        return holdingShoppingCart;
    }

    @Override
    public void addObserver(EventType e, Observer o) {
        if (e.equals(EventType.LOG)){
        }
        if (observers.get(e) == null){
            List<Observer> observers = new ArrayList<>();
            observers.add(o);
            this.observers.put(e,observers);
        }else{
            List<Observer> observers = this.observers.get(e);
            observers.add(o);
        }
    }

    @Override
    public void updateObservers(EventType e, Object o) {
        for (Observer observer:this.observers.get(e)) {
            observer.update(o);
        }
    }

    @Override
    public void removeObserver(EventType e, Observer o) {
        this.observers.get(e).remove(o);
    }
}