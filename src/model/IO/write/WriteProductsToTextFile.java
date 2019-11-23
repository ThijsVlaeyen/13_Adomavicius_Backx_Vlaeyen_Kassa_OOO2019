package model.IO.write;

import model.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteProductsToTextFile extends WriteToFileTemplate {

   private String path;

   public WriteProductsToTextFile(String path){
      super(path);
   }

   @Override
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
