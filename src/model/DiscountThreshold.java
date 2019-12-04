package model;

import database.ProductDB;

public class DiscountThreshold implements DiscountStrategy{

   @Override
   public int calculateDiscount(ShoppingCart shoppingCart) {
      return 0;
   }
}
