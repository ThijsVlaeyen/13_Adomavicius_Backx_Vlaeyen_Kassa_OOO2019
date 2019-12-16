package model.IO;

import model.Product;

import java.util.*;

public class ArticleLoadSaveTextfile extends LoadSaveTextfileTemplate {

   public ArticleLoadSaveTextfile(){
      this("src/Files/article.txt");
   }

   public ArticleLoadSaveTextfile(String path){
      super(path);
   }

   @Override
   protected Map<String, String> readLine(Scanner rowScanner) {
      rowScanner.useDelimiter(",");
      Map<String, String> map = new HashMap<String, String>();
      map.put("id", rowScanner.next());
      map.put("name", rowScanner.next());
      map.put("group", rowScanner.next());
      map.put("price", rowScanner.next());
      map.put("stock", rowScanner.next());
      return map;
   }

   @Override
   protected Product parseListToObject(Map<String, String> objectValues) {
      Product productToReturn = null;
      try {
         int id = Integer.parseInt(objectValues.get("id"));
         double price = Double.parseDouble(objectValues.get("price"));
         int stock = Integer.parseInt(objectValues.get("stock"));
         productToReturn = new Product(id, objectValues.get("name"), objectValues.get("group"), price, stock);
      } catch (NumberFormatException e){
         e.printStackTrace();
      }
      return productToReturn;
   }

   @Override
   protected String convertToString(Object obj) {
      Product product = (Product) obj;
      return product.getId()+","+product.getName()+","+product.getGroup()+","+product.getPrice()+","+product.getStock()+"\n";
   }
}
