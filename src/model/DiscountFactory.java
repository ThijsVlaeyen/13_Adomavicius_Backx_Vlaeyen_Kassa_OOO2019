package model;

import java.util.ArrayList;

public class DiscountFactory {

   ArrayList<DiscountStrategy> discounts = new ArrayList<>();

   public void create(ArrayList<String> types){
      for (String t: types){
         switch (t){
            case "GROUP":
               discounts.add(new DiscountGroup());
               break;
            case "THRESHOLD":
               discounts.add(new DiscountThreshold());
               break;
            case "EXPENSIVE":
               discounts.add(new DiscountExpensive());
               break;
         }
      }
   }

   public ArrayList<DiscountStrategy> getDiscounts(){return discounts;}

}
