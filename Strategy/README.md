# 二十三种设计模式 - 策略模式

## 策略模式原理

在阎宏博士的《JAVA与模式》一书中开头是这样描述策略（Strategy）模式的：

　　**策略模式属于对象的行为模式。其用意是针对一组算法，将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。策略模式使得算法可以在不影响到客户端的情况下发生变化。**

策略模式是对算法的包装，是把使用算法的责任和算法本身分割开来，委派给不同的对象管理。策略模式通常把一个系列的算法包装到一系列的策略类里面，作为一个抽象策略类的子类。用一句话来说，就是：“准备一组算法，并将每一个算法封装起来，使得它们可以互换”。下面就以一个示意性的实现讲解策略模式实例的结构。

这个模式涉及到三个角色：

- **环境(Context)角色：**持有一个Strategy的引用。

- **抽象策略(Strategy)角色：**这是一个抽象角色，通常由一个接口或抽象类实现。此角色给出所有的具体策略类所需的接口。

- **具体策略(ConcreteStrategy)角色：**包装了相关的算法或行为。

### 环境角色类

```java
public class Context {
    //持有一个具体策略的对象
    private Strategy strategy;
    /**
     * 构造函数，传入一个具体策略对象
     * @param strategy    具体策略对象
     */
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    /**
     * 策略方法
     */
    public void contextInterface(){
        
        strategy.strategyInterface();
    }
    
}
```

### 抽象策略类

```java
public interface Strategy {
    /**
     * 策略方法
     */
    public void strategyInterface();
}
```

### 具体策略类

```java
public class ConcreteStrategyA implements Strategy {

    @Override
    public void strategyInterface() {
        //相关的业务
    }

}
```

```java
public class ConcreteStrategyB implements Strategy {

    @Override
    public void strategyInterface() {
        //相关的业务
    }

}
```

```java
public class ConcreteStrategyC implements Strategy {

    @Override
    public void strategyInterface() {
        //相关的业务
    }

}
```

## 使用场景

### 模拟鸭子项目

1. 从项目“模拟鸭子游戏”开始
2. 从OO的角度设计这个项目，鸭子超类，扩展超类：

```java
package com.java.lance.stimulateduck.oo;

public abstract class Duck {
    public Duck() {

    }

    public void Quack() {
        System.out.println("~~gaga~~");
    }

    public abstract void display();

    public void swim() {
        System.out.println("~~i'm swim~~");
    }
}

```

3. GreenHeadDuck继承Duck：

```java
package com.java.lance.stimulateduck.oo;

public class GreenHeadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("**GreenHead**");
    }
}
```

4. RedHeadDuck继承Duck：

```java
package com.java.lance.stimulateduck.oo;

public class RedHeadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("**RedHead**");
    }
}
```

5. 测试

```java
package com.java.lance.stimulateduck.oo;

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
    }
}
```

6. 输出

```
**GreenHead**
~~gaga~~
~~i'm swim~~
**RedHead**
~~gaga~~
~~i'm swim~~

Process finished with exit code 0
```

### 项目的新需求

1. 于此同时为了应对新的需求，看看这个设计的可扩展性
   - 添加会飞的鸭子
2. OO思维里的继承方式解决方案是：

```java
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
```

在超类里添加飞方法，这时所有继承超类的子类都拥有飞方法。但是并不是所有的鸭子都会飞。

所以：

**继承的问题：对类的局部改动，尤其超类的局部改动，会影响其他部分。影响会有溢出效应。**

### 用OO原则解决新需求的不足

1. 继续尝试用OO原理来解决，覆盖：

```java
package com.java.lance.stimulateduck.oo;

public class GreenHeadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("**GreenHead**");
    }

    public void fly() {
        System.out.println("~~no fly~~");
    }
}
```

这样的话，如果有几十种方法，这时工作量非常大，子类需要覆盖超类的方法。

所以，超类挖的一个坑，每个子类都要来填，增加工作量，复杂度O(N^2)。不是好的设计方式。

需要新的设计方式，应对项目的扩展性，降低复杂度：

1）需要分析项目变化与不变的部分，提取变化的部分，抽象成接口+实现；

2）鸭子哪些功能是会根据新需求变化的？叫声，飞行。。。

### 用策略模式来解决新需求

1. 接口：

```java
package com.java.lance.stimulateduck.flybehavior;

public interface FlyBehavior {
    void fly();
}
```

```java
package com.java.lance.stimulateduck.quackbehavior;

public interface QuackBehavior {
    void quack();
}
```

好处：新增行为简单，行为类更好的复用，组合更方便。既有继承带来的复用好处，没有挖坑。

### 重新设计模拟鸭子项目

1. 重新设计的鸭子项目：

```java
package com.java.lance.stimulateduck.duck;

import FlyBehavior;
import QuackBehavior;

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
```

### 总结策略模式定义

1. 绿头鸭，石头鸭：

```java
package com.java.lance.stimulateduck.duck;

import GoodFlyBehavior;
import GaGaQuackBehavior;

public class GreenHeadDuck extends Duck {

    public GreenHeadDuck(){
        flyBehavior = new GoodFlyBehavior();
        quackBehavior = new GaGaQuackBehavior();
    }
    @Override
    public void display() {
		System.out.println("~~green head~~");
    }
}
```

2. 策略模式：分别封装行为接口，实现算法族，超类里放行为接口对象，在子类里具体设定行为对象。原则就是：分离变化部分，封装接口，基于接口编程各种功能。此模式让行为算法的变化独立于算法的使用者。

## 认识策略模式

　　**策略模式的重心**

　　策略模式的重心不是如何实现算法，而是如何组织、调用这些算法，从而让程序结构更灵活，具有更好的维护性和扩展性。

　　**算法的平等性**

　　策略模式一个很大的特点就是各个策略算法的平等性。对于一系列具体的策略算法，大家的地位是完全一样的，正因为这个平等性，才能实现算法之间可以相互替换。所有的策略算法在实现上也是相互独立的，相互之间是没有依赖的。

　　所以可以这样描述这一系列策略算法：策略算法是相同行为的不同实现。

　　**运行时策略的唯一性**

　　运行期间，策略模式在每一个时刻只能使用一个具体的策略实现对象，虽然可以动态地在不同的策略实现中切换，但是同时只能使用一个。

　　**公有的行为**

　　经常见到的是，所有的具体策略类都有一些公有的行为。这时候，就应当把这些公有的行为放到共同的抽象策略角色Strategy类里面。当然这时候抽象策略角色必须要用Java抽象类实现，而不能使用接口。

　　这其实也是典型的将代码向继承等级结构的上方集中的标准做法。

## 策略模式的优点

1. 策略模式提供了管理相关的算法族的办法。策略类的等级结构定义了一个算法或行为族。恰当使用继承可以把公共的代码移到父类里面，从而避免代码重复。

2. 使用策略模式可以避免使用多重条件(if-else)语句。多重条件语句不易维护，它把采取哪一种算法或采取哪一种行为的逻辑与算法或行为的逻辑混合在一起，统统列在一个多重条件语句里面，比使用继承的办法还要原始和落后。

## 策略模式的缺点

1. 客户端必须知道所有的策略类，并自行决定使用哪一个策略类。这就意味着客户端必须理解这些算法的区别，以便适时选择恰当的算法类。换言之，策略模式只适用于客户端知道算法或行为的情况。
   1. 由于策略模式把每个具体的策略实现都单独封装成为类，如果备选的策略很多的话，那么对象的数目就会很可观。

## 策略模式注意点

1. 分析项目中变化部分与不变部分
2. 多用组合少用继承；用行为类组合，而不是行为的继承。更有弹性。
