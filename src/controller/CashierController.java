package controller;

import database.ProductDB;
import model.*;
import view.panels.CashierSalesPane;
/**
@Author Rafael backx, Thomas Adomavicius
*/

import java.util.List;

public class CashierController implements Observer {
    private CashierSalesPane view;
    private ProductDB db;
    private ShoppingCart cart;

    public CashierController(ProductDB db) {
        this.db = db;
        db.addObserver(EventType.PRODUCTSCHANGED, this);
        cart = new ShoppingCart(db);
    }

    public ShoppingCart getCart(){
        return this.cart;
    }

    public void setView(CashierSalesPane view) {
        this.view = view;
    }

    public void addArticle(int code) {
        cart.add(code);
    }

    public void removeArticles(List<Product> products){
        cart.delete(products);
    }

    public void addOnHold() {
        cart.addOnHold();
    }

    public void takeFromHold() {
        if (cart.takeFromHold() != null){
            cart = cart.takeFromHold();
        }
    }

    public void payment() {
        cart.payment();
    }

    public void close() {
        cart.closeSale();
    }

    @Override
    public void update(Object object) {
        ShoppingCart cart = (ShoppingCart) object;
        view.updateTable(cart.getItemsList());
        view.updateTotalAmount(cart.getTotalPrice());
        view.updateDiscount(cart.calculateDiscount());
        view.updatePaymentButton(cart.getState()!=cart.getClosedState());
    }
}