package com.java.lance.stimulateduck;

import com.java.lance.stimulateduck.duck.Duck;
import com.java.lance.stimulateduck.duck.GreenHeadDuck;
import com.java.lance.stimulateduck.duck.RedHeadDuck;
import com.java.lance.stimulateduck.flybehavior.NoFlyBehavior;

public class StimulateDuck {
    public static void main(String[] args) {
        Duck mGreenHeadDuck = new GreenHeadDuck();
        Duck mRedHeadDuck = new RedHeadDuck();

        mGreenHeadDuck.display();
        mGreenHeadDuck.fly();
        mGreenHeadDuck.quack();

        mRedHeadDuck.display();
        mRedHeadDuck.fly();
        mRedHeadDuck.quack();

        mGreenHeadDuck.setFlyBehavior(new NoFlyBehavior());
        mGreenHeadDuck.display();
        mGreenHeadDuck.fly();

    }
}
