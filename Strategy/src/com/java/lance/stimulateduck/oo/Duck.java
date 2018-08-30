package com.java.lance.stimulateduck.oo;

public abstract class Duck {
    public Duck() {

    }

    public void quack() {
        System.out.println("~~gaga~~");
    }

    public abstract void display();

    public void swim() {
        System.out.println("~~i'm swim~~");
    }

    public void fly() {
        System.out.println("~~i'm fly~~");
    }
}
