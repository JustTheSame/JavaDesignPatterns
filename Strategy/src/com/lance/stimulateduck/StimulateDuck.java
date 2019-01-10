package com.lance.stimulateduck;



import com.lance.stimulateduck.duck.Duck;
import com.lance.stimulateduck.duck.GreenHeadDuck;
import com.lance.stimulateduck.duck.RedHeadDuck;
import com.lance.stimulateduck.flybehavior.NoFlyBehavior;

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
