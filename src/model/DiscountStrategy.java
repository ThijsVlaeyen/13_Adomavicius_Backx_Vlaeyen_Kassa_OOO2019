package model;

import database.ProductDB;

public interface DiscountStrategy {
   int calculateDiscount(ShoppingCart shoppingCart);
}
