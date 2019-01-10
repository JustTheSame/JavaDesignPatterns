package com.lance.calculator.operation;

public class PlusOperation extends Operation {
    @Override
    public double getResult() {
        return NumberA + NumberB;
    }
}
