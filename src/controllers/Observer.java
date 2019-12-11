package controllers;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public interface Observer {
    void update(Object object);
}
