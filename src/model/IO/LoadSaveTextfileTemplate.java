package model.IO;

import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @Author Vlaeyen Thijs
 * LoadSaveTextfileTemplate
 */

public abstract class LoadSaveTextfileTemplate implements LoadSaveStrategy {

   private String path;
   private File file;
   private ArrayList<Object> list;
   private Scanner fileScanner;
   private Map<String, String> objectValues;
   private Product objectToAdd;
   private FileWriter writer;

   public LoadSaveTextfileTemplate(String path){
      if (path.isEmpty()){
         throw new IllegalArgumentException("Path can't be empty");
      }
      this.path = path;
      this.list = new ArrayList<>();
   }

   @Override
   public final ArrayList<Object> load() {
      loadSetup();
      while(fileScanner.hasNextLine()) {
         Scanner rowScanner = new Scanner(fileScanner.nextLine());
         objectValues = readLine(rowScanner);
         objectToAdd = parseListToObject(objectValues);
         list.add(objectToAdd);
      }
      return list;
   }

   @Override
   public final void save(ArrayList<Object> list) {
      saveSetup();
      try{
         for (Object obj: list){
            writer.write(convertToString(obj));
         }
         writer.flush();
         writer.close();
      }catch(IOException e){
         e.printStackTrace();
      }
   }

   protected abstract Map<String, String> readLine(Scanner rowScanner);

   protected abstract Product parseListToObject(Map<String, String> objectValues);

   protected abstract String convertToString(Object obj);

   private void loadSetup() {
      file = new File(path);
      try {
         this.fileScanner = new Scanner(file);
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }
   }

   private void saveSetup() {
      try{
         file = new File(path);
         file.createNewFile();
         writer = new FileWriter(file);
      }catch(IOException e){
         e.printStackTrace();
      }
   }
}
