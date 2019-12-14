package model.IO;

public enum ReceiptType {
   CONSOLE("console", "model.IO.ReceiptConsole");

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
