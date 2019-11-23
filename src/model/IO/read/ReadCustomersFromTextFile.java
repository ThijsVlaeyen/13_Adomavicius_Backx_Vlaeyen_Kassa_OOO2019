package model.IO.read;

import model.Product;

import java.util.ArrayList;

public class ReadCustomersFromTextFile extends ReadFromFileTemplate{
//This has to be made yet just here for example use interface

   private String path;

   public ReadCustomersFromTextFile(String path){
      super(path);
   }

   @Override
   public void readFromFile() {

   }

   @Override
   public ArrayList<Product> getList() {
      return null;
   }
}
