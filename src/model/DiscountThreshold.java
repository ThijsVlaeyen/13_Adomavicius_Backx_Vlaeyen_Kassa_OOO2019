package model;

import model.IO.LoadSaveProperties;

public class DiscountThreshold implements DiscountStrategy{

   private LoadSaveProperties properties = new LoadSaveProperties();

   @Override
   public double calculateDiscount(ShoppingCart shoppingCart) {
      double discount = 0;
      String percent = LoadSaveProperties.getDiscountThresholdPercent();
      percent = percent.substring(0, percent.length()-1);
      for (Product p: shoppingCart.getItemsList()) {
         discount += p.getPrice();
      }
      if (discount > Integer.parseInt(LoadSaveProperties.getDiscountThresholdAmount())){
         return discount /100 * Integer.parseInt(percent);
      } else {
         return 0;
      }
   }
}
