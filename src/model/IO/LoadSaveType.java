package model.IO;

public enum LoadSaveType {
   TEXTFILE( "textfile", "model.IO.LoadSaveTextfile"),
   EXCEL("excel", "model.IO.LoadSaveExcelFile");

   private final String name;
   private final String classname;

   LoadSaveType(String name, String classname){
      this.name = name;
      this.classname = classname;
   }

   public String getClassName() {
      return classname;
   }

   public String getName() {
      return name;
   }

}
