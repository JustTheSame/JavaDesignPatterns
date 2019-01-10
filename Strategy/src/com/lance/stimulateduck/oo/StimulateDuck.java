package com.lance.stimulateduck.oo;

public class StimulateDuck {
    public static void main(String[] args) {
        GreenHeadDuck greenHeadDuck = new GreenHeadDuck();
        RedHeadDuck redHeadDuck = new RedHeadDuck();

        greenHeadDuck.display();
        greenHeadDuck.quack();
        greenHeadDuck.swim();

        redHeadDuck.display();
        redHeadDuck.quack();
        redHeadDuck.swim();

        greenHeadDuck.fly();
        redHeadDuck.fly();
    }
}
