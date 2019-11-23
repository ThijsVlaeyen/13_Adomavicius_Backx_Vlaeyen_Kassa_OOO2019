package model.IO;

import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProductsFromFile extends TextLoadSaveTemplate {
   public ProductsFromFile(String path) {
      super(path);
   }

   @Override
   public ArrayList<Product> load() {
      File productfile = new File(super.getPath());
      HashMap<Integer, Product> list = new HashMap<>();
      try{
         Scanner fileScanner = new Scanner(productfile);
         while(fileScanner.hasNextLine()){
            Scanner rowScanner = new Scanner(fileScanner.nextLine());
            rowScanner.useDelimiter(",");
            String id = rowScanner.next();
            String name = rowScanner.next();
            String group = rowScanner.next();
            String price = rowScanner.next();
            String stock = rowScanner.next();

            try {
               int productId = Integer.parseInt(id);
               double productPrice = Double.parseDouble(price);
               int productStock = Integer.parseInt(stock);
               list.put(productId,new Product(productPrice,name,productStock,productId,group));
            }catch (NumberFormatException e){
               e.printStackTrace();
            }
         }
      }catch(FileNotFoundException e){
         System.out.println(e);
      }
      return new ArrayList<>(list.values());
   }

   @Override
   public void save(ArrayList<Product> products) {
      File productfile = new File(super.getPath());
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
