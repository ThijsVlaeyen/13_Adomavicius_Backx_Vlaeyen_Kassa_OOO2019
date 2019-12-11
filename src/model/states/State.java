package model.states;

import model.Product;

import java.util.List;

public interface State {
    void add(int code);
    void addOnHold();
    void takeFromHold();
    void remove(List<Product> products);
    void closeSale();
    void payment();
}
