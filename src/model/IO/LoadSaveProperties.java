package model.IO;

import java.io.*;
import java.util.Properties;

public class LoadSaveProperties {

   static InputStream inputStream;
   static Properties properties;
   static OutputStream outputStream;

   public LoadSaveProperties(){
      try{
         properties = new Properties();
         inputStream = new FileInputStream("src/Files/config.properties");

         if (inputStream != null){
            properties.load(inputStream);
         } else {
            throw new FileNotFoundException("property file not found");
         }
         inputStream.close();
      } catch (Exception e){
         System.out.println(e);
      }
   }

   public void save() {
      try{
         outputStream = new FileOutputStream("src/Files/config.properties");
         properties.store(outputStream, null);
      } catch (Exception e){
         e.printStackTrace();
      }
   }

   //LOAD SAVE STRATEGY
   public static String getLoadSave(){return properties.getProperty("Strategy");}
   public static void setLoadSave(String value){properties.setProperty("Strategy", value);}

   //ACTIVE DISCOUNTS
   public static void setDiscountActive(String value){properties.setProperty("DiscountActive", value);}
   public static String getDiscountActive(){return properties.getProperty("DiscountActive");}

   //DISCOUNT GROUP
   public static void setDiscountGroupGroup(String value){properties.setProperty("DiscountGroupGroup", value);}
   public static void setDiscountGroupPercent(String value){properties.setProperty("DiscountGroupPercent", value);}
   public static String getDiscountGroupGroup(){return properties.getProperty("DiscountGroupGroup", "Group 1");}
   public static String getDiscountGroupPercent(){return properties.getProperty("DiscountGroupPercent", "1%");}

   //DISCOUNT THRESHOLD
   public static void setDiscountThresholdAmount(String value){properties.setProperty("ThresholdAmount", value);}
   public static void setDiscountThresholdPercent(String value){properties.setProperty("ThresholdPercent", value);}
   public static String getDiscountThresholdAmount(){return properties.getProperty("ThresholdAmount", "0");}
   public static String getDiscountThresholdPercent(){return properties.getProperty("ThresholdPercent", "1%");}

   //DISCOUNT EXPENSIVE
   public static void setDiscountExpensivePercent(String value){properties.setProperty("ExpensivePercent", value);}
   public static String getDiscountExpensivePercent(){return properties.getProperty("ExpensivePercent", "1%");}


}
