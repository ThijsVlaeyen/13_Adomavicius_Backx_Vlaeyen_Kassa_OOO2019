package model.IO;

import model.Product;

import java.util.ArrayList;
import java.util.List;

abstract public class TextLoadSaveTemplate implements LoadSaveStrategy {

   private String path;

   public TextLoadSaveTemplate(String path){
      if (path.isEmpty()){
         throw new IllegalArgumentException("Path can't be empty");
      }
      this.path = path;
   }

   @Override
   public ArrayList<Product> load() {
      return readFromFile();
   }

   protected abstract ArrayList<Product> readFromFile();

   @Override
   public void save(List<Product> products) {
      saveFile(products);
   }

   protected abstract void saveFile(List<Product> products);

   public String getPath() {
      return path;
   }
}
