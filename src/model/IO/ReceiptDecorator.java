package model.IO;

import model.ShoppingCart;

public abstract class ReceiptDecorator implements ReceiptManager {

   private ReceiptManager wrappee;

   @Override
   public String writeData(ShoppingCart cart) {
      return null;
   }
}
