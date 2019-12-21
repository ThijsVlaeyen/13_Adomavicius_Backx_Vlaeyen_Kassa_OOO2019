package model;

import database.ProductDB;

/**
 * @Author Vlaeyen Thijs
 * DiscountStrategy
 */

public interface DiscountStrategy {
   double calculateDiscount(ShoppingCart shoppingCart);
}
