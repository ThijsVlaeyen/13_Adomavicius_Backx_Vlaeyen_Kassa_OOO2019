package controllers;

import database.ProductDB;
import model.IO.LoadSaveProperties;
import model.IO.LoadSaveType;
import view.panels.SettingsPane;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class SettingsController {
    private ProductDB db;
    private SettingsPane view;
    private LoadSaveProperties properties = new LoadSaveProperties();

    public SettingsController(ProductDB db){
        this.db = db;
        properties.load();
    }

    public ArrayList<String> getTypes(){
        ArrayList<String> result = new ArrayList<>();
        for (LoadSaveType lst:LoadSaveType.values()){
            result.add(lst.getName());
        }
        return result;
    }

    public void setView(SettingsPane view){
        this.view = view;
    }

    public String getType(){
        return properties.getLoadSave().toLowerCase();
    }

    public void save(){properties.save();}

    //LOAD SAVE STRATEGY
    public void setLoadSave(String type) {
        properties.setLoadSave(type);
    }

    //GROUP DISCOUNT SETTERS GETTERS
    public void setDiscountGroupActive(String type){
        properties.setDiscountGroupActive(type);
    }
    public void setDiscountGroupGroup(String type){
        properties.setDiscountGroupGroup(type);
    }
    public void setDiscountGroupPercent(String type){
        properties.setDiscountGroupPercent(type);
    }
    public String getDiscountGroupActive(){return properties.getDiscountGroupActive();}

    //THRESHOLD DISCOUNT SETTERS
    public void setDiscountThresholdActive(String type){properties.setDiscountThresholdActive(type);}
    public void setDiscountThresholdAmount(String type){properties.setDiscountThresholdAmount(type);}
    public void setDiscountThresholdPercent(String type){properties.setDiscountThresholdPercent(type);}
    public String getDiscountThresholdActive(){return properties.getDiscountThresholdActive();}

    //EXPENSIVE DISCOUNT SETTERS
    public void setDiscountExpensiveActive(String type){properties.setDiscountExpensiveActive(type);}
    public void setDiscountExpensivePercent(String type){properties.setDiscountExpensivePercent(type);}
    public String getDiscountExpensiveActive(){return properties.getDiscountExpensiveActive();}
}
