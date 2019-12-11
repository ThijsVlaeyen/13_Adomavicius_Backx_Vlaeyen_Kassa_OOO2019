package model;

import model.IO.LoadSaveProperties;
import model.states.*;

import java.util.*;

public class ShoppingCart {
    State openState;
    State onHoldState;
    State closedState;
    State paidState;
    State state;

    private Map<Product,Integer> cart;

    public ShoppingCart(){
        this.cart = new HashMap<>();
        openState = new OpenState(this);
        onHoldState = new OnHoldState(this);
        closedState = new ClosedState(this);
        paidState = new PaidState(this);
        state = openState;
    }

    public ShoppingCart(Map<Product, Integer> cart) {
        this();
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

    public boolean addOnHold() {
        return state.addOnHold();
    }

    public boolean payment() { return state.payment(); }

    public void takeFromHold() {
        state.takeFromHold();
    }

    public void delete() {
        state.remove();
    }

    public void closeSale() {
        state.closeSale();
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
        DiscountFactory factory = new DiscountFactory();
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
