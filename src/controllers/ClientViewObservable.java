package controllers;

import model.Product;

import java.util.ArrayList;

public interface ClientViewObservable {
    void addObserver(ClientViewObserver o);
    void updateObservers();
    void removeObserver(ClientViewObserver o);
}
