package model.IO;

import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadFromTextFile {

   private String path;

      public ReadFromTextFile(String path){

         if (path.isEmpty()){
            throw new IllegalArgumentException("Path can't be empty");
         }
         this.path = path;
      }

      public HashMap<Integer, Product> ReadFromFile(){
         File productfile = new File(this.path);
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

               //Product creation here need to type cast id group price and stock
               //Also add to hashmap key value is id of product

            }
         }catch(FileNotFoundException e){
            System.out.println(e);
         }
         return list;
      }

}
