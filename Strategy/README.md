# 二十三种设计模式 - 策略模式

## 策略模式简介

### 定义

策略模式：属于对象的行为模式。其用意是针对一组算法，将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。

策略模式：使得算法可以在不影响到客户端的情况下发生变化。

策略模式：是对算法的包装，是把使用算法的责任和算法本身分割开来，委派给不同的对象管理。策略模式通常把一个系列的算法包装到一系列的策略类里面，作为一个抽象策略类的子类。用一句话来说，就是：“准备一组算法，并将每一个算法封装起来，使得它们可以互换”。

## 策略模式结构

### 结构图

### 参与者

策略模式参与者：

Context：环境角色，持有一个Strategy的引用。

Strategy：抽象策略角色，这是一个抽象角色，通常由一个接口或抽象类实现。此角色给出所有的具体策略类所需的接口。

ConcreteStrategy：具体策略角色，包装了相关的算法或行为。

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

## 策略模式实现

### 收银软件项目

现在需要给商场做收银软件，收银员根据客户购买产品的单价和数量，向客户打印小票。

这个实现很简单，一个类就可以搞定了

```java
package com.lance.market.basic;

import java.util.Scanner;

public class Cash {
    public String list = "";
    public Double totalPrice = 0.00;

    public void buttonOK() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入单价：");
        String price = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入数量：");
        String num = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入折扣：");
        String discount = scanner.nextLine();

        Double account = Double.parseDouble(price) * Integer.parseInt(num) * Double.parseDouble(discount) / 10;
        list += "单价：" + price + "，数量：" + num + "，折扣：" + discount + "\n";
        totalPrice += account;
    }

    public static void main(String[] args) {
        Cash cash = new Cash();
        boolean flag = true;
        while (flag) {
            cash.buttonOK();
            if (cash.totalPrice > 10) {
                flag = false;
            }
        }
        System.out.println("=============");
        System.out.println("清单：\n" + cash.list);
        System.out.println("总价：" + cash.totalPrice);
    }
}
```

但是，用面向对象的角度思考，这个类将前端输入和业务逻辑混合在一块了，不利于维护，扩展，复用，也不灵活。

假如，现在商场在做活动，所有商品打折，7折，过一段时间，商场又搞活动，所有商品5折，国庆节活动，所有商品满200减50。

按照上面的方式写代码，那么每次都要写一遍，如何将其复用起来，并且每次增加新的活动的时候，又不会影响到原来的活动。

其实我们刚才所说的简单工厂设计模式，也可应用在这里。

首先，有一个工厂类，在这个工厂类里面，根据类型，依赖于不同的现金收费方式。

先写一个抽象的现金收费方式类（可理解为Product）

```java
package com.lance.market.factory.product;

public abstract class CashFee {
    public abstract double acceptCash(double money);
}
```

然后定义现金收费方式的实现类，分别是：正常收费，折扣类，返现类

```java
package com.lance.market.factory.product;

public class NormalCashFee extends CashFee {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
```

```java
package com.lance.market.factory.product;

public class DiscountCashFee extends CashFee {

    private double discount = 0.00;


    public DiscountCashFee(double discount) {
        this.discount = discount / 10 ;
    }

    @Override
    public double acceptCash(double money) {
        return this.discount * money;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
```

```java
package com.lance.market.factory.product;

public class ReturnCashFee extends CashFee {

    private double baseCash;

    private double returnCash;

    public ReturnCashFee(double baseCash, double returnCash) {
        this.baseCash = baseCash;
        this.returnCash = returnCash;
    }

    public double getBaseCash() {
        return baseCash;
    }

    public void setBaseCash(double baseCash) {
        this.baseCash = baseCash;
    }

    public double getReturnCash() {
        return returnCash;
    }

    public void setReturnCash(double returnCash) {
        this.returnCash = returnCash;
    }

    @Override
    public double acceptCash(double money) {
        return money - Math.floor(money / baseCash) * returnCash;
    }
}
```

再定义一个工厂类，用来生产各种各样的现金收费方式

