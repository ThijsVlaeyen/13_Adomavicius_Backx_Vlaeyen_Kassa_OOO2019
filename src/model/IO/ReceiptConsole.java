package model.IO;

import model.Product;
import model.ShoppingCart;

import java.util.Map;

public class ReceiptConsole implements ReceiptManager{

   @Override
   public String writeData(ShoppingCart cart) {
      String out = "Description\t\tAmount\tPrice\n****************************\n";
      for (Map.Entry<Product,Integer> entry : cart.getItems().entrySet()){
         out += entry.getKey().getName() + "\t\t\t" + entry.getValue() + "\t\t" + entry.getKey().getPrice()*entry.getValue() + "\n";
      }
      out += "****************************\nPayed (discount included): " + cart.getFinalPrice();
      return out;
   }
}
