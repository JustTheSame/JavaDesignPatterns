package com.lance.market.factory.product;

public class DiscountCashFee extends CashFee {

    private double discount = 0.00;


    public DiscountCashFee(double discount) {
        this.discount = discount / 10 ;
    }

    @Override
    public double acceptCash(double money) {
        return this.discount * money;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
