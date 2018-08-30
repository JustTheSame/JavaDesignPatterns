package com.java.lance.stimulateduck.duck;

import com.java.lance.stimulateduck.flybehavior.FlyBehavior;
import com.java.lance.stimulateduck.quackbehavior.QuackBehavior;

public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }

    public void fly() {
        flyBehavior.fly();
    }

    public void quack() {
        quackBehavior.quack();
    }

    public abstract void display();

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }

    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }
}
