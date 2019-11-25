package model.IO;

import model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class LoadSaveProperties {

   public LoadSaveProperties(){
   }

   public String load() {
      String result = "";
      InputStream inputStream;

      try{
         Properties prop = new Properties();
         String propFileName = "config.properties";

         inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

         if (inputStream != null){
            prop.load(inputStream);
         } else {
            throw new FileNotFoundException("property file" + propFileName + " not found");
         }

         result = prop.getProperty("Strategy");

         inputStream.close();

      } catch (Exception e){
         System.out.println(e);
      }
      return result;
   }

   public void save(String value) {
      try {
         Properties prop = new Properties();
         InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties");
         prop.load(in);
         prop.setProperty("Strategy", value);
         prop.store(new FileOutputStream("resources/config.properties"), null);
         in.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
