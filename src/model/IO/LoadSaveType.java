package model.IO;

public enum LoadSaveType {
   TEXTFILE( "textfile", "model.IO.ArticleLoadSaveTextfile"),
   EXCEL("excel", "model.IO.ArticleLoadSaveExcelFile");

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
