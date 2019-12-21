package model.IO;

/**
 * @Author Vlaeyen Thijs
 * LoadSaveStrategyFactory
 */

public class LoadSaveStrategyFactory {
   private static LoadSaveStrategyFactory instance = null;
   private LoadSaveStrategy loadSave = null;

   private LoadSaveStrategyFactory()
   {

   }

   public static LoadSaveStrategyFactory getInstance()
   {
      if (instance == null)
         instance = new LoadSaveStrategyFactory();
      return instance;
   }

   public LoadSaveStrategy create(String type){
      LoadSaveType lsType = LoadSaveType.valueOf(type);
      String className = lsType.getClassName();
      try{
         Class dbClassName = Class.forName(className);
         Object object = dbClassName.newInstance();
         loadSave = (LoadSaveStrategy)object;
      } catch (Exception e){
         e.printStackTrace();
      }
      return loadSave;
   }
}
