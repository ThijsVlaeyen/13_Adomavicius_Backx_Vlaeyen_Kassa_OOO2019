package model;

/**
 * @Author Vlaeyen Thijs
 * DiscountFactory
 */

public class DiscountFactory {
   private static DiscountFactory instance = null;
   public DiscountStrategy discount;

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

   private DiscountFactory()
   {

   }

   public static DiscountFactory getInstance()
   {
      if (instance == null)
         instance = new DiscountFactory();
      return instance;
   }
}
