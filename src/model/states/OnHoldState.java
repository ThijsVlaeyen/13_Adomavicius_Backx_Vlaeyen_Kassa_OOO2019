package model.states;

import model.ShoppingCart;

public class OnHoldState implements State {
    ShoppingCart shoppingCart;

    public OnHoldState(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public boolean addOnHold() {
        return false;
    }

    @Override
    public boolean takeFromHold() {
        return false;
    }

    @Override
    public boolean remove() {
        return false;
    }

    @Override
    public boolean closeSale() {
        return false;
    }

    @Override
    public boolean payment() {
        return false;
    }
}
