package view.panels;

import controllers.SettingsController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SettingsPane extends GridPane {

   private SettingsController controller;

   private ComboBox<String> loadstrategies = new ComboBox<>();
   private Button saveLoadStrategy;

   private final CheckBox groupCheckbox = new CheckBox();
   private final ComboBox groups = new ComboBox<String>();
   private TextField groupDiscount = new TextField();

   private final CheckBox thresholdCheckbox = new CheckBox();
   private TextField thresholdDiscount = new TextField();

   public SettingsPane(SettingsController controller){
      this.controller = controller;
      this.setPadding(new Insets(5, 5, 5, 5));
      this.setVgap(5);
      this.setHgap(5);

      //THIS IS FOR LOAD SAVE STRATEGY
      //DROPDOWNLIST FOR LOAD OPTIONS
      this.add(new Label("Load strategy:"), 0,0,1,1);
      loadstrategies.getItems().addAll(controller.getTypes());
      loadstrategies.setValue(controller.getType());
      this.add(loadstrategies,1,0,1,1);

      //BUTTON
      saveLoadStrategy = new Button("Save load option");
      this.add(saveLoadStrategy, 0, 1,1,1);
      saveLoadStrategy.setOnMouseClicked(event ->{
         controller.setLoadSave(loadstrategies.getValue().toUpperCase());
         controller.save();
      });

      //THIS IS FOR DISCOUNT
      this.add(new Label("Discounts:"), 0,2,1,1);
      //FOR GROUP DISCOUNT
      groupCheckbox.setSelected(Boolean.parseBoolean(controller.getDiscountGroupActive()));
      this.add(groupCheckbox, 0,3,1,1);
      this.add(new Label("Group discount:"),1,3,1,1);
      groups.getItems().addAll("Group 1", "Group 2");
      this.add(groups, 2,3,1,1);
      this.add(groupDiscount, 3,3,1,1);
      setGroupButtons();
      groupCheckbox.setOnAction(event -> setGroupButtons());

      //FOR THRESHOLD DISCOUNT
      thresholdCheckbox.setSelected(Boolean.parseBoolean(controller.getDiscountThresholdActive()));
      this.add(thresholdCheckbox,0,4,1,1);
      this.add(new Label("Threshold discount:"), 1,4,1,1);
      this.add(thresholdDiscount,2,4,1,1);
      setThresholdButtons();
      groupCheckbox.setOnAction(event -> setThresholdButtons());

   }

   private void setGroupButtons(){
      if(groupCheckbox.isSelected()){
         groups.setDisable(false);
         groupDiscount.setDisable(false);
      }else{
         groups.setDisable(true);
         groupDiscount.setDisable(true);
      }
   }

   private void setThresholdButtons(){
      if (thresholdCheckbox.isSelected()){
         thresholdDiscount.setDisable(false);
      } else {
         thresholdDiscount.setDisable(true);
      }
   }
}
