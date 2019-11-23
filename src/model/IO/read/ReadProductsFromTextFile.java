package model.IO.read;

import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadProductsFromTextFile extends ReadFromFileTemplate {

   private String path;
   private HashMap<Integer, Product> list;

      public ReadProductsFromTextFile(String path){
         super(path);
      }

      @Override
      public void readFromFile(){
         File productfile = new File(this.path);
         list = new HashMap<>();
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
      }

      @Override
      public ArrayList<Product> getList(){
         ArrayList<Product> products = new ArrayList<>(list.values());
         return products;
      }

}
