package model.states;

import model.Product;
import model.ShoppingCart;

import java.util.List;

public abstract class  State {
    public  void add(int code){
       throw new IllegalStateException("you cant use this method now");
    }
    public  void addOnHold(){
        throw new IllegalStateException("you cant use this method now");
    }
    public ShoppingCart takeFromHold(){
        throw new IllegalStateException("you cant use this method now");
    }
    public  void remove(List<Product> products){
        throw new IllegalStateException("you cant use this method now");
    }
    public  void closeSale(){
        throw new IllegalStateException("you cant use this method now");
    }
    public  void payment(){
        throw new IllegalStateException("you cant use this method now");
    }
}
