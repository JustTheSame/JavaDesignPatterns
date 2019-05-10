package com.lance.decoration.builder;

public class ConcreteDecorator1 extends Decorator {
    @Override
    public void buildWall() {
        product.setWall("wall-1");
    }

    @Override
    public void buildTV() {
        product.setTelevison("TV-1");
    }

    @Override
    public void buildSofa() {
        product.setSofa("sofa-1");
    }
}