```java
package com.lance.market.factory;

import com.lance.market.factory.product.CashFee;
import com.lance.market.factory.product.DiscountCashFee;
import com.lance.market.factory.product.NormalCashFee;
import com.lance.market.factory.product.ReturnCashFee;

import java.util.Scanner;

public class CashFeeFactory {
    public static CashFee createCashFee(int type, double discount, double basePrice, double returnPrice) {
        CashFee cashFee = null;
        switch (type) {
            case 1:
                cashFee = new NormalCashFee();
                break;
            case 2:
                cashFee = new DiscountCashFee(discount);
                break;
            case 3:
                cashFee = new ReturnCashFee(basePrice, returnPrice);
                break;
        }

        return cashFee;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入单价：");
        String price = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入数量：");
        String num = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入折扣类型（1 无折扣 2 打折 3 满减）：");
        String type = scanner.nextLine();

        double discount = 0.0d;
        double basePrice = 0;
        double returnPrice = 0;
        if ("2".equals(type)) {
            scanner = new Scanner(System.in);
            System.out.println("输入折扣：");
            discount = Double.parseDouble(scanner.nextLine());
        }

        if ("3".equals(type)) {
            scanner = new Scanner(System.in);
            System.out.println("基础金额：");
            basePrice = Double.parseDouble(scanner.nextLine());
            scanner = new Scanner(System.in);
            System.out.println("返还现金：");
            returnPrice = Double.parseDouble(scanner.nextLine());
        }

        Double money = Double.parseDouble(price) * Integer.parseInt(num);
        CashFee cashFee = CashFeeFactory.createCashFee(Integer.parseInt(type), discount, basePrice, returnPrice);
        System.out.println("总价：" + cashFee.acceptCash(money));
    }
}
```

#### 思考：

使用简单工厂设计模式的优缺点：

优点：

1. 业务逻辑和前端展示相互分离开了。业务逻辑的修改，不影响前端代码展示。
2. 每一个业务逻辑单独一个类，修改或者添加一个类，不会影响到其他的类。
3. 使用工厂类封装了业务逻辑类，前端不需要知道到底每种业务怎么实现，只需要知道他的父类即可。

缺点：

1. 如果活动很频繁，经常会搞各种各样的活动，那么业务逻辑就会有很多种，每一次都要增加一个类。
2. 每增加一个类都要修改工厂类，修改会很频繁。

####  小结：

简单工厂设计模式虽然也能解决这个问题，但这个模式只是解决对类的创建问题。

由此我们应该使用**策略模式**对需求做修改。

与简单工厂模式类似，我们都需要先定义一个现金算法的抽象类，以及现金算法的各个实现类。

```java
package com.lance.market.strategy;

public abstract class CashFee {
    public abstract double acceptCash(double money);
}
```

```java
package com.lance.market.factory.product;

public class NormalCashFee extends CashFee {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
```

```java
package com.lance.market.factory.product;

public class DiscountCashFee extends CashFee {

    private double discount = 0.00;


    public DiscountCashFee(double discount) {
        this.discount = discount / 10 ;
    }

    @Override
    public double acceptCash(double money) {
        return this.discount * money;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
```

```java
package com.lance.market.factory.product;

public class ReturnCashFee extends CashFee {

    private double baseCash;

    private double returnCash;

    public ReturnCashFee(double baseCash, double returnCash) {
        this.baseCash = baseCash;
        this.returnCash = returnCash;
    }

    public double getBaseCash() {
        return baseCash;
    }

    public void setBaseCash(double baseCash) {
        this.baseCash = baseCash;
    }

    public double getReturnCash() {
        return returnCash;
    }

    public void setReturnCash(double returnCash) {
        this.returnCash = returnCash;
    }

    @Override
    public double acceptCash(double money) {
        return money - Math.floor(money / baseCash) * returnCash;
    }
}
```

最后，我们来创建上下文类

