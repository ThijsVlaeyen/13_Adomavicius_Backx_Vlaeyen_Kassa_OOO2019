package controllers;

import database.ProductDB;
import model.EventType;
import model.Product;
import model.ShoppingCart;
import view.ClientView;

import java.util.List;
import java.util.Map;

public class ClientViewController implements Observer {
    private ClientView view;
    private ShoppingCart model;
    private ProductDB db;

    public ClientViewController(ProductDB db){
        this.db = db;
        db.addObserver(EventType.PRODUCTSCHANGED, this);
        this.model = new ShoppingCart(db);
    }

    public void setView(ClientView view){
        this.view = view;
    }

    public double getTotalPrice(){
        double totalPrice=0.0;
        Map<Product,Integer> products = model.getItems();
        for(Map.Entry<Product,Integer> product:products.entrySet()){
            totalPrice+= (product.getKey().getPrice())*product.getValue();
        }
        return totalPrice;
    }

    public Map<Product,Integer> getItems(){
        return model.getItems();
    }

    @Override
    public void update(Object object) {
        ShoppingCart cart = (ShoppingCart) object;
        this.model.getItems().clear();
        for (Product p:cart.getItemsList()){
            model.addProduct(p);
        }
        this.view.update();
    }
}
