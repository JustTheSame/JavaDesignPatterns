package com.lance.decoration;

import com.lance.decoration.builder.ConcreteDecorator1;
import com.lance.decoration.builder.Decorator;
import com.lance.decoration.director.ProjectManager;
import com.lance.decoration.product.Parlour;

public class Program {
    public static void main(String[] args) {

        Decorator builder = new ConcreteDecorator1();
        ProjectManager director = new ProjectManager(builder);
        Parlour product = director.decorate();
        product.show();

    }
}
