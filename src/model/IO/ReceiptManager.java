package model.IO;
import model.ShoppingCart;

/**
 * @Author Vlaeyen Thijs
 * ReceiptManager
 */

public interface ReceiptManager {
   String writeData(ShoppingCart cart);
}
