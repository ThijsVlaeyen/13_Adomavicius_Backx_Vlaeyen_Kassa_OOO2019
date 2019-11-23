package model.IO.write;

import model.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

abstract public class WriteToFileTemplate {

   String path;

   public WriteToFileTemplate(String path){
      if (path.isEmpty()){
         throw new IllegalArgumentException("Path can't be empty");
      }
      this.path = path;
   }

   public void writeToTextFile(ArrayList<Product> products){

   }
}
