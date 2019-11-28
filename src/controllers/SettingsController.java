package controllers;

import database.ProductDB;
import model.IO.LoadSaveProperties;
import model.IO.LoadSaveType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SettingsController {
    private ProductDB db;
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

    public String getType(){
        return properties.load().toLowerCase();
    }


    public void save(String type) {
        properties.save(type);
    }
}
