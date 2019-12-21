package controller;

import database.ProductDB;
import model.EventType;
import model.IO.*;
import model.Log;
import model.ShoppingCart;
import view.panels.LogPane;

/**
 * @Author Vlaeyen Thijs & Adomavicius Tomas
 * Log controller
 */

public class LogController implements Observer {
private LogPane view;

public LogController(ProductDB db) {
   db.addObserver(EventType.LOG, this);
}

public void setView(LogPane view) {
   this.view = view;
}

@Override
public void update(Object object) {
   view.update(new Log(((ShoppingCart) object).getTotalPrice(), ((ShoppingCart) object).calculateDiscount(), ((ShoppingCart) object).getFinalPrice()));

   LoadSaveProperties properties = new LoadSaveProperties();
   ReceiptManager receipt = new ReceiptConsole();
   ReceiptFactoryDecorator factory = new ReceiptFactoryDecorator();
   String array = properties.getReceiptActive();
   array = array.replaceAll("\\[*\\]*", "");
   String[] activeReceipts = array.split(", ");

   for (String p : activeReceipts) {
      if (!p.trim().isEmpty()) {
         receipt = factory.createObject(p, receipt);
      }
   }

   System.out.println(receipt.writeData((ShoppingCart) object));
}
}
