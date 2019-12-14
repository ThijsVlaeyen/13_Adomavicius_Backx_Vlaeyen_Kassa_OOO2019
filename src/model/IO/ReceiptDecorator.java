package model.IO;

import model.ShoppingCart;

public abstract class ReceiptDecorator implements ReceiptManager {
   @Override
   public String writeData(ShoppingCart cart) {
      return null;
   }
}
