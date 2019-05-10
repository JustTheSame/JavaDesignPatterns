package com.lance.decoration.product;

public class Parlour {

    private String wall;

    private String televison;

    private String sofa;

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getTelevison() {
        return televison;
    }

    public void setTelevison(String televison) {
        this.televison = televison;
    }

    public String getSofa() {
        return sofa;
    }

    public void setSofa(String sofa) {
        this.sofa = sofa;
    }

    @Override
    public String toString() {
        return "Parlour{" +
                "wall='" + wall + '\'' +
                ", televison='" + televison + '\'' +
                ", sofa='" + sofa + '\'' +
                '}';
    }

    public void show()
    {
        System.out.println("=====show time======");
        System.out.println(toString());
    }
}
