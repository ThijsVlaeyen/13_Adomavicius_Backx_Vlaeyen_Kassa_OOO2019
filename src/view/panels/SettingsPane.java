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
   private final ComboBox groupDiscounts = new ComboBox<String>();

   private final CheckBox thresholdCheckbox = new CheckBox();
   private final ComboBox thresholdDiscounts = new ComboBox<String>();
   private TextField thresholdAmount = new TextField();

   private final CheckBox expensiveCheckbox = new CheckBox();
   private final ComboBox expensiveDiscounts = new ComboBox<String>();

   private Button saveDiscount;

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

      //FOR GROUP DISCOUNT
      this.add(new Label("Discounts:"), 0,2,1,1);
      groupCheckbox.setSelected(Boolean.parseBoolean(controller.getDiscountGroupActive()));
      if(groupCheckbox.isSelected()){
         groups.setDisable(false);
         groupDiscounts.setDisable(false);
      }else{
         groups.setDisable(true);
         groupDiscounts.setDisable(true);
      }
      this.add(groupCheckbox, 0,3,1,1);
      this.add(new Label("Group discount:"),1,3,1,1);
      groupDiscounts.getItems().addAll("1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%", "15%", "16%", "17%", "18%", "19%", "20%","21%", "22%", "23%", "24%", "25%", "26%","27%", "28%", "29%", "30%");
      groups.getItems().addAll("Group 1", "Group 2");
      this.add(groups, 2,3,1,1);
      this.add(groupDiscounts, 3,3,1,1);
      groupCheckbox.setOnAction(event -> {
         if(groupCheckbox.isSelected()){
            groups.setDisable(false);
            groupDiscounts.setDisable(false);
         }else{
            groups.setDisable(true);
            groupDiscounts.setDisable(true);
         }});

      //FOR THRESHOLD DISCOUNT
      thresholdCheckbox.setSelected(Boolean.parseBoolean(controller.getDiscountThresholdActive()));
      if (thresholdCheckbox.isSelected()){
         thresholdDiscounts.setDisable(false);
         thresholdAmount.setDisable(false);
      } else {
         thresholdDiscounts.setDisable(true);
         thresholdAmount.setDisable(true);
      }
      this.add(thresholdCheckbox,0,4,1,1);
      this.add(new Label("Threshold discount:"), 1,4,1,1);
      this.add(thresholdAmount,2,4,1,1);
      thresholdDiscounts.getItems().addAll("1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%", "15%", "16%", "17%", "18%", "19%", "20%","21%", "22%", "23%", "24%", "25%", "26%","27%", "28%", "29%", "30%");
      this.add(thresholdDiscounts,3,4,1,1);
      thresholdCheckbox.setOnAction(event -> {
         if (thresholdCheckbox.isSelected()){
            thresholdDiscounts.setDisable(false);
            thresholdAmount.setDisable(false);
         } else {
            thresholdDiscounts.setDisable(true);
            thresholdAmount.setDisable(true);
         }});

      //FOR EXPENSIVE DISCOUNT
      expensiveCheckbox.setSelected(Boolean.parseBoolean(controller.getDiscountThresholdActive()));
      if (expensiveCheckbox.isSelected()){
         expensiveDiscounts.setDisable(false);
      } else {
         expensiveDiscounts.setDisable(true);
      }
      this.add(new Label("Expensive:"),1,5,1,1);
      this.add(expensiveCheckbox,0,5,1,1);
      expensiveDiscounts.getItems().addAll("1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%", "15%", "16%", "17%", "18%", "19%", "20%","21%", "22%", "23%", "24%", "25%", "26%","27%", "28%", "29%", "30%");
      this.add(expensiveDiscounts,3,5,1,1);
      expensiveCheckbox.setOnAction(event -> {
         if (expensiveCheckbox.isSelected()){
            expensiveDiscounts.setDisable(false);
         } else {
            expensiveDiscounts.setDisable(true);
         }
      });

      //DISCOUNT BUTTONS
      saveDiscount = new Button("Save Discounts");
      this.add(saveDiscount,0,6,1,1);
      saveDiscount.setOnAction(event -> {
         controller.setDiscountGroupActive(Boolean.toString(groupCheckbox.isSelected()));
         if (groupCheckbox.isSelected()){
            controller.setDiscountGroupGroup(groups.getValue().toString().trim());
            controller.setDiscountGroupPercent(groupDiscounts.getValue().toString().trim());
         } else {
            controller.setDiscountGroupGroup("null");
            controller.setDiscountGroupPercent("null");
         }

         controller.setDiscountThresholdActive(Boolean.toString(thresholdCheckbox.isSelected()));
         if (thresholdCheckbox.isSelected()){
            controller.setDiscountThresholdAmount(thresholdAmount.getText());
            controller.setDiscountThresholdPercent(thresholdDiscounts.getValue().toString().trim());
         } else {
            controller.setDiscountThresholdAmount("null");
            controller.setDiscountThresholdPercent("null");
         }
         controller.setDiscountExpensiveActive(Boolean.toString(expensiveCheckbox.isSelected()));
         if (expensiveCheckbox.isSelected()){
            controller.setDiscountExpensivePercent(expensiveDiscounts.getValue().toString().trim());
         } else {
            controller.setDiscountExpensivePercent("null");
         }
         controller.save();
      });


   }

}
