package com.lance.calculator;

import com.lance.calculator.factory.OperationFactory;
import com.lance.calculator.operation.Operation;

public class Program {
    public static void main(String[] args) {
        Operation operation = OperationFactory.createOperation("+");
        operation.NumberA = 10;
        operation.NumberB = 5;
        System.out.println("计算结果: " + operation.getResult());
    }
}
