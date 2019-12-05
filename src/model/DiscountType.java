package model;

public enum DiscountType {
   GROUP("group","model.DiscountGroup"),
   THRESHOLD("threshold","model.DiscountThreshold"),
   EXPENSIVE("expensive","model.DiscountExpensive");

   private final String name;
   private final String classname;

   DiscountType(String name, String classname){
      this.name = name;
      this.classname = classname;
   }

   public String getName(){return this.name;}
   public String getClassname(){return this.classname;}
}
