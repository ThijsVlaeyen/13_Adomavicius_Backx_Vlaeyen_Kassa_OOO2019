package model.IO;

public class ReceiptFactory {
   public ReceiptManager createObject(String type){
      ReceiptType receiptType = ReceiptType.valueOf(type);
      String className = receiptType.getClassName();
      ReceiptManager receipt = null;

      try{
         Class dbClassName = Class.forName(className);
         Object object = dbClassName.newInstance();
         receipt = (ReceiptManager) object;
      } catch (Exception e){
         e.printStackTrace();
      }
      return receipt;
   }
}
