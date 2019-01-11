package com.lance.market.basic;

import java.util.Scanner;

public class Cash {
    public String list = "";
    public Double totalPrice = 0.00;

    public void buttonOK() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入单价：");
        String price = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入数量：");
        String num = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入折扣：");
        String discount = scanner.nextLine();

        Double account = Double.parseDouble(price) * Integer.parseInt(num) * Double.parseDouble(discount) / 10;
        list += "单价：" + price + "，数量：" + num + "，折扣：" + discount + "\n";
        totalPrice += account;
    }

    public static void main(String[] args) {
        Cash cash = new Cash();
        boolean flag = true;
        while (flag) {
            cash.buttonOK();
            if (cash.totalPrice > 10) {
                flag = false;
            }
        }
        System.out.println("=============");
        System.out.println("清单：\n" + cash.list);
        System.out.println("总价：" + cash.totalPrice);
    }
}
