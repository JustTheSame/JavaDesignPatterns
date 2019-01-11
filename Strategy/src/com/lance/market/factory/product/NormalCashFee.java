package com.lance.market.factory.product;

public class NormalCashFee extends CashFee {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
