package view.panels;

import controller.SettingsController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * @Author Vlaeyen Thijs
 * SettingsPane
 */

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
   private Button resetDiscount;

   private final CheckBox headerMessageCheckbox = new CheckBox();
   private final TextField headerMessage = new TextField();

   private final CheckBox headerDateCheckbox = new CheckBox();

   private final CheckBox footerMessageCheckbox = new CheckBox();
   private final TextField footerMessage = new TextField();

   private final CheckBox footerDiscountCheckbox = new CheckBox();

   private final CheckBox footerVATCheckbox = new CheckBox();

   private Button saveReceipt;
   private Button resetReceipt;

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
      groupCheckbox.setSelected(containsDiscount("GROUP"));
      groups.setValue(controller.getDiscountGroupGroup());
      groupDiscounts.setValue(controller.getDiscountGroupPercent());
      setGroupButtons();
      this.add(groupCheckbox, 0,3,1,1);
      this.add(new Label("Group discount:"),1,3,1,1);
      groupDiscounts.getItems().addAll("1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%", "15%", "16%", "17%", "18%", "19%", "20%","21%", "22%", "23%", "24%", "25%", "26%","27%", "28%", "29%", "30%");
      groups.getItems().addAll("Group 1", "Group 2");
      this.add(groups, 2,3,1,1);
      this.add(groupDiscounts, 3,3,1,1);
      groupCheckbox.setOnAction(event -> setGroupButtons());

      //FOR THRESHOLD DISCOUNT
      thresholdCheckbox.setSelected(containsDiscount("THRESHOLD"));
      thresholdDiscounts.setValue(controller.getDiscountThresholdPercent());
      thresholdAmount.setText(controller.getDiscountThresholdAmount());
      setThresholdButtons();
      this.add(thresholdCheckbox,0,4,1,1);
      this.add(new Label("Threshold discount:"), 1,4,1,1);
      this.add(thresholdAmount,2,4,1,1);
      thresholdDiscounts.getItems().addAll("1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%", "15%", "16%", "17%", "18%", "19%", "20%","21%", "22%", "23%", "24%", "25%", "26%","27%", "28%", "29%", "30%");
      this.add(thresholdDiscounts,3,4,1,1);
      thresholdCheckbox.setOnAction(event -> setThresholdButtons());

      //FOR EXPENSIVE DISCOUNT
      expensiveCheckbox.setSelected(containsDiscount("EXPENSIVE"));
      expensiveDiscounts.setValue(controller.getDiscountExpensivePercent());
      setExpensiveButtons();
      this.add(new Label("Expensive:"),1,5,1,1);
      this.add(expensiveCheckbox,0,5,1,1);
      expensiveDiscounts.getItems().addAll("1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%", "15%", "16%", "17%", "18%", "19%", "20%","21%", "22%", "23%", "24%", "25%", "26%","27%", "28%", "29%", "30%");
      this.add(expensiveDiscounts,3,5,1,1);
      expensiveCheckbox.setOnAction(event -> setExpensiveButtons());

      //DISCOUNT BUTTONS
      saveDiscount = new Button("Save Discounts");
      this.add(saveDiscount,0,6,1,1);
      saveDiscount.setOnAction(event -> saveDiscount());
      resetDiscount = new Button("Reset Discounts");
      this.add(resetDiscount,1,6,1,1);
      resetDiscount.setOnAction(event -> {
         groupCheckbox.setSelected(false);
         thresholdCheckbox.setSelected(false);
         expensiveCheckbox.setSelected(false);
         setGroupButtons();
         setThresholdButtons();
         setExpensiveButtons();
         saveDiscount();
      });

      //RECEIPT

      this.add(new Label("Receipt:"), 0,7,1,1);

      //HEADER MESSAGE
      headerMessageCheckbox.setSelected(containsReceipt("HEADERMESSAGE"));
      this.add(headerMessageCheckbox, 0,8,1,1);
      this.add(new Label("Header Message: "),1,8,1,1);
      this.add(headerMessage,2,8,1,1);
      headerMessage.setText(controller.getReceiptHeaderMessage());
      setHeaderMessageButtons();
      headerMessageCheckbox.setOnAction(event -> setHeaderMessageButtons());

      //HEADER DATE
      headerDateCheckbox.setSelected(containsReceipt("HEADERDATE"));
      this.add(headerDateCheckbox, 0,9,1,1);
      this.add(new Label("Header Date"),1,9,1,1);

      //FOOTER MESSAGE
      footerMessageCheckbox.setSelected(containsReceipt("FOOTERMESSAGE"));
      this.add(footerMessageCheckbox, 0,10,1,1);
      this.add(new Label("Footer Message: "),1,10,1,1);
      this.add(footerMessage,2,10,1,1);
      footerMessage.setText(controller.getReceiptFooterMessage());
      setFooterMessageButtons();
      footerMessageCheckbox.setOnAction(event -> setFooterMessageButtons());

      //FOOTER DISCOUNT
      footerDiscountCheckbox.setSelected(containsReceipt("FOOTERDISCOUNT"));
      this.add(footerDiscountCheckbox,0,11,1,1);
      this.add(new Label("Footer Discount"),1,11,1,1);

      //FOOTER VAT
      footerVATCheckbox.setSelected(containsReceipt("FOOTERVAT"));
      this.add(footerVATCheckbox,0,12,1,1);
      this.add(new Label("Footer VAT"),1,12,1,1);

      //BUTTONS
      saveReceipt = new Button("Save Receipt");
      resetReceipt = new Button("Reset Receipt");
      this.add(saveReceipt,0,13,1,1);
      this.add(resetReceipt,1,13,1,1);
      saveReceipt.setOnAction(event -> saveReceipt());
      resetReceipt.setOnAction(event -> {
         headerMessageCheckbox.setSelected(false);
         headerDateCheckbox.setSelected(false);
         footerMessageCheckbox.setSelected(false);
         footerDiscountCheckbox.setSelected(false);
         footerVATCheckbox.setSelected(false);
         setHeaderMessageButtons();
         setFooterMessageButtons();
         saveReceipt();
      });

   }

   private void setGroupButtons(){
      if(groupCheckbox.isSelected()){
         groups.setDisable(false);
         groupDiscounts.setDisable(false);
      }else{
         groups.setDisable(true);
         groupDiscounts.setDisable(true);
         groups.setValue("Group 1");
         groupDiscounts.setValue("1%");
      }
   }

   private void setThresholdButtons(){
      if (thresholdCheckbox.isSelected()){
         thresholdDiscounts.setDisable(false);
         thresholdAmount.setDisable(false);
      } else {
         thresholdDiscounts.setDisable(true);
         thresholdAmount.setDisable(true);
         thresholdDiscounts.setValue("1%");
         thresholdAmount.setText("0");
      }
   }

   private void setExpensiveButtons(){
      if (expensiveCheckbox.isSelected()){
         expensiveDiscounts.setDisable(false);
      } else {
         expensiveDiscounts.setDisable(true);
         expensiveDiscounts.setValue("1%");
      }
   }

   private void setHeaderMessageButtons(){
      if (headerMessageCheckbox.isSelected()){
         headerMessage.setDisable(false);
      } else {
         headerMessage.setDisable(true);
         headerMessage.setText(" ");
      }
   }

   private void setFooterMessageButtons(){
      if (footerMessageCheckbox.isSelected()){
         footerMessage.setDisable(false);
      } else {
         footerMessage.setDisable(true);
         footerMessage.setText(" ");
      }
   }

   private void saveReceipt(){
      ArrayList<String> active = new ArrayList<>();
      if(headerMessageCheckbox.isSelected()){
         active.add("HEADERMESSAGE");
         controller.setReceiptHeaderMessage(headerMessage.getText());
      } else {
         controller.setReceiptHeaderMessage("null");
      }

      if(headerDateCheckbox.isSelected()){
         active.add("HEADERDATE");
      }

      if(footerMessageCheckbox.isSelected()){
         active.add("FOOTERMESSAGE");
         controller.setReceiptFooterMessage(footerMessage.getText());
      }else{
         controller.setReceiptFooterMessage("null");
      }

      if(footerVATCheckbox.isSelected()){
         active.add("FOOTERVAT");
      }

      if(footerDiscountCheckbox.isSelected()){
         active.add("FOOTERDISCOUNT");
      }
      controller.setReceiptActive(active.toString());
      controller.save();
   }

   private void saveDiscount(){
      ArrayList<String> active = new ArrayList<>();

      if (groupCheckbox.isSelected()){
         active.add("GROUP");
         controller.setDiscountGroupGroup(groups.getValue().toString().trim());
         controller.setDiscountGroupPercent(groupDiscounts.getValue().toString().trim());
      } else {
         controller.setDiscountGroupGroup("null");
         controller.setDiscountGroupPercent("null");
      }

      if (thresholdCheckbox.isSelected()){
         active.add("THRESHOLD");
         controller.setDiscountThresholdAmount(thresholdAmount.getText());
         controller.setDiscountThresholdPercent(thresholdDiscounts.getValue().toString().trim());
      } else {
         controller.setDiscountThresholdAmount("null");
         controller.setDiscountThresholdPercent("null");
      }

      if (expensiveCheckbox.isSelected()){
         active.add("EXPENSIVE");
         controller.setDiscountExpensivePercent(expensiveDiscounts.getValue().toString().trim());
      } else {
         controller.setDiscountExpensivePercent("null");
      }
      controller.setDiscountActive(active.toString());
      controller.save();
   }

   private boolean containsDiscount(String discount){
      return contains(discount, controller.getDiscountActive());
   }

   private boolean containsReceipt(String receipt){
      return contains(receipt, controller.getReceiptActive());
   }

   private boolean contains(String discount, String array){
      boolean out = false;
      array = array.replaceAll("\\[*\\]*", "");
      String[] activeDiscounts = array.split(", ");
      for (String s:activeDiscounts) {
         if (s.equals(discount)){
            out = true;
         }
      }
      return out;
   }
}
