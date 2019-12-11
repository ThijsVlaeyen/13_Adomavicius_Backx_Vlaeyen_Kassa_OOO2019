package model.states;

import model.ShoppingCart;

public class OpenState implements State {
    ShoppingCart shoppingCart;

    public OpenState(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public boolean addOnHold() {
        shoppingCart.setState(shoppingCart.getOnHoldState());
        return true;
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
