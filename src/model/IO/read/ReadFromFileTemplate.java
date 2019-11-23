package model.IO.read;

import model.Product;

import java.util.ArrayList;

abstract public class ReadFromFileTemplate {

   private String path;

   public ReadFromFileTemplate(String path){
      if (path.isEmpty()){
         throw new IllegalArgumentException("Path can't be empty");
      }
      this.path = path;
   }

   public void readFromFile() {

   }

   public ArrayList<Product> getList(){
      return null;
   }

}
