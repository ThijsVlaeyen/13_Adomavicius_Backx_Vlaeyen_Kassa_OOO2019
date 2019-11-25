package model.IO;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import model.IO.excel.ExcelPlugin;
import model.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelLoadSaveStrategy implements LoadSaveStrategy {
    private Workbook workbook;
    private File file;
    private Sheet sheet;
    ExcelPlugin excelPlugin;

    public ExcelLoadSaveStrategy(String filepath, ExcelPlugin excelPlugin){
        file = new File(filepath);
        this.excelPlugin = excelPlugin;
    }

    @Override
    public ArrayList<Product> load() {
        ArrayList<ArrayList<String>> args = excelPlugin.read(file);
        ArrayList<Product> products = new ArrayList<Product>();
        for (ArrayList<String> list : args) {
            products.add(new Product(Double.parseDouble(list.get(3)), list.get(1),
                    Integer.parseInt(list.get(5)), Integer.parseInt(list.get(0)), list.get(2)));
        }
        return products;
    }

    @Override
    public void save(List<Product> products) {
        ArrayList<ArrayList<String>> args = new ArrayList<ArrayList<String>>();
        ArrayList<String> list = new ArrayList<String>();
        for (Product p : products) {
            list.clear();
            list.add(String.valueOf(p.getId()));
            list.add(p.getName());
            list.add(p.getGroup());
            list.add(String.valueOf(p.getPrice()));
            list.add(String.valueOf(p.getStock()));
            args.add(list);
        }
        try{
            excelPlugin.write(file, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
