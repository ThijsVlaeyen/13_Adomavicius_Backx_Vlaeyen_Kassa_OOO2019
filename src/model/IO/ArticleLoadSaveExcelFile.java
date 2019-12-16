package model.IO;


import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ArticleLoadSaveExcelFile implements LoadSaveStrategy {
    private ExcelPlugin excelPlugin;
    private String path;

    public ArticleLoadSaveExcelFile(String path){
        if (path.isEmpty()){
            throw new IllegalArgumentException("Path can't be empty");
        }
        this.path = path;
    }

    public ArticleLoadSaveExcelFile(){
        this("src/Files/article.xls");
    }

    @Override
    public ArrayList<Object> load() {
        excelPlugin = new ExcelPlugin();
        ArrayList<Object> result = new ArrayList<>();
        try {
            ArrayList<ArrayList<String>> products = excelPlugin.read(new File(path));
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
    public void save(ArrayList<Object> products) {
        ArrayList<ArrayList<String>> result= new ArrayList<>();
        for (Object obj : products) {
            Product p = (Product) obj;
            ArrayList<String> product = new ArrayList<>();
            product.add(String.valueOf(p.getId()));
            product.add(p.getName());
            product.add(p.getGroup());
            product.add(String.valueOf(p.getPrice()));
            product.add(String.valueOf(p.getStock()));
            result.add(product);
        }
        try {
            excelPlugin.write(new File(path),result);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
