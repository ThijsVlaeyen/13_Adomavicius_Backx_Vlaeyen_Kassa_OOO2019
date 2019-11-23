package model.IO;

import model.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WriteToTextFile {

   private String path;

   public WriteToTextFile(String path){
      if (path.isEmpty()){
         throw new IllegalArgumentException("Path can't be empty");
      }
      this.path = path;
   }

   public void writeToTextFile(ArrayList<Product> products){
      File productfile = new File(this.path);
      try{
         productfile.createNewFile();
         FileWriter writer = new FileWriter(productfile);
         for (Product p: products){
            writer.write(p.getId()+","+p.getName()+","+p.getGroup()+","+p.getPrice()+","+p.getStock()+"\n");
         }
         writer.flush();
         writer.close();
      }catch(IOException e){
         e.printStackTrace();
      }
   }

}
