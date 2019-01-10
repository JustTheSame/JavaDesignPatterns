# 二十三种设计模式 - 简单工厂模式



## 简单工厂模式简介

### 定义

简单工厂模式：定义一个Factory类，可以根据参数的不同返回不同类的实例，被创建的实例通常有共同的父类。

简单工厂模式：只需要一个Factory类。

简单工厂模式：又称为静态工厂模式（Static Factory Pattern），<u>Factory类为静态类或包含静态方法</u>。

简单工厂模式：不属于23种GOF设计模式。

简单工厂模式：实质是由一个工厂类根据传入的参数，动态决定应该创建哪一个产品实例。



## 简单工厂模式结构

### 结构图

### 参与者

简单工厂模式参与者：

**Product**：抽象产品类，将具体产品类公共的代码进行抽象和提取后封装在一个抽象产品类中。

**ConcreteProject**：具体产品类，将需要创建的各种不同产品对象的相关代码封装到具体产品类中。

**Factory**：工厂类，提供一个工厂类用于创建各种产品，在工厂类中提供一个创建产品的工厂方法，该方法可以根据所传入参数的不同创建不同的具体产品对象。

**Client**：客户端类，只需要用工厂类的工厂方法并传入相应的参数即可得到一个产品对象。



## 简单工厂模式实现

首先创建一个抽象的Operation类

```java
package com.lance.calculator.operation;

public abstract class Operation {
    public double NumberA;
    public double NumberB;

    public double getNumberA() {
        return NumberA;
    }

    public void setNumberA(double numberA) {
        NumberA = numberA;
    }

    public double getNumberB() {
        return NumberB;
    }

    public void setNumberB(double numberB) {
        NumberB = numberB;
    }

    public abstract double getResult();
}
```

根据需求对Operation类进行继承

加法运算方式

```java
package com.lance.calculator.operation;

public class PlusOperation extends Operation {
    @Override
    public double getResult() {
        return NumberA + NumberB;
    }
}
```

减法运算方式

```
package com.lance.calculator.operation;

public class MinusOperation extends Operation {
    @Override
    public double getResult() {
        return NumberA - NumberB;
    }
}
```

乘法运算方式

```java
package com.lance.calculator.operation;

public class MultiplyOperation extends Operation {
    @Override
    public double getResult() {
        return NumberA * NumberB;
    }
}
```

除法运算方式

```java
package com.lance.calculator.operation;

public class DivideOperation extends Operation {
    @Override
    public double getResult() {
        return NumberA / NumberB;
    }
}
```

然后创建工厂类，根据不同的参数创建不同的调用方法

```java
package com.lance.calculator.factory;

import com.lance.calculator.operation.*;

public class OperationFactory {
    public static Operation createOperation(String operate) {
        Operation operation = null;
        switch (operate) {
            case "+":
                operation = new PlusOperation();
                break;
            case "-":
                operation = new MinusOperation();
                break;
            case "*":
                operation = new MultiplyOperation();
                break;
            case "/":
                operation = new DivideOperation();
                break;
        }
        return operation;
    }
}
```

客户端调用具体的运算

```java
package com.lance.calculator;

import com.lance.calculator.factory.OperationFactory;
import com.lance.calculator.operation.Operation;

public class Program {
    public static void main(String[] args) {
        Operation operation = OperationFactory.createOperation("+");
        operation.NumberA = 10;
        operation.NumberB = 5;
        System.out.println("计算结果: " + operation.getResult());
    }
}
```



## 简单工厂模式应用分析

### 简单工厂模式优点

1. 实现创建和使用分离；
2. Client无需知道所创建的ConcreteProduct类名，只需要知道ConcreteProduct所对应的参数；

### 简单工厂模式缺点

1. Factory类集中所有ConcreteProduct的创建逻辑，职责过重，一旦需要添加新的ConcreteProduct，则需要修改Factory逻辑。这样违背了OCP（开放-关闭原则）；
2. 由于使用了static方法，造成Factory无法形成基于继承的结构；

