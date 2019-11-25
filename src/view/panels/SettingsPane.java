package view.panels;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.IO.LoadSaveTextfile;
import view.CashierMainPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SettingsPane extends GridPane {

   private ComboBox<String> loadstrategies = new ComboBox<>();

   public SettingsPane(){
      this.setPadding(new Insets(5, 5, 5, 5));
      this.setVgap(5);
      this.setHgap(5);

      this.add(new Label("Load strategy:"), 0,0,1,1);

      //DROPDOWNLIST
      loadstrategies.getItems().addAll("Textfile", "Excel");
      loadstrategies.setValue("Textfile");
      this.add(loadstrategies,0,1,1,1);

      //BUTTON
      Button saveLoadStrategy = new Button("Save");
      saveLoadStrategy.setOnMouseClicked(event -> saveToProperties());
      this.add(saveLoadStrategy, 0, 3,1,1);
   }

   //THIS IS TEMPORARY WILL PROBABLY CHANGE IN NEAR FUTURE
   private void saveToProperties(){
      File file = new File("src/Files/properties.txt");
      try{
         file.createNewFile();
         FileWriter writer = new FileWriter(file);
         writer.write(loadstrategies.getValue().toUpperCase());
         writer.flush();
         writer.close();
      } catch (IOException e){
         e.printStackTrace();
      }
   }

}
