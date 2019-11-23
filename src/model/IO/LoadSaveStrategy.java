package model.IO;

import model.Product;

import java.util.ArrayList;

public interface LoadSaveStrategy {
   ArrayList<Product> load();
   void save(ArrayList<Product> products);
}
