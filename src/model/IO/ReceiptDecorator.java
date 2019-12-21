package model.IO;

import model.ShoppingCart;

/**
 * @Author Vlaeyen Thijs
 * ReceiptDecorator
 */

public abstract class ReceiptDecorator implements ReceiptManager {
   @Override
   public String writeData(ShoppingCart cart) {
      return null;
   }
}