```java
package com.lance.market.strategy.context;

import com.lance.market.factory.product.CashFee;
import com.lance.market.factory.product.DiscountCashFee;
import com.lance.market.factory.product.NormalCashFee;
import com.lance.market.factory.product.ReturnCashFee;

import java.util.Scanner;

public class CashContext {

    private CashFee cashFee;

    public CashContext(CashFee cashFee) {
        this.cashFee = cashFee;
    }

    public double getResult(double money) {
        return cashFee.acceptCash(money);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入单价：");
        String price = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入数量：");
        String num = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入折扣类型（1 无折扣 2 打折 3 满减）：");
        String type = scanner.nextLine();

        double discount = 0.0d;
        double basePrice = 0;
        double returnPrice = 0;
        if ("2".equals(type)) {
            scanner = new Scanner(System.in);
            System.out.println("输入折扣：");
            discount = Double.parseDouble(scanner.nextLine());
        }

        if ("3".equals(type)) {
            scanner = new Scanner(System.in);
            System.out.println("基础金额：");
            basePrice = Double.parseDouble(scanner.nextLine());
            scanner = new Scanner(System.in);
            System.out.println("返还现金：");
            returnPrice = Double.parseDouble(scanner.nextLine());
        }

        Double money = Double.parseDouble(price) * Integer.parseInt(num);

        CashContext cashContext = null;
        switch (type) {
            case "1":
                cashContext = new CashContext(new NormalCashFee());
                break;
            case "2":
                cashContext = new CashContext(new DiscountCashFee(discount));
                break;
            case "3":
                cashContext = new CashContext(new ReturnCashFee(basePrice, returnPrice));
                break;
        }

        System.out.println("总价：" + cashContext.getResult(money));
    }

}
```

#### 思考：

1. 业务逻辑和前端页面展示分开。
2. 有一个context上下文类，在其内部引用了CashFee类，构造方法定义了具体的实现类。
3. 但是这样操作客户端依然需要switch判断。

这时我们可以将简单工厂模式和策略模式结合起来使用，并且其他的都不用变化，变化的是CashContext。

```java
package com.lance.market.strategy.context;

import com.lance.market.factory.product.CashFee;
import com.lance.market.factory.product.DiscountCashFee;
import com.lance.market.factory.product.NormalCashFee;
import com.lance.market.factory.product.ReturnCashFee;

import java.util.Scanner;

public class CashContext {

    private CashFee cashFee;

    public CashContext(int type, double discount, double basePrice, double returnPrice) {
        switch (type) {
            case 1:
                this.cashFee = new NormalCashFee();
                break;
            case 2:
                this.cashFee = new DiscountCashFee(discount);
                break;
            case 3:
                this.cashFee = new ReturnCashFee(basePrice, returnPrice);
                break;
        }
    }

    public double getResult(double money) {
        return cashFee.acceptCash(money);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入单价：");
        String price = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入数量：");
        String num = scanner.nextLine();

        scanner = new Scanner(System.in);
        System.out.println("输入折扣类型（1 无折扣 2 打折 3 满减）：");
        String type = scanner.nextLine();

        double discount = 0.0d;
        double basePrice = 0;
        double returnPrice = 0;
        if ("2".equals(type)) {
            scanner = new Scanner(System.in);
            System.out.println("输入折扣：");
            discount = Double.parseDouble(scanner.nextLine());
        }

        if ("3".equals(type)) {
            scanner = new Scanner(System.in);
            System.out.println("基础金额：");
            basePrice = Double.parseDouble(scanner.nextLine());
            scanner = new Scanner(System.in);
            System.out.println("返还现金：");
            returnPrice = Double.parseDouble(scanner.nextLine());
        }

        Double money = Double.parseDouble(price) * Integer.parseInt(num);

        CashContext cashContext = new CashContext(Integer.parseInt(type), discount, basePrice, returnPrice);


        System.out.println("总价：" + cashContext.getResult(money));
    }

}
```

现在我们将前端的switch转移到了CashContext的内部，这样，前端只需要传递给我类别信息就可以了。

下面来对比一下：简单工厂设计模式 和 策略模式+简单工厂模式的区别：

```java
CashFee cashFee = CashFeeFactory.createCashFee(Integer.parseInt(type), discount, basePrice, returnPrice);
```

```java
 CashContext cashContext = new CashContext(Integer.parseInt(type), discount, basePrice, returnPrice);
```

对于客户端而言，简单工厂设计模式，客户端需要知道两个类，CashFee和CashFeeFactory；而简单工厂模式+策略模式，客户段只需要知道一个类CashContex即可，降低类耦合性。

### 鸭子项目

#### 模拟鸭子项目

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

#### 项目的新需求

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

#### 用OO原则解决新需求的不足

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

#### 用策略模式来解决新需求

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

#### 重新设计模拟鸭子项目

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

#### 总结策略模式定义

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
2. 由于策略模式把每个具体的策略实现都单独封装成为类，如果备选的策略很多的话，那么对象的数目就会很可观。

## 策略模式注意点

1. 分析项目中变化部分与不变部分
2. 多用组合少用继承；用行为类组合，而不是行为的继承。更有弹性。
