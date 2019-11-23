package model.IO.write;

import model.Product;

import java.util.ArrayList;

public class WriteCustomersToTextFile extends WriteToFileTemplate {
   //Just here for example interface use but we will need this in the future probably

   private String path;

   public WriteCustomersToTextFile(String path){
      super(path);
   }

   @Override
   public void writeToTextFile(ArrayList<Product> products) {

   }
}
