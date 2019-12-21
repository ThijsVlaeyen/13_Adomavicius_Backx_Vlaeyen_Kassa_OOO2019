package model.IO;

import model.ShoppingCart;

import java.time.LocalDate;

/**
 * @Author Vlaeyen Thijs
 * ReceiptFooterDiscount
 */

public class ReceiptFooterDiscount extends ReceiptDecorator{

   private ReceiptManager receipt;
   private LoadSaveProperties properties = new LoadSaveProperties();

   public ReceiptFooterDiscount(ReceiptManager receipt){
      this.receipt = receipt;
   }

   @Override
   public String writeData(ShoppingCart cart){
      String out = receipt.writeData(cart);
      out += "\nPrice (Discount excluded):\t" + cart.getTotalPrice() + "\nDiscount: \t\t"+ (cart.getTotalPrice()-cart.getFinalPrice());
      return out;
   }

}
