package com.example.Repositry;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelRepositry {
    private String filepath;
    private static final String FILE_PATH = "data.xlsx";
    public ExcelRepositry(String a){
        this.filepath = a;
    }
    public void files(){
        //エクセルワークブックが既に存在するのか
        File file = new File(FILE_PATH);
        if(file.exists()){
            return;
        }
        //ワークブックを作成
        try (Workbook workbook = new XSSFWorkbook()){
            //シートを作成
            createCustomerSheet(workbook);
            createOrderSheet(workbook);
            createOrderItemSheet(workbook);

            //書き込む
            try (FileOutputStream out = new FileOutputStream(FILE_PATH)) {
                workbook.write(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //エクセルシート作成
    private void createCustomerSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Customers");
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("customer_id");
        header.createCell(1).setCellValue("name");
    }

    private void createOrderSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Orders");
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("order_id");
        header.createCell(1).setCellValue("customer_id");
        header.createCell(2).setCellValue("total");
        header.createCell(3).setCellValue("datetime");
    }

    private void createOrderItemSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("OrderItems");
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("order_id");
        header.createCell(1).setCellValue("product");
        header.createCell(2).setCellValue("price");
        header.createCell(3).setCellValue("quantity");
    }
    //idチェック:名前を取得
    public String checkId(int a){
        try (Workbook workbook = WorkbookFactory.create(new File(FILE_PATH))){
            Sheet sheet = workbook.getSheet("Customers");
            for (Row row : sheet){
                //ヘッダーを除外
                if (row.getRowNum() == 0) continue;

                //セルに何もなかった時
                Cell idCell = row.getCell(0);
                if (idCell == null) continue;

                //idが存在した場合trueを返す
                int id = (int) idCell.getNumericCellValue();
                if (id == a) {
                    //idと一致する名前を返す
                    Cell nameCell = row.getCell(1);
                    String c =  nameCell.getStringCellValue();
                    return c;
                }
            }
        } catch (Exception e) {
            // error
        }
        return null;
    }
    //idチェック:存在の有無
    public boolean existsCustomer(int targetId) {
        try (Workbook workbook = WorkbookFactory.create(new File(FILE_PATH))) {
            //シートを開く
            Sheet sheet = workbook.getSheet("Customers");

            //idを比較
            for (Row row : sheet) {
                //ヘッダーを除外
                if (row.getRowNum() == 0) continue;

                //セルに何もなかった時
                Cell idCell = row.getCell(0);
                if (idCell == null) continue;

                //idが存在した場合trueを返す
                int id = (int) idCell.getNumericCellValue();
                if (id == targetId) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    //顧客シートに追加
    public void adcus(int a, String b){
        try (Workbook workbook =WorkbookFactory.create(new File(FILE_PATH))){
            Sheet sheet = workbook.getSheet("Customers");
            int lastrow = sheet.getLastRowNum();
            //最後のデータの次の行
            Row row = sheet.createRow(lastrow + 1);

            row.createCell(0).setCellValue(a);
            row.createCell(1).setCellValue(b);

            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //注文票シートに追加
    public void asord(String a, String b, String c, String d){
        try (Workbook workbook = WorkbookFactory.create(new File(FILE_PATH))){
            Sheet sheet = workbook.getSheet("Orders");
            int lastrow = sheet.getLastRowNum();
            Row row = sheet.createRow(lastrow + 1);

            row.getCell(0).setCellValue(a);
            row.getCell(1).setCellValue(b);
            row.getCell(2).setCellValue(c);
            row.getCell(3).setCellValue(d);
            try( FileOutputStream fos = new FileOutputStream(FILE_PATH)){
                workbook.write(fos);
            }

        } catch (Exception e) {
            // error
        }
    }
    //物品シートに追加
    public void asite(String a,String b, String c, String d){
        try (Workbook workbook = WorkbookFactory.create(new File(FILE_PATH))){
            Sheet sheet = workbook.getSheet("OrderItems");

            int lastrow = sheet.getLastRowNum();
            Row row = sheet.createRow(lastrow + 1);

            row.getCell(0).setCellValue(a);
            row.getCell(1).setCellValue(b);
            row.getCell(2).setCellValue(c);
            row.getCell(3).setCellValue(d);
            try( FileOutputStream fos = new FileOutputStream(FILE_PATH)){
                workbook.write(fos);
            }

        } catch (Exception e) {
            //error
        }
    }
    //IDを照らし合わせる
    public String itmupd(int a){
         try (Workbook workbook = WorkbookFactory.create(new File(FILE_PATH))){
            Sheet sheet = workbook.getSheet("OrderItems");
            for (Row row : sheet){
                //ヘッダーを除外
                if (row.getRowNum() == 0) continue;

                //セルに何もなかった時
                Cell idCell = row.getCell(0);
                if (idCell == null) continue;

                //idが存在した場合trueを返す
                int id = (int) idCell.getNumericCellValue();
                if (id == a) {
                    //idと一致する名前を返す
                    Cell nameCell = row.getCell(1);
                    String c =  nameCell.getStringCellValue();
                    return c;
                }
            }
        } catch (Exception e) {
            // error
        }
        return null;
    }
    //物品変更
    public void upd(int a, String b, String c){
        try (Workbook workbook = WorkbookFactory.create(new File(FILE_PATH))){
            Sheet sheet = workbook.getSheet("OrderItems");
            for (Row row : sheet){
                //ヘッダーを除外
                if (row.getRowNum() == 0) continue;

                //セルに何もなかった時
                Cell idCell = row.getCell(0);
                if (idCell == null) continue;

                //idが存在した場合trueを返す
                int id = (int) idCell.getNumericCellValue();
                if (id == a) {
                    if (b == "1"){
                        row.getCell(2).setCellValue(c);
                    }else if(b == "2"){
                        row.getCell(3).setCellValue(c);
                    }else{
                        break;
                    }
                }
            }
            
        } catch (Exception e) {
            // error
        }
    }
}
