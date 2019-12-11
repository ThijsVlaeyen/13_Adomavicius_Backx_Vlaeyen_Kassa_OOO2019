package controllers;

import model.Product;
import model.ShoppingCart;
import view.ClientView;

import java.util.List;
import java.util.Map;

public class Controller implements Observer {
    private ClientView view;
    private CashierController observable;
    private ShoppingCart model;

    public Controller(){
        this.model = new ShoppingCart();
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
        List<Product> products = (List<Product>) object;
        this.model.getItems().clear();
        for (Product p:products){
            model.addProduct(p);
        }
        this.view.update();
    }
}
