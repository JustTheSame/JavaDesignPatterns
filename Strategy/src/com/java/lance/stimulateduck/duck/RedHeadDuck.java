package com.java.lance.stimulateduck.duck;

import com.java.lance.stimulateduck.flybehavior.BadFlyBehavior;
import com.java.lance.stimulateduck.quackbehavior.GaGaQuackBehavior;
import com.java.lance.stimulateduck.quackbehavior.GuGuQuackBehavior;

public class RedHeadDuck extends Duck {
    public RedHeadDuck() {
        flyBehavior = new BadFlyBehavior();
        quackBehavior = new GuGuQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("~~red head~~");
    }
}
