package model.IO;

import java.lang.reflect.Constructor;

public class ReceiptFactoryDecorator {
   public ReceiptDecorator createObject(String type, ReceiptManager receiptManager){
      ReceiptType receiptType = ReceiptType.valueOf(type);
      String className = receiptType.getClassName();
      ReceiptDecorator receipt = null;

      try{
         Class dbClassName = Class.forName(className);
         Constructor cons = dbClassName.getConstructor(ReceiptManager.class);
         Object object = cons.newInstance(receiptManager);
         receipt = (ReceiptDecorator) object;
      } catch (Exception e){
         e.printStackTrace();
      }
      return receipt;
   }
}
