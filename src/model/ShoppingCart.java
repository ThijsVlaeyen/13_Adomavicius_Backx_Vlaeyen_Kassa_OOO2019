package model;

import database.ProductDB;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.IO.LoadSaveProperties;
import model.states.*;

import java.util.*;
/**
@Author Rafael Backx
*/
public class ShoppingCart {
    private State openState;
    private State onHoldState;
    private State closedState;
    private State paidState;
    private State state;

    private Map<Product,Integer> cart;
    private ProductDB db;

    public ShoppingCart(ProductDB db){
        this.db = db;
        this.cart = new HashMap<>();
        openState = new OpenState(this, db);
        onHoldState = new OnHoldState(this, db);
        closedState = new ClosedState(this, db);
        paidState = new PaidState(this, db);
        state = openState;
    }

    public ShoppingCart(Map<Product, Integer> cart, ProductDB db) {
        this(db);
        this.cart = cart;
    }

    public void addProduct(Product p){
        if (checkStock(p)) {
            boolean found = false;
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                if (entry.getKey().equals(p)) {
                    found = true;
                    cart.put(entry.getKey(), cart.get(entry.getKey()) + 1);
                    break;
                }
            }
            if (!found) {
                cart.put(p, 1);
            }
        }
    }

    private boolean checkStock(Product p) {
        List<Product> products = this.getItemsList();
        for (Product product : products) {
            if (product.equals(p)) {
                int currentstock = db.getProduct(p.getId()).getStock() - countOccurences(product);
                return currentstock > 0;
            }
        }
//        if (this.getItems().get(p) != null) {
//            int currentstock = db.getProduct(p.getId()).getStock() - this.getItems().get(p);
//            return currentstock > 0;
//        }
        return db.getProduct(p.getId()).getStock()>0;
    }

    private int countOccurences(Product p){
        List<Product> products = this.getItemsList();
        int result = 0;
        for (Product product : products){
            if (product.equals(p)){
                result++;
            }
        }
        return result;
    }

    public void remove(Product p){
        if (p != null && this.cart.get(p) != null){
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
            for (int i=0;i<entry.getValue() && i< entry.getKey().getStock();i++) {
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
        return new ShoppingCart(new HashMap<Product, Integer>(cart), db);
    }

    public void addOnHold() {
        state.addOnHold();
    }

    public void payment() {
        state.payment(); }

    public ShoppingCart takeFromHold() {
        return state.takeFromHold();
    }

    public void delete(List<Product> products) {
        state.remove(products);
    }

    public void closeSale() {
        state.closeSale();
    }

    public void add(int code) {
        state.add(code);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public State getOpenState() {
        return openState;
    }

    public State getOnHoldState() {
        return onHoldState;
    }

    public State getClosedState() {
        return closedState;
    }

    public State getPaidState() {
        return paidState;
    }

    public double getFinalPrice(){
        return getTotalPrice()-calculateDiscount();
    }

    public double calculateDiscount() {
        double totalDiscount =0.0;
        LoadSaveProperties loadSaveProperties = new LoadSaveProperties();
        String discounts = LoadSaveProperties.getDiscountActive();
        discounts = discounts.replaceAll("\\[*\\]*","");
        String[] discountsArray = discounts.split(", ");
        DiscountFactory factory = DiscountFactory.getInstance();
        if (!discountsArray[0].equals("")) {
            for (String s : discountsArray) {
                //System.out.println(s);
                DiscountStrategy discount = factory.create(s);
                totalDiscount += discount.calculateDiscount(this);
            }
        }
        return totalDiscount;
    }
}
