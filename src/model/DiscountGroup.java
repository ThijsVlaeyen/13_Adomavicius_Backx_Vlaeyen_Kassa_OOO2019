package model;

import database.ProductDB;

public class DiscountGroup implements DiscountStrategy {

   @Override
   public int calculateDiscount(ShoppingCart shoppingCart) {
      return 0;
   }
}
