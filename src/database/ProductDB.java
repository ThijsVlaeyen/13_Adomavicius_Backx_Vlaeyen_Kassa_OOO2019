package database;

import controller.Observable;
import controller.Observer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.EventType;
import model.IO.LoadSaveProperties;
import model.IO.LoadSaveStrategy;
import model.IO.LoadSaveStrategyFactory;
import model.Product;
import model.ShoppingCart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
@Author Rafael Backx,Thomas Adomavicius
*/
public class ProductDB implements Observable {
    private Map<Integer, Product> productsMap;
    private LoadSaveStrategy loadSaveStrategy;
    private Map<EventType, List<Observer>> observers;
    private ShoppingCart holdingShoppingCart;

    public ProductDB() {
        productsMap = new HashMap<>();
        observers = new HashMap<>();
        LoadSaveStrategyFactory factory = LoadSaveStrategyFactory.getInstance();
        LoadSaveProperties ls = new LoadSaveProperties();
        this.loadSaveStrategy = factory.create(LoadSaveProperties.getLoadSave());
    }


    public void save(){
        loadSaveStrategy.save(new ArrayList<>(this.productsMap.values()));
    }

    public Product getProduct(int code){
        return this.productsMap.get(code);
    }

    public ArrayList<Product> load(){
        for (Object obj : loadSaveStrategy.load()) {
            Product i = (Product) obj;
            productsMap.put(i.getId(), i);
        }
        return new ArrayList<>(this.productsMap.values());
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

    public boolean isProductExist(int code) {
        try{
            return productsMap.get(code).getStock() != 0;
        }catch (NullPointerException e){
            return false;
        }
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
        cart = new ShoppingCart(this);
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
        if (holdingShoppingCart == null || holdingShoppingCart.getItemsList().isEmpty()){
            Alert empty = new Alert(Alert.AlertType.INFORMATION,"there are no carts on hold", ButtonType.OK);
            empty.show();
            return null;
        }else {
            return holdingShoppingCart;
        }
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