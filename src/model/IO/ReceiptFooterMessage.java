package model.IO;

import model.ShoppingCart;

/**
 * @Author Vlaeyen Thijs
 * ReceiptFooterMessage
 */

public class ReceiptFooterMessage extends ReceiptDecorator {

private ReceiptManager receipt;
private LoadSaveProperties properties = new LoadSaveProperties();

public ReceiptFooterMessage(ReceiptManager receipt){
   this.receipt = receipt;
}

@Override
public String writeData(ShoppingCart cart){
   String out = receipt.writeData(cart);
   out += "\n"+LoadSaveProperties.getReceiptFooterMessage();
   return out;
}

}
