package model;

import model.IO.LoadSaveProperties;
public class DiscountExpensive implements DiscountStrategy {

   private LoadSaveProperties properties = new LoadSaveProperties();

   @Override
   public double calculateDiscount(ShoppingCart shoppingCart) {
      double highestprice = 0;
      String percent = LoadSaveProperties.getDiscountExpensivePercent();
      percent = percent.substring(0, percent.length()-1);
      for (Product p: shoppingCart.getItemsList()) {
         if (p.getPrice() > highestprice){
            highestprice = p.getPrice();
         }
      }
      return highestprice /100 * Integer.parseInt(percent);
   }
}
