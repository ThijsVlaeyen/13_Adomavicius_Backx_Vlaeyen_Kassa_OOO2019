package model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product,Integer> cart;

    public ShoppingCart(){
        this.cart = new HashMap<>();
    }

    public void addProduct(Product p){
        this.cart.put(p,1);
    }

    public void addProduct(Product p, int amount){
        this.cart.put(p,amount);
    }

    public void remove(Product p){
        this.cart.remove(p);
    }

    public Map<Product,Integer> getItems(){
        return this.cart;
    }
}
