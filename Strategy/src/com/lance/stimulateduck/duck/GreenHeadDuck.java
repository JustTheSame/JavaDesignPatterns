package com.lance.stimulateduck.duck;

import com.lance.stimulateduck.flybehavior.GoodFlyBehavior;
import com.lance.stimulateduck.quackbehavior.GaGaQuackBehavior;

public class GreenHeadDuck extends Duck {

    public GreenHeadDuck(){
        flyBehavior = new GoodFlyBehavior();
        quackBehavior = new GaGaQuackBehavior();
    }
    @Override
    public void display() {
        System.out.println("~~green head~~");
    }
}
