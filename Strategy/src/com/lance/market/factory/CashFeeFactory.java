package com.lance.market.factory;

import com.lance.market.factory.product.CashFee;
import com.lance.market.factory.product.DiscountCashFee;
import com.lance.market.factory.product.NormalCashFee;
import com.lance.market.factory.product.ReturnCashFee;

import java.util.Scanner;

public class CashFeeFactory {
    public static CashFee createCashFee(int type, double discount, double basePrice, double returnPrice) {
        CashFee cashFee = null;
        switch (type) {
            case 1:
                cashFee = new NormalCashFee();
                break;
            case 2:
                cashFee = new DiscountCashFee(discount);
                break;
            case 3:
                cashFee = new ReturnCashFee(basePrice, returnPrice);
                break;
        }

        return cashFee;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入单价：");
        String price = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入数量：");
        String num = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入折扣类型（1 无折扣 2 打折 3 满减）：");
        String type = scanner.nextLine();

        double discount = 0.0d;
        double basePrice = 0;
        double returnPrice = 0;
        if ("2".equals(type)) {
            scanner = new Scanner(System.in);
            System.out.println("输入折扣：");
            discount = Double.parseDouble(scanner.nextLine());
        }

        if ("3".equals(type)) {
            scanner = new Scanner(System.in);
            System.out.println("基础金额：");
            basePrice = Double.parseDouble(scanner.nextLine());
            scanner = new Scanner(System.in);
            System.out.println("返还现金：");
            returnPrice = Double.parseDouble(scanner.nextLine());
        }

        Double money = Double.parseDouble(price) * Integer.parseInt(num);
        CashFee cashFee = CashFeeFactory.createCashFee(Integer.parseInt(type), discount, basePrice, returnPrice);
        System.out.println("总价：" + cashFee.acceptCash(money));
    }
}
