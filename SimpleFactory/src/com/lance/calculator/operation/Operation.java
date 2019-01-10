package com.lance.calculator.operation;

public abstract class Operation {
    public double NumberA;
    public double NumberB;

    public double getNumberA() {
        return NumberA;
    }

    public void setNumberA(double numberA) {
        NumberA = numberA;
    }

    public double getNumberB() {
        return NumberB;
    }

    public void setNumberB(double numberB) {
        NumberB = numberB;
    }

    public abstract double getResult();
}
