package com.lance.market.factory.product;

public class ReturnCashFee extends CashFee {

    private double baseCash;

    private double returnCash;

    public ReturnCashFee(double baseCash, double returnCash) {
        this.baseCash = baseCash;
        this.returnCash = returnCash;
    }

    public double getBaseCash() {
        return baseCash;
    }

    public void setBaseCash(double baseCash) {
        this.baseCash = baseCash;
    }

    public double getReturnCash() {
        return returnCash;
    }

    public void setReturnCash(double returnCash) {
        this.returnCash = returnCash;
    }

    @Override
    public double acceptCash(double money) {
        return money - Math.floor(money / baseCash) * returnCash;
    }
}
