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
        return properties.load().toLowerCase();
    }


    public void save(String type) {
        properties.save(type);
    }
}
