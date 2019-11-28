package view.panels;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import controllers.SettingsController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.IO.LoadSaveProperties;
import model.IO.LoadSaveTextfile;
import model.IO.LoadSaveType;
import view.CashierMainPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SettingsPane extends GridPane {

   private ComboBox<String> loadstrategies = new ComboBox<>();
   private SettingsController controller;

   public SettingsPane(SettingsController controller){
      this.controller = controller;
      this.setPadding(new Insets(5, 5, 5, 5));
      this.setVgap(5);
      this.setHgap(5);

      this.add(new Label("Load strategy:"), 0,0,1,1);

      //DROPDOWNLIST

      loadstrategies.getItems().addAll(controller.getTypes());
      loadstrategies.setValue(controller.getType());
      this.add(loadstrategies,0,1,1,1);

      //BUTTON
      Button saveLoadStrategy = new Button("Save");
      //saveLoadStrategy.setOnMouseClicked(event -> properties.save(loadstrategies.getValue().toUpperCase()));
      saveLoadStrategy.setOnMouseClicked(event ->controller.save(loadstrategies.getValue().toUpperCase()));
      this.add(saveLoadStrategy, 0, 3,1,1);
   }
}
