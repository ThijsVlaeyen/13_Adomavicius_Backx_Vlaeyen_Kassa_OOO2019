package model.IO;


import excel.ExcelPlugin;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import model.Product;

import java.io.File;
import java.util.HashMap;

public class ReadFromExcelFile {
    private ExcelPlugin excelPlugin;
    private String path;

    public ReadFromExcelFile(String filepath){
        excelPlugin = new ExcelPlugin();
        this.path = filepath;
    }

    public void saveToExcel()
}
