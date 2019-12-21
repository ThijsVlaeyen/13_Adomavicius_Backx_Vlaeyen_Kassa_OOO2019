package model.IO;

/**
 * @Author Vlaeyen Thijs
 * LoadSaveStrategyFactory
 */

public class LoadSaveStrategyFactory {
   private static LoadSaveStrategyFactory instance = null;
   public LoadSaveStrategy loadsave;

   private LoadSaveStrategyFactory(String type)
   {
      LoadSaveType lsType = LoadSaveType.valueOf(type);
      String className = lsType.getClassName();
      try{
         Class dbClassName = Class.forName(className);
         Object object = dbClassName.newInstance();
         loadsave = (LoadSaveStrategy)object;
      } catch (Exception e){
         e.printStackTrace();
      }
   }

   public static LoadSaveStrategyFactory getInstance(String type)
   {
      if (instance == null)
         instance = new LoadSaveStrategyFactory(type);
      return instance;
   }
}
