package controllers;

import database.ProductDB;
import model.DiscountFactory;
import model.DiscountStrategy;
import model.IO.LoadSaveProperties;
import model.Product;
import model.ShoppingCart;
import view.panels.CashierSalesPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CashierController implements  ClientViewObservable {
    private CashierSalesPane view;
    private ProductDB db;
    private ShoppingCart model;
    private ClientViewObserver observer;
    private ShoppingCart holdingShoppingCart;

    public CashierController(ProductDB db) {
        this.db = db;
        model = new ShoppingCart();
        holdingShoppingCart = new ShoppingCart();
    }

    public void setView(CashierSalesPane view) {
        this.view = view;
    }

    public void addArticle(int code) {
        if (db.isProductExist(code)){
            model.addProduct(db.getProduct(code));
            view.setNotExistingCode(false);
            view.updateTable(model.getItemsList());
            view.updateTotalAmount(model.getTotalPrice());
        }else{
            view.setNotExistingCode(true);
        }
        calculateDiscount();
        updateObservers();
    }

    public void removeArticle(Product p){
        remove(p);
        view.updateTable(model.getItemsList());
        view.updateTotalAmount(model.getTotalPrice());
        updateObservers();
    }

    public void remove(Product p){
        if (p != null){
            model.remove(p);
        }
    }

    public void removeArticles(List<Product> products){
        for (Product p:products){
            remove(p);
        }
        view.updateTable(model.getItemsList());
        view.updateTotalAmount(model.getTotalPrice());
        updateObservers();
    }

    public void addOnHold() {
        if(holdingShoppingCart.getItemsList().isEmpty()) {
            holdingShoppingCart = (ShoppingCart) model.clone();
            model.clear();
            view.updateTable(model.getItemsList());
            view.updateTotalAmount(model.getTotalPrice());
        }
        else {
            view.showAlert("there are already items on hold");
        }
        updateObservers();
    }

    public void takeFromHold() {
        if(holdingShoppingCart.getItemsList().isEmpty()) {
            view.showAlert("there are no items to add");
        }
        else {
            model = (ShoppingCart) holdingShoppingCart.clone();
            holdingShoppingCart.clear();
            view.updateTable(model.getItemsList());
            view.updateTotalAmount(model.getTotalPrice());
        }
        updateObservers();
    }

    public void payment() {
        db.updateStocks(model.getItemsList());
        model.clear();
        view.updateTable(model.getItemsList());
        view.updateTotalAmount(model.getTotalPrice());
        updateObservers(getLogMessage());
    }

    public String getLogMessage() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date) + " " + model.getTotalPrice() + " " + calculateDiscount() + " " + getFinalPrice();
    }

    @Override
    public void addObserver(ClientViewObserver o) {
        this.observer = o;
    }

    @Override
    public void updateObservers(){
        this.observer.update(model.getItemsList());
    }

    public void updateObservers(String log) {
        //todo this.observer.update(log, model.getItemsList());
    }

    @Override
    public void removeObserver(ClientViewObserver o) {

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
                totalDiscount += discount.calculateDiscount(model);
            }
        }
        view.updateDiscount(totalDiscount);
        return totalDiscount;
    }

    public double getFinalPrice(){
        return model.getTotalPrice()-calculateDiscount();
    }

    public void close() {
        view.updateTotalAmount(getFinalPrice());
    }
}