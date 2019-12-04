package model.IO;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.*;
import java.util.Properties;

public class LoadSaveProperties {

   static InputStream inputStream;
   static Properties properties;
   static OutputStream outputStream;

   public LoadSaveProperties(){
   }

   public static void load(){
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

   //DISCOUNT GROUP
   public static void setDiscountGroupActive(String value){properties.setProperty("DiscountGroup", value);}
   public static void setDiscountGroupGroup(String value){properties.setProperty("DiscountGroupGroup", value);}
   public static void setDiscountGroupPercent(String value){properties.setProperty("DiscountGroupPercent", value);}
   public static String getDiscountGroupActive(){return properties.getProperty("DiscountGroup", "false");}

   //DISCOUNT THRESHOLD
   public static void setDiscountThresholdActive(String value){properties.setProperty("ThresholdActive", value);}
   public static void setDiscountThresholdAmount(String value){properties.setProperty("ThresholdAmount", value);}
   public static void setDiscountThresholdPercent(String value){properties.setProperty("ThresholdPercent", value);}
   public static String getDiscountThresholdActive(){return properties.getProperty("ThresholdActive", "false");}

   //DISCOUNT EXPENSIVE
   public static void setDiscountExpensiveActive(String value){properties.setProperty("ExpensiveActive", value);}
   public static void setDiscountExpensivePercent(String value){properties.setProperty("ExpensivePercent", value);}


}
