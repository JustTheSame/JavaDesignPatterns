package com.lance.market.strategy;

public class NormalCashFee extends CashFee {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
