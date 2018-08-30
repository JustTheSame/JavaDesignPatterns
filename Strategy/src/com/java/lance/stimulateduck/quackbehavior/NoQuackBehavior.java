package com.java.lance.stimulateduck.quackbehavior;

public class NoQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("~~no quack~~");
    }
}
