package model.IO;

public class LoadSaveStrategyFactory {
   public LoadSaveStrategy createObject(String type){
      LoadSaveType lsType = LoadSaveType.valueOf(type);
      String className = lsType.getClassName();
      LoadSaveStrategy loadsave = null;

      try{
         Class dbClassName = Class.forName(className);
         Object object = dbClassName.newInstance();
         loadsave = (LoadSaveStrategy)object;
      } catch (Exception e){
         e.printStackTrace();
      }
      return loadsave;
   }
}
