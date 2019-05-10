package com.lance.decoration.director;

import com.lance.decoration.builder.Decorator;
import com.lance.decoration.product.Parlour;

public class ProjectManager {

    private Decorator builder;

    public ProjectManager(Decorator builder) {
        this.builder = builder;
    }

    //产品构建与组装方法
    public Parlour decorate() {
        builder.buildWall();
        builder.buildTV();
        builder.buildSofa();
        return builder.getResult();
    }
}
