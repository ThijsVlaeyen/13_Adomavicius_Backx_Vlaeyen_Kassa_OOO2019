package model;

import javafx.scene.shape.Polyline;

import java.util.*;

public class ShoppingCart {
    private Map<Product,Integer> cart;

    public ShoppingCart(){
        this.cart = new HashMap<>();
    }

    public ShoppingCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

//    public void addProduct(Product p){
//        this.cart.put(p,1);
//    }
//
//    public void addProduct(Product p, int amount){
//        this.cart.put(p,amount);
//    }

    public void addProduct(Product p){
        if (cart.get(p) != null){
            cart.put(p,cart.get(p)+1);
        }else{
            cart.put(p,1);
        }
    }

    public void remove(Product p){
        if (this.cart.get(p) != null){
            if (this.cart.get(p) >1){
                this.cart.put(p,this.cart.get(p)-1);
            }else{
                this.cart.remove(p);
            }
        }
    }

    public void clear() {
        cart.clear();
    }

    public Map<Product,Integer> getItems(){
        return this.cart;
    }

    public List<Product> getItemsList(){
        List<Product> products = new ArrayList<>();
        for (Map.Entry<Product,Integer> entry:this.cart.entrySet()){
            for (int i=0;i<entry.getValue();i++) {
                products.add(entry.getKey());
            }
        }
        return products;
    }

    public double getTotalPrice(){
        List<Product> products = getItemsList();
        double price = 0;
        for (Product p:products){
            price += p.getPrice();
        }
        return price;
    }

    public Object clone()
    {
        return new ShoppingCart(new HashMap<Product, Integer>(cart));
    }
}
