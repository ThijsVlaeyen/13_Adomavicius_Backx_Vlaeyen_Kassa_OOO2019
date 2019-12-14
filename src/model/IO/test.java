package model.IO;

import database.ProductDB;
import model.Product;
import model.ShoppingCart;

public class test {


public static void main(String[] args) {
   ReceiptFactory factory = new ReceiptFactory();
   ReceiptManager reee = factory.createObject("CONSOLE");
   ReceiptFooterDiscount message = new ReceiptFooterDiscount(reee);
   ProductDB db = new ProductDB();
   ShoppingCart cart = new ShoppingCart(db);
   Product a = new Product(2, "test", 10, 10, "gr1");
   Product b = new Product(2.5, "tst2", 10, 11, "gr2");
   cart.addProduct(a);
   cart.addProduct(a);
   cart.addProduct(a);
   cart.addProduct(a);
   cart.addProduct(a);
   cart.addProduct(b);
   cart.addProduct(b);
   System.out.println(message.writeData(cart));
}
}