package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.Repositry.ExcelRepositry;
import com.example.service.OrderService;

public class items {
    //物品の追加、クエリを作成し表示、データを表示
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ExcelRepositry repo = new ExcelRepositry("data.xlsx");
        repo.files();
        OrderService service = new OrderService(null);

        System.out.println("物品の追加:1 物品データの変更:2 データを表示:3　を入力してください");
        int swi = Integer.parseInt(br.readLine());
        switch (swi) {
            case 1:
                service.itemtoroku();
                break;
            case 2:
                System.out.println("IDを入力してください");
                int x = Integer.parseInt(br.readLine());
                service.updite(x);
                break;
            case 3:
                //クエリを作成して表示
                break;
        
            default:
                break;
        }

    }
}
