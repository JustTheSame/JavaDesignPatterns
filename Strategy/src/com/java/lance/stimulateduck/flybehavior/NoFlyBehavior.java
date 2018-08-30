package com.java.lance.stimulateduck.flybehavior;

public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("~~no fly~~");
    }
}
