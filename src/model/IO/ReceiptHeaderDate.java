package model.IO;

import model.ShoppingCart;

import java.time.LocalDate;

/**
 * @Author Vlaeyen Thijs
 * ReceiptHeaderDate
 */

public class ReceiptHeaderDate extends ReceiptDecorator{

   private ReceiptManager receipt;
   private LoadSaveProperties properties = new LoadSaveProperties();

   public ReceiptHeaderDate(ReceiptManager receipt){
      this.receipt = receipt;
   }

   @Override
   public String writeData(ShoppingCart cart){
      String out = LocalDate.now()+"\n";
      out += receipt.writeData(cart);
      return out;
   }

}
