package model;

import controller.Observer;

import java.util.ArrayList;
import java.util.List;

public enum EventType {
    PRODUCTSCHANGED(new ArrayList<>()),
    LOG(new ArrayList<>());

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
