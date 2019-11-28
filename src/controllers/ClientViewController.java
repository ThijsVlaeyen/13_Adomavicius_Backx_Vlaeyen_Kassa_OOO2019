package controllers;

import model.Product;
import model.ShoppingCart;
import view.ClientView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientViewController implements ClientViewObserver{
    private ClientView view;
    private CashierController observable;
    private Map<Product,Integer> products;
    private ShoppingCart model;

    public ClientViewController(){
        this.products = new HashMap<>();
        this.model = new ShoppingCart();
    }

    public void setView(ClientView view){
        this.view = view;
    }

    @Override
    public void update(List<Product> products) {
        this.model.getItems().clear();
        for (Product p:products){
            Map<Product,Integer> items = this.model.getItems();
            if (items.containsKey(p)){
                int amount = items.get(p);
                amount +=1;
                this.model.addProduct(p,amount);
            }else{
                this.model.addProduct(p);
            }
        }
        this.view.update();
    }

    public Map<Product,Integer> getItems(){
        return model.getItems();
    }
}
