package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.Repositry.ExcelRepositry;
import com.example.service.OrderService;

public class Main {
    private static String names;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ExcelRepositry repo = new ExcelRepositry("data.xlsx");
        repo.files();
        OrderService service = new OrderService(repo);

        System.out.println("IDを入力してください:新規の顧客の場合0を入力してください");
        String ID = br.readLine();
        names = service.first(ID);

        boolean aa = true;

        while(aa){
            System.out.println("ようこそ" + names + "さん");
        System.out.println("商品IDを入力");
        String orderid = br.readLine(); 
        //
        System.out.println("商品ID:" + orderid);
        System.out.println("量を入力");
        String totals = br.readLine(); 

        System.out.println("商品ID:" + orderid);
        System.out.println("量" + totals);
        System.out.println("日にちを入力(書式:yyyy/mm/dd)");
        String datetime = br.readLine();
        
        System.out.println("商品ID:" + orderid);
        System.out.println("量" + totals);
        System.out.println("日にち" + datetime);
        System.out.println("これでよろしいですか？");
        
        //noの場合登録を行わない処理を挿入予定
        service.toroku(ID, orderid, totals, datetime);

        System.out.println("続ける場合はy 終了する場合はnを入力");
        String bb = br.readLine();
        if (bb == "n"){
            aa = false;
        }
        }

        // System.out.println("ようこそ" + names + "さん");
        // System.out.println("商品IDを入力");
        // String orderid = br.readLine(); 
        // //
        // System.out.println("商品ID:" + orderid);
        // System.out.println("量を入力");
        // String totals = br.readLine(); 

        // System.out.println("商品ID:" + orderid);
        // System.out.println("量" + totals);
        // System.out.println("日にちを入力(書式:yyyy/mm/dd)");
        // String datetime = br.readLine(); 
        // service.toroku(ID, orderid, totals, datetime);
    }
}