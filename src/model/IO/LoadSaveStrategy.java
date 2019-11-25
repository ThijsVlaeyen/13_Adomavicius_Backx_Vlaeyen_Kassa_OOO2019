package model.IO;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public interface LoadSaveStrategy {
   ArrayList<Product> load();
   void save(List<Product> products);
   void setPath(String path);
}
