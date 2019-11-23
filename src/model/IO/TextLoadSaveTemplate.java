package model.IO;

import model.Product;

import java.util.ArrayList;

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
      return null;
   }

   @Override
   public void save(ArrayList<Product> products) {

   }

   public String getPath() {
      return path;
   }
}
