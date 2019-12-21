package controller;

import model.EventType;
/**
@Author Rafael backx
*/

public interface Observable {
    void addObserver(EventType e, Observer o);
    void updateObservers(EventType e, Object o);
    void removeObserver(EventType e, Observer o);
}
