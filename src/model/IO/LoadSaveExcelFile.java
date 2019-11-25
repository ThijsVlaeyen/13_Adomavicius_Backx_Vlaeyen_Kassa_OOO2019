package model.IO;


import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoadSaveExcelFile extends TextLoadSaveTemplate {
    private ExcelPlugin excelPlugin;

    public LoadSaveExcelFile(String filepath){
        super(filepath);
    }

    public LoadSaveExcelFile(){
        this("src/Files/article.xls");
    }

    @Override
    public ArrayList<Product> readFromFile() {
        excelPlugin = new ExcelPlugin();
        ArrayList<Product> result = new ArrayList<>();
        try {
            ArrayList<ArrayList<String>> products = excelPlugin.read(new File(super.getPath()));
            for (ArrayList<String> product:products) {
                result.add(new Product(Integer.parseInt(product.get(0)),product.get(1),product.get(2),Double.parseDouble(product.get(3)) ,Integer.parseInt(product.get(4))));
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void saveFile(List<Product> products) {

    }

    protected void saveFile(ArrayList<Product> products) {
        ArrayList<ArrayList<String>> result= new ArrayList<>();
        for (Product p:products) {
            ArrayList<String> product = new ArrayList<>();
            product.add(String.valueOf(p.getId()));
            product.add(p.getName());
            product.add(p.getGroup());
            product.add(String.valueOf(p.getPrice()));
            product.add(String.valueOf(p.getStock()));
            result.add(product);
        }
        try {
            excelPlugin.write(new File(super.getPath()),result);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
