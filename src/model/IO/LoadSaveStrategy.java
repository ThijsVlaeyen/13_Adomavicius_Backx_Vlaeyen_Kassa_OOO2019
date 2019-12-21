package model.IO;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Vlaeyen Thijs
 * LoadSaveStrategy
 */

public interface LoadSaveStrategy {
   ArrayList<Object> load();
   void save(ArrayList<Object> products);
}
