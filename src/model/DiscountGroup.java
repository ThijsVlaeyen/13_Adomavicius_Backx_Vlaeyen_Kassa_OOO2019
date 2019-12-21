package model;

import model.IO.LoadSaveProperties;

/**
 * @Author Vlaeyen Thijs
 * DiscountGroup
 */

public class DiscountGroup implements DiscountStrategy {

private LoadSaveProperties properties = new LoadSaveProperties();

   @Override
   public double calculateDiscount(ShoppingCart shoppingCart) {
      double totalGroupPrice = 0;
      String group = LoadSaveProperties.getDiscountGroupGroup();
      String percent = LoadSaveProperties.getDiscountGroupPercent();
      percent = percent.substring(0, percent.length()-1);
      if (group.equals("Group 1")){
         group = "gr1";
      }else if(group.equals("Group 2")){
         group = "gr2";
      }
      for (Product p:shoppingCart.getItemsList()){
         if(p.getGroup().equals(group)){
            totalGroupPrice += p.getPrice();
         }
      }
      return totalGroupPrice /100 * Integer.parseInt(percent);
   }
}
