package model;

public class DiscountFactory {

   public DiscountStrategy create(String type){
      DiscountType dType = DiscountType.valueOf(type);
      String classname = dType.getClassname();
      DiscountStrategy discount = null;

      try{
         Class dbClassName = Class.forName(classname);
         Object object = dbClassName.newInstance();
         discount = (DiscountStrategy)object;
      } catch (Exception e){
         e.printStackTrace();
      }
      return discount;
   }


}
