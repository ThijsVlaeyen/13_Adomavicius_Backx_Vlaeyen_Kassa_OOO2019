package model.IO;

import model.ShoppingCart;

/**
 * @Author Vlaeyen Thijs
 * ReceiptFooterVAT
 */

public class ReceiptFooterVAT extends ReceiptDecorator {

private ReceiptManager receipt;
private LoadSaveProperties properties = new LoadSaveProperties();

public ReceiptFooterVAT(ReceiptManager receipt) {
   this.receipt = receipt;
}

@Override
public String writeData(ShoppingCart cart) {
   String out = receipt.writeData(cart);
   out += "\nPrice (VAT excluded):\t" + Math.round(cart.getFinalPrice()/1.06*100.00)/100.00 + "\nVAT:\t\t" + Math.round((cart.getFinalPrice()-cart.getFinalPrice()/1.06)*100.00)/100.00;
   return out;
}

}
