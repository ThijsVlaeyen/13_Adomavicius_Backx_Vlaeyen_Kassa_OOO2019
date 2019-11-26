package database;

import model.IO.LoadSaveStrategy;
import model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDB {
    private Map<Integer,Product> products;
    private LoadSaveStrategy loadSaveStrategy;

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
        this.products = new HashMap<>();
        ArrayList<Product> products = loadSaveStrategy.load();
        for (Product p:products) {
            this.products.put(p.getId(), p);
        }
        System.out.println(this.products);
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

}
