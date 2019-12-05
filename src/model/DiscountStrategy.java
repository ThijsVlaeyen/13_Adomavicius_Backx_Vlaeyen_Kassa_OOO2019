package model;

import database.ProductDB;

public interface DiscountStrategy {
   double calculateDiscount(ShoppingCart shoppingCart);
}
