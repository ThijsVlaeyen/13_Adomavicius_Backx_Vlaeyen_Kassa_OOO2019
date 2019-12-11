package model;

import controllers.Observer;

import java.util.ArrayList;
import java.util.List;

public enum EventType {
    ADDPRODUCT(new ArrayList<>()),
    ADDONHOLD(new ArrayList<>()),
    TAKEFROMHOLD(new ArrayList<>()),
    DELETE(new ArrayList<>()),
    CLOSESALE(new ArrayList<>()),
    PAYMENT(new ArrayList<>()),
    TODO(new ArrayList<>());

    private List<Observer> observers;

    private EventType(List<Observer> observers){
        this.observers = observers;
    }

    public void addObserver(Observer o){
        this.observers.add(o);
    }

    public List<Observer> getObservers(){
        return this.observers;
    }
}
