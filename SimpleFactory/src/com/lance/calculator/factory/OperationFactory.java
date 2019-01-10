package com.lance.calculator.factory;

import com.lance.calculator.operation.*;

public class OperationFactory {
    public static Operation createOperation(String operate) {
        Operation operation = null;
        switch (operate) {
            case "+":
                operation = new PlusOperation();
                break;
            case "-":
                operation = new MinusOperation();
                break;
            case "*":
                operation = new MultiplyOperation();
                break;
            case "/":
                operation = new DivideOperation();
                break;
        }
        return operation;
    }
}
