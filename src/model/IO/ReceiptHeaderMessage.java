package model.IO;

import model.ShoppingCart;

/**
 * @Author Vlaeyen Thijs
 * ReceiptHeaderMessage
 */

public class ReceiptHeaderMessage extends ReceiptDecorator {

   private ReceiptManager receipt;
   private LoadSaveProperties properties = new LoadSaveProperties();

   public ReceiptHeaderMessage(ReceiptManager receipt){
      this.receipt = receipt;
   }

   @Override
   public String writeData(ShoppingCart cart){
      String out = LoadSaveProperties.getReceiptHeaderMessage()+"\n";
      out += receipt.writeData(cart);
      return out;
   }

}
