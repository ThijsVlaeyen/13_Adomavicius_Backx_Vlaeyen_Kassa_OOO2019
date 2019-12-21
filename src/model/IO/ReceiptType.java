package model.IO;

/**
 * @Author Vlaeyen Thijs
 * ReceiptType
 */

public enum ReceiptType {
   HEADERMESSAGE("headermessage", "model.IO.ReceiptHeaderMessage"),
   HEADERDATE("headerdate", "model.IO.ReceiptHeaderDate"),
   FOOTERMESSAGE("footermessage", "model.IO.ReceiptFooterMessage"),
   FOOTERVAT("footervat", "model.IO.ReceiptFooterVAT"),
   FOOTERDISCOUNT("footerdiscount", "model.IO.ReceiptFooterDiscount");

   private final String name;
   private final String classname;

   ReceiptType(String name, String classname){
      this.name = name;
      this.classname = classname;
   }

   public String getClassName() {
      return classname;
   }

   public String getName() {
      return name;
   }
}
