package database;

import controllers.Observer;

public interface Observable {
    void updateObservers();
    void addObserver(Observer o);
}
