package com.lance.stimulateduck.duck;

import com.lance.stimulateduck.flybehavior.BadFlyBehavior;
import com.lance.stimulateduck.quackbehavior.GuGuQuackBehavior;

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
