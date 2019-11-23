package database;

import model.IO.LoadSaveStrategy;
import model.Product;

import java.util.List;

public class ProductDB {
    private String path;
    private List<Product> products;
    private LoadSaveStrategy loadSaveStrategy;

    public ProductDB(String path){
        this.path = path;
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public void save(){
        loadSaveStrategy.save(this.products);
    }

    public void load(){
        this.products = loadSaveStrategy.load();
    }

    public void add(Product p){
        if (p != null){
            this.products.add(p);
        }
    }

    public void remove(Product p){
        if (p != null){
            this.products.remove(p);
        }
    }

    public String getPath(){
        return this.path;
    }

}
