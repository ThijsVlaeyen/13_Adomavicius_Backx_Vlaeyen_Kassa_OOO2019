package model;

import database.ProductDB;

public class DiscountExpensive implements DiscountStrategy {

   @Override
   public int calculateDiscount(ShoppingCart shoppingCart) {
      return 0;
   }
}
