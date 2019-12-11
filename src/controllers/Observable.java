package controllers;

public interface Observable {
    void addObserver(Observer o);
    void updateObservers(Object o);
    void removeObserver(Observer o);
}
