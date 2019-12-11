package model.states;

import model.ShoppingCart;

public class ClosedState implements State {
    ShoppingCart shoppingCart;

    public ClosedState(ShoppingCart shoppingCart) {
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
        shoppingCart.setState(shoppingCart.getPaidState());
        return true;
    }
}
