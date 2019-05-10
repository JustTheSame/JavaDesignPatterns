package com.lance.decoration.builder;

public class ConcreteDecorator2 extends Decorator {
    @Override
    public void buildWall() {
        product.setWall("wall-2");
    }

    @Override
    public void buildTV() {
        product.setTelevison("TV-2");
    }

    @Override
    public void buildSofa() {
        product.setSofa("sofa-2");
    }
}
