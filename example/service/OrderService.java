package com.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.Repositry.ExcelRepositry;

public class OrderService {
    private final ExcelRepositry repo;
    //private String idchck;
    public OrderService(ExcelRepositry a) {
        this.repo = a;
    }

    //IDを受け取って名前を返す
    public String first(String a){
        int cusId = parseId(a);
        String x = null;

        //IDをチェック
        if (cusId == 0) {
            try {
                newaccount();
                x = repo.checkId(cusId);
            } catch (IOException e) {
                System.out.println("入力エラーが発生しました");
            }
        } else {
            x = repo.checkId(cusId);
        }
        return x;
    }
    //int型に変更
    private int parseId(String input) {
        return Integer.parseInt(input);
    }
    private void newaccount() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //ExcelRepositry excelRepository = new ExcelRepositry("");

        System.out.println("IDを入力してください");
        int nid = Integer.parseInt(br.readLine());
        //IDが既に存在するかチェック
        if (repo.existsCustomer(nid)) {
            System.out.println("そのIDは既に存在します");
            return;
        }
        System.out.println("名前を入力してください（空白等不要）");
        String nname = br.readLine();
        //IDと名前をexcelrepositryに送り、カスターマーシートに追加
        repo.adcus(nid, nname);
        System.out.println("顧客を登録しました");
    }
    public void toroku(String id,String orderid,String totals,String date){
        repo.asord(id,orderid,totals,date);
    }
    public void itemtoroku() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("IDを入力してください");
        String ids = br.readLine();
        System.out.println("物品名を入力してください");
        String items = br.readLine();
        System.out.println("価格を入力してください");
        String prices = br.readLine();
        System.out.println("総量を入力してください");
        String quantities = br.readLine();

        System.out.println("ID: " + ids);
        System.out.println("物品名: " + items);
        System.out.println("価格: " + prices);
        System.out.println("総量: " + quantities);
        System.out.println("登録する場合:y しない場合:nを入力");
        String checks = br.readLine();
        if (checks == "y"){
            repo.asite(ids, items, prices, quantities);
        }
    }
    public void updite(int a) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String b = repo.itmupd(a);
        System.out.println("物品名: " + b);
        System.out.println("価格を変更:1 総量を変更:2 を入力してください");
        String c = br.readLine();
        System.out.println("変更後のデータ を入力してください");
        String d = br.readLine();

        repo.upd(a, c, d);
    }    
}