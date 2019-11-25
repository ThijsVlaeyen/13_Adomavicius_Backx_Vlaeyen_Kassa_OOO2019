package model.IO;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import model.Product;

import java.io.File;
import java.util.HashMap;

public class ReadFromExcelFile {
    private Workbook workbook;
    private File inputFile;
    private Sheet sheet;

    public ReadFromExcelFile(String filepath){
        try{
            inputFile = new File(filepath);
            workbook = Workbook.getWorkbook(this.inputFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.sheet = workbook.getSheet(0);
    }

    public HashMap<Integer, Product> readData(){
        HashMap<Integer,Product> result = new HashMap<>();
        for (int i=0;i<sheet.getRows();i++){
            for (int j=0;j<sheet.getColumns();i++){
                Cell  cell = sheet.getCell(i,j);
                System.out.println(cell.getContents());
            }
        }
        return result;
    }

}
