package controller;

import database.ProductDB;
import model.IO.LoadSaveProperties;
import model.IO.LoadSaveType;
import view.panels.SettingsPane;

import java.util.ArrayList;

public class SettingsController {//todo maybe also observer?
    private ProductDB db;
    private SettingsPane view;
    private LoadSaveProperties properties;

    public SettingsController(ProductDB db){
        this.db = db;
        properties = new LoadSaveProperties();
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
        return LoadSaveProperties.getLoadSave().toLowerCase();
    }

    public void save(){properties.save();}

    //LOAD SAVE STRATEGY
    public void setLoadSave(String type) {
        LoadSaveProperties.setLoadSave(type);
    }

    //SET ACTIVE DISCOUNTS
    public void setDiscountActive(String type){LoadSaveProperties.setDiscountActive(type);}
    public String getDiscountActive(){return LoadSaveProperties.getDiscountActive();}

    //GROUP DISCOUNT SETTERS GETTERS
    public void setDiscountGroupGroup(String type){
        LoadSaveProperties.setDiscountGroupGroup(type);
    }
    public void setDiscountGroupPercent(String type){
        LoadSaveProperties.setDiscountGroupPercent(type);
    }
    public String getDiscountGroupGroup(){return LoadSaveProperties.getDiscountGroupGroup();}
    public String getDiscountGroupPercent(){return LoadSaveProperties.getDiscountGroupPercent();}

    //THRESHOLD DISCOUNT SETTERS
    public void setDiscountThresholdAmount(String type){LoadSaveProperties.setDiscountThresholdAmount(type);}
    public void setDiscountThresholdPercent(String type){LoadSaveProperties.setDiscountThresholdPercent(type);}
    public String getDiscountThresholdAmount(){return LoadSaveProperties.getDiscountThresholdAmount();}
    public String getDiscountThresholdPercent(){return LoadSaveProperties.getDiscountThresholdPercent();}

    //EXPENSIVE DISCOUNT SETTERS
    public void setDiscountExpensivePercent(String type){LoadSaveProperties.setDiscountExpensivePercent(type);}
    public String getDiscountExpensivePercent(){return LoadSaveProperties.getDiscountExpensivePercent();}

    //RECEIPT
    //HEADER MESSAGE
    public void setReceiptHeaderMessage(String type){LoadSaveProperties.setReceiptHeaderMessage(type);}
    public String getReceiptHeaderMessage(){return LoadSaveProperties.getReceiptHeaderMessage();}

    //FOOTER MESSAGE
    public void setReceiptFooterMessage(String type){LoadSaveProperties.setReceiptFooterMessage(type);}
    public String getReceiptFooterMessage(){return LoadSaveProperties.getReceiptFooterMessage();}

    //ACTIVE
    public void setReceiptActive(String type){LoadSaveProperties.setReceiptActive(type);}
    public String getReceiptActive(){return LoadSaveProperties.getReceiptActive();}
}
