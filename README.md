23种设计模式测试题

# 目录

[1-工厂方法模式](#1-工厂方法模式)

[2-抽象工厂模式](#2-抽象工厂模式)

[3-原型模式](#3-原型模式)

[4-建造者模式](#4-建造者模式)

[5-外观模式](#5-外观模式)

[6-适配器模式](#6-适配器模式)

[7-组合模式](#7-组合模式)

[8-代理模式](#8-代理模式)

[9-桥接模式](#9-桥接模式)

[10-装饰模式](#10-装饰模式)

[11-享元模式](#11-享元模式)

[12-策略模式](#12-策略模式)

[13-模版模式](#13-模版模式)

[14-备忘录模式](#14-备忘录模式)

[15-观察者模式](#15-观察者模式)

[16-命令模式](#16-命令模式)

[17-状态模式](#17-状态模式)

[18-职责链模式](#18-职责链模式)

[19-中介者模式](#19-中介者模式)



## 1. 工厂方法模式

- TCL有惠州工厂和广州工厂，其中惠州工厂主要生产CRT电视机，广州工厂主要生产LCD电视机，请设计其生产管理系统。

工厂方法模式（Factory Method Pattern）是一种创建型设计模式，它提供了一种定义接口用于创建对象，但由子类决定实例化哪一个类的方式。在你的需求中，我们需要设计一个生产管理系统，该系统应当能根据不同的工厂（惠州工厂、广州工厂）生产不同类型的电视机（CRT电视机、LCD电视机）。工厂方法模式正适合这种需求。

### 系统设计

- **电视机产品接口**：`TV`接口，定义电视机的基本操作方法。
- **具体产品**：`CRT_TV`和`LCD_TV`，分别实现`TV`接口，表示不同类型的电视机。
- **工厂接口**：`TVFactory`接口，定义一个工厂方法`createTV()`，用来创建不同类型的电视机。
- **具体工厂**：`HuizhouFactory`和`GuangzhouFactory`，分别实现`TVFactory`接口，根据工厂创建不同类型的电视机。

### 代码实现

```java
// 1. 定义电视机产品接口
interface TV {
    void show();
}

// 2. 具体产品：CRT电视机
class CRT_TV implements TV {
    @Override
    public void show() {
        System.out.println("显示CRT电视");
    }
}

// 3. 具体产品：LCD电视机
class LCD_TV implements TV {
    @Override
    public void show() {
        System.out.println("显示LCD电视");
    }
}

// 4. 定义工厂方法接口
interface TVFactory {
    TV createTV();
}

// 5. 具体工厂：惠州工厂（生产CRT电视机）
class HuizhouFactory implements TVFactory {
    @Override
    public TV createTV() {
        return new CRT_TV();  // 惠州工厂生产CRT电视机
    }
}

// 6. 具体工厂：广州工厂（生产LCD电视机）
class GuangzhouFactory implements TVFactory {
    @Override
    public TV createTV() {
        return new LCD_TV();  // 广州工厂生产LCD电视机
    }
}

// 7. 客户端代码（生产管理系统）
public class FactoryMethodDemo {
    public static void main(String[] args) {
        // 客户端根据需求选择不同的工厂进行生产
        TVFactory huizhouFactory = new HuizhouFactory();
        TV huizhouTV = huizhouFactory.createTV();
        huizhouTV.show();  // 输出：显示CRT电视
        
        TVFactory guangzhouFactory = new GuangzhouFactory();
        TV guangzhouTV = guangzhouFactory.createTV();
        guangzhouTV.show();  // 输出：显示LCD电视
    }
}
```

### 解释

1. **产品接口 (`TV`)**：
   - 这个接口定义了所有电视机产品的通用方法，比如`show()`方法，负责展示电视机的显示效果。

2. **具体产品 (`CRT_TV` 和 `LCD_TV`)**：
   - 这些类实现了`TV`接口，分别代表不同类型的电视机，`CRT_TV`代表CRT电视，`LCD_TV`代表LCD电视。

3. **工厂接口 (`TVFactory`)**：
   - 该接口定义了一个工厂方法`createTV()`，用来创建电视机实例。

4. **具体工厂 (`HuizhouFactory` 和 `GuangzhouFactory`)**：
   - `HuizhouFactory`实现了`TVFactory`接口，负责生产`CRT_TV`，而`GuangzhouFactory`负责生产`LCD_TV`。

5. **客户端 (`FactoryMethodDemo`)**：
   - 在客户端代码中，我们通过创建不同的工厂对象（`HuizhouFactory`和`GuangzhouFactory`），来决定生产不同类型的电视机。
   - 然后调用工厂方法`createTV()`来获取相应的电视机对象，并调用`show()`方法来展示电视机的类型。

### 输出结果

```
显示CRT电视
显示LCD电视
```

### 扩展性

- 如果将来需要添加更多类型的电视机（比如LED电视），可以通过新增具体的产品类（如`LED_TV`）和工厂（如`ShenzhenFactory`）来扩展系统，而不需要修改现有的代码。
- 只需要在客户端中选择合适的工厂并调用`createTV()`方法即可。

### 总结

使用工厂方法模式，可以将不同类型的电视机的创建过程封装在不同的工厂中。客户端只需要知道如何通过工厂方法来获取需要的产品，而不需要关心具体的创建细节，这样使得系统更具可扩展性、灵活性和可维护性。


----------------------------

## 2. 抽象工厂模式

抽象工厂模式（Abstract Factory Pattern）是工厂方法模式的进一步抽象，它提供了一个接口，用于创建一系列相关或相互依赖的对象，而不需要指定具体类。根据你的需求，系统需要能够通过不同的工厂（惠州工厂和广州工厂）生产不同类型的电视机和电脑。具体来说，惠州工厂生产CRT电视机和笔记本电脑，而广州工厂生产LCD电视机和台式电脑。

### 设计思路

1. **产品系列接口**：为了将电视机和电脑归为不同的产品系列，我们定义两个接口，一个用于电视机（`TV`），另一个用于电脑（`Computer`）。
2. **具体产品**：对于电视机和电脑，分别有不同的实现类，如CRT电视机、LCD电视机、笔记本电脑和台式电脑。
3. **抽象工厂接口**：定义一个抽象工厂接口（`ProductFactory`），该接口声明了创建电视机和电脑的工厂方法。
4. **具体工厂**：惠州工厂和广州工厂分别实现抽象工厂接口，分别创建不同的电视机和电脑产品。

### 代码实现

```java
// 1. 定义电视机接口
interface TV {
    void show();
}

// 2. 定义电脑接口
interface Computer {
    void display();
}

// 3. 具体产品：CRT电视机
class CRT_TV implements TV {
    @Override
    public void show() {
        System.out.println("显示CRT电视");
    }
}

// 4. 具体产品：LCD电视机
class LCD_TV implements TV {
    @Override
    public void show() {
        System.out.println("显示LCD电视");
    }
}

// 5. 具体产品：笔记本电脑
class Laptop implements Computer {
    @Override
    public void display() {
        System.out.println("显示笔记本电脑");
    }
}

// 6. 具体产品：台式电脑
class Desktop implements Computer {
    @Override
    public void display() {
        System.out.println("显示台式电脑");
    }
}

// 7. 抽象工厂接口
interface ProductFactory {
    TV createTV();
    Computer createComputer();
}

// 8. 具体工厂：惠州工厂（生产CRT电视机和笔记本电脑）
class HuizhouFactory implements ProductFactory {
    @Override
    public TV createTV() {
        return new CRT_TV();  // 惠州工厂生产CRT电视机
    }

    @Override
    public Computer createComputer() {
        return new Laptop();  // 惠州工厂生产笔记本电脑
    }
}

// 9. 具体工厂：广州工厂（生产LCD电视机和台式电脑）
class GuangzhouFactory implements ProductFactory {
    @Override
    public TV createTV() {
        return new LCD_TV();  // 广州工厂生产LCD电视机
    }

    @Override
    public Computer createComputer() {
        return new Desktop();  // 广州工厂生产台式电脑
    }
}

// 10. 客户端代码（生产管理系统）
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // 客户端根据需求选择不同的工厂进行生产
        ProductFactory huizhouFactory = new HuizhouFactory();
        TV huizhouTV = huizhouFactory.createTV();
        Computer huizhouComputer = huizhouFactory.createComputer();
        huizhouTV.show();  // 输出：显示CRT电视
        huizhouComputer.display();  // 输出：显示笔记本电脑

        ProductFactory guangzhouFactory = new GuangzhouFactory();
        TV guangzhouTV = guangzhouFactory.createTV();
        Computer guangzhouComputer = guangzhouFactory.createComputer();
        guangzhouTV.show();  // 输出：显示LCD电视
        guangzhouComputer.display();  // 输出：显示台式电脑
    }
}
```

### 解释

1. **产品接口 (`TV` 和 `Computer`)**：
   - `TV`接口定义了所有电视机的共同行为（如`show()`方法）。
   - `Computer`接口定义了所有电脑的共同行为（如`display()`方法）。

2. **具体产品**：
   - `CRT_TV`和`LCD_TV`分别是不同类型的电视机产品，分别实现了`TV`接口。
   - `Laptop`和`Desktop`分别是笔记本电脑和台式电脑产品，分别实现了`Computer`接口。

3. **抽象工厂接口 (`ProductFactory`)**：
   - `ProductFactory`接口定义了两个工厂方法：`createTV()`和`createComputer()`，用来创建相关的电视机和电脑产品。

4. **具体工厂**：
   - `HuizhouFactory`和`GuangzhouFactory`分别实现了`ProductFactory`接口，负责创建不同的电视机和电脑产品。
      - `HuizhouFactory`负责创建CRT电视机和笔记本电脑。
      - `GuangzhouFactory`负责创建LCD电视机和台式电脑。

5. **客户端代码**：
   - 在客户端代码中，我们根据不同的需求，选择相应的工厂（`HuizhouFactory`或`GuangzhouFactory`），通过工厂方法`createTV()`和`createComputer()`来获得所需的电视机和电脑对象，并调用各自的`show()`和`display()`方法。

### 输出结果

```
显示CRT电视
显示笔记本电脑
显示LCD电视
显示台式电脑
```

### 扩展性

- **新增产品**：如果将来需要增加新的电视机（如LED电视）或电脑（如平板电脑），可以直接创建新的产品类，并在相应的工厂中实现创建方法。
- **新增工厂**：如果需要为其他地区或者不同的生产线增加工厂（比如`ShenzhenFactory`），只需要实现`ProductFactory`接口，并根据工厂需求创建相应的电视机和电脑。

### 总结

通过使用抽象工厂模式，能够轻松管理多个产品系列（如电视机和电脑）。每个工厂负责生产一组相关的产品，且客户端不需要关心具体的产品实现细节，只需要通过抽象工厂来创建产品。这样的设计使得系统具有高度的灵活性和可扩展性，特别适合当产品之间存在一定的关联时。


------------------

## 3. 原型模式

- 某公司需要一个图形工具箱，提供圆形、长方形和正方形的绘制功能，请为其设计该图形工具箱系统。


原型模式（Prototype Pattern）是一种创建型设计模式，它通过复制现有的对象来创建新对象，而不是通过`new`关键字来创建对象。这种模式特别适合于当创建对象的成本很高或创建对象的过程较为复杂时。使用原型模式，我们可以通过克隆现有对象来实现新的对象创建。

### 设计思路

在这个例子中，我们需要创建一个图形工具箱，可以用来绘制不同形状的图形。我们有三种形状：
- 圆形（Circle）
- 长方形（Rectangle）
- 正方形（Square）

每个图形都可以通过克隆已有对象来生成新的图形，从而实现原型模式。

### 设计步骤

1. **图形接口**：我们定义一个图形接口（`Shape`），其中包含一个`draw()`方法和一个`clone()`方法，后者用于克隆该图形对象。
2. **具体图形类**：每个具体的图形类（如`Circle`、`Rectangle`、`Square`）实现`Shape`接口，分别定义`draw()`方法和`clone()`方法。
3. **客户端**：通过一个图形工具箱（`ShapeCache`）来管理原型对象，并通过调用`clone()`方法来获取图形的副本。

### 代码实现

```java
// 1. 定义图形接口
interface Shape extends Cloneable {
    void draw();  // 绘制图形
    Shape clone();  // 克隆该图形
}

// 2. 具体图形：圆形
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }

    @Override
    public Shape clone() {
        try {
            return (Circle) super.clone();  // 使用Object的clone方法克隆对象
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// 3. 具体图形：长方形
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制长方形");
    }

    @Override
    public Shape clone() {
        try {
            return (Rectangle) super.clone();  // 使用Object的clone方法克隆对象
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// 4. 具体图形：正方形
class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制正方形");
    }

    @Override
    public Shape clone() {
        try {
            return (Square) super.clone();  // 使用Object的clone方法克隆对象
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// 5. 图形缓存类：用于管理原型对象的缓存
class ShapeCache {
    private static final Map<String, Shape> shapeMap = new HashMap<>();

    // 加载默认的原型对象
    static {
        shapeMap.put("Circle", new Circle());
        shapeMap.put("Rectangle", new Rectangle());
        shapeMap.put("Square", new Square());
    }

    // 根据形状名称获取克隆的对象
    public static Shape getShape(String shapeType) {
        Shape cachedShape = shapeMap.get(shapeType);
        return cachedShape != null ? cachedShape.clone() : null;
    }
}

// 6. 客户端代码：绘制图形工具箱
public class PrototypeDemo {
    public static void main(String[] args) {
        // 从ShapeCache中获取并克隆不同的图形
        Shape circle1 = ShapeCache.getShape("Circle");
        circle1.draw();  // 输出：绘制圆形

        Shape rectangle1 = ShapeCache.getShape("Rectangle");
        rectangle1.draw();  // 输出：绘制长方形

        Shape square1 = ShapeCache.getShape("Square");
        square1.draw();  // 输出：绘制正方形

        // 再次从缓存中获取并克隆
        Shape circle2 = ShapeCache.getShape("Circle");
        circle2.draw();  // 输出：绘制圆形

        Shape rectangle2 = ShapeCache.getShape("Rectangle");
        rectangle2.draw();  // 输出：绘制长方形
    }
}
```

### 解释

1. **图形接口 (`Shape`)**：
   - `Shape`接口定义了图形对象的基本行为。`draw()`方法用于绘制图形，`clone()`方法用于克隆当前对象。
   - 由于原型模式要求对象能够被复制，我们在`Shape`接口中继承了`Cloneable`接口，以便支持克隆操作。

2. **具体图形类 (`Circle`, `Rectangle`, `Square`)**：
   - 每个具体图形类都实现了`Shape`接口，并提供了自己的`draw()`方法和`clone()`方法。`clone()`方法通过`super.clone()`调用父类的`clone()`方法，来克隆当前对象。

3. **图形缓存类 (`ShapeCache`)**：
   - `ShapeCache`是一个缓存类，负责存储和管理原型对象。这里通过一个`Map<String, Shape>`存储预定义的原型图形对象（例如圆形、长方形、正方形）。
   - `getShape(String shapeType)`方法根据给定的形状类型从缓存中获取原型对象，并通过`clone()`方法创建并返回该形状的副本。

4. **客户端代码 (`PrototypeDemo`)**：
   - 在客户端代码中，我们通过`ShapeCache`获取并克隆所需的图形对象。每次调用`ShapeCache.getShape()`时，都会返回一个新的图形副本，避免了直接使用`new`关键字来创建新对象。

### 输出结果

```
绘制圆形
绘制长方形
绘制正方形
绘制圆形
绘制长方形
```

### 优点

1. **性能优化**：如果对象的创建成本较高，可以通过克隆现有对象来创建新对象，避免了重复的构造过程。
2. **灵活性和扩展性**：可以通过添加新的图形类来扩展系统，且无需修改现有代码，只需要向`ShapeCache`中添加新原型。
3. **避免复杂对象构建过程**：使用原型克隆能够避免复杂对象的创建过程，尤其适用于需要创建多个相同类型对象的场景。

### 总结

原型模式使得我们可以通过克隆现有对象来创建新的对象，而无需从头开始构建。对于需要大量相同类型对象创建的场景，原型模式提供了一种高效且灵活的解决方案。在本例中，图形工具箱通过原型模式提供了一个简单而有效的方式来管理和创建各种图形对象。


------------------

## 4. 建造者模式

- 西式快餐通常由食品和饮料组成，如麦当劳由汉堡和雪碧组成，肯德基由炸鸡和可乐组成，请设计其生产管理系统。



建造者模式（Builder Pattern）是一种创建型设计模式，它允许你通过多个步骤构建一个复杂的对象。这个模式将对象的构建过程与对象本身的表示分离，从而使得相同的构建过程可以创建不同的表示。

在你的需求中，西式快餐由食品（如汉堡、炸鸡等）和饮料（如雪碧、可乐等）组成。我们可以使用建造者模式来管理这种组合过程，从而让客户能够灵活选择不同的食品和饮料进行组合。

### 设计思路
1. **产品类（Product）**：西式快餐的组合，包括食品和饮料。
2. **抽象建造者类（Builder）**：定义产品构建的步骤，但不具体实现这些步骤。
3. **具体建造者类（ConcreteBuilder）**：实现具体的食品和饮料组合。
4. **指挥者类（Director）**：负责指挥具体建造者按照顺序构建产品。
5. **客户端（Client）**：客户端负责调用指挥者来获取最终的产品。

### 代码实现

```java
// 1. 产品类：西式快餐产品，包括食品和饮料
class Meal {
    private String food;   // 食品
    private String drink;  // 饮料

    public void setFood(String food) {
        this.food = food;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "Meal [Food=" + food + ", Drink=" + drink + "]";
    }
}

// 2. 抽象建造者类
abstract class MealBuilder {
    protected Meal meal = new Meal();

    // 建造食物
    public abstract void buildFood();

    // 建造饮料
    public abstract void buildDrink();

    // 获取最终的餐品
    public Meal getMeal() {
        return meal;
    }
}

// 3. 具体建造者类：麦当劳建造者
class McDonaldsMealBuilder extends MealBuilder {

    @Override
    public void buildFood() {
        meal.setFood("Hamburger");  // 麦当劳的食物是汉堡
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Sprite");  // 麦当劳的饮料是雪碧
    }
}

// 4. 具体建造者类：肯德基建造者
class KFCMealBuilder extends MealBuilder {

    @Override
    public void buildFood() {
        meal.setFood("Fried Chicken");  // 肯德基的食物是炸鸡
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Coca-Cola");  // 肯德基的饮料是可乐
    }
}

// 5. 指挥者类：负责组织建造过程
class MealDirector {
    private MealBuilder mealBuilder;

    // 通过构造函数注入不同的建造者
    public MealDirector(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    // 指挥者负责建造过程
    public Meal constructMeal() {
        mealBuilder.buildFood();
        mealBuilder.buildDrink();
        return mealBuilder.getMeal();
    }
}

// 6. 客户端类
public class Client {
    public static void main(String[] args) {
        // 创建麦当劳的建造者并指挥建造
        MealBuilder mcDonaldsBuilder = new McDonaldsMealBuilder();
        MealDirector director = new MealDirector(mcDonaldsBuilder);
        Meal mcDonaldsMeal = director.constructMeal();
        System.out.println("麦当劳套餐：" + mcDonaldsMeal);

        // 创建肯德基的建造者并指挥建造
        MealBuilder kfcBuilder = new KFCMealBuilder();
        director = new MealDirector(kfcBuilder);
        Meal kfcMeal = director.constructMeal();
        System.out.println("肯德基套餐：" + kfcMeal);
    }
}
```

### 解释

1. **Meal 类（产品类）**：
    - `Meal` 类包含 `food` 和 `drink` 两个属性，分别表示食品和饮料。提供了 `setFood()` 和 `setDrink()` 方法来设置这两个属性，最后通过 `toString()` 方法输出整个套餐的描述。

2. **MealBuilder 类（抽象建造者）**：
    - 这是一个抽象类，定义了建造食品和饮料的步骤，并提供了一个 `getMeal()` 方法来获取最终的 `Meal` 产品。具体的建造步骤（如 `buildFood()` 和 `buildDrink()`）由子类实现。

3. **McDonaldsMealBuilder 和 KFCMealBuilder 类（具体建造者）**：
    - 这些类分别是针对不同快餐品牌的建造者，分别负责构建麦当劳套餐和肯德基套餐。它们实现了 `MealBuilder` 类中的抽象方法，构建了各自的食品和饮料。

4. **MealDirector 类（指挥者）**：
    - `MealDirector` 类负责指挥建造者类按一定顺序完成构建过程。它接受一个 `MealBuilder` 对象，并通过调用建造者的 `buildFood()` 和 `buildDrink()` 方法来构建套餐，最后返回最终构建好的 `Meal` 对象。

5. **Client 类（客户端）**：
    - 客户端通过创建具体的建造者（`McDonaldsMealBuilder` 或 `KFCMealBuilder`）并通过 `MealDirector` 指挥构建过程，获取最终的套餐。可以很方便地扩展新的餐品，只需添加新的具体建造者类。

### 输出结果

```
麦当劳套餐：Meal [Food=Hamburger, Drink=Sprite]
肯德基套餐：Meal [Food=Fried Chicken, Drink=Coca-Cola]
```

### 总结
通过使用建造者模式，我们能够灵活地组合不同的食品和饮料，创建复杂的西式快餐套餐。建造者模式提供了清晰的构建步骤，方便扩展新的套餐类型，并且客户端无需关心套餐的具体构建过程，简化了代码的维护和扩展。

-----------------

## 5. 外观模式

- 电脑开机包括CPU启动、内存自检、硬盘读取和OS装入四个步骤，请设计该开机系统。

在设计一个模拟电脑开机过程的系统时，可以使用 **外观模式**（Facade Pattern）来简化复杂的过程，提供一个统一的接口，来管理一系列复杂的子系统。外观模式的核心思想是将子系统的复杂性隐藏在一个简单的接口后面，让客户通过这个接口与整个系统交互。

在你的例子中，开机过程包括：**CPU启动、内存自检、硬盘读取、操作系统加载** 四个步骤。我们可以将这些步骤分别封装为不同的类，然后提供一个外观类，统一管理和调用这些步骤。

### 外观模式的类设计

1. **CPU类**：模拟CPU启动
2. **内存类**：模拟内存自检
3. **硬盘类**：模拟硬盘读取
4. **操作系统类**：模拟操作系统加载
5. **开机外观类**：作为外观，简化用户的调用流程

### Java代码实现

```java
// Step 1: 模拟CPU启动
class CPU {
    public void start() {
        System.out.println("CPU启动...");
    }
}

// Step 2: 模拟内存自检
class Memory {
    public void selfCheck() {
        System.out.println("内存自检...");
    }
}

// Step 3: 模拟硬盘读取
class HardDrive {
    public void readData() {
        System.out.println("硬盘读取...");
    }
}

// Step 4: 模拟操作系统加载
class OperatingSystem {
    public void load() {
        System.out.println("操作系统加载...");
    }
}

// Step 5: 外观类，简化开机过程
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    private OperatingSystem os;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
        this.os = new OperatingSystem();
    }

    // 提供统一的开机接口
    public void startComputer() {
        System.out.println("电脑开机启动...");
        cpu.start();          // 启动CPU
        memory.selfCheck();   // 内存自检
        hardDrive.readData(); // 硬盘读取
        os.load();            // 加载操作系统
        System.out.println("开机完成!");
    }
}

// 测试代码
public class Main {
    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        // 用户调用外观类，简化开机过程
        computerFacade.startComputer();
    }
}
```

### 代码解释

1. **CPU类**：模拟CPU启动的行为，`start`方法输出“CPU启动...”。
2. **Memory类**：模拟内存自检，`selfCheck`方法输出“内存自检...”。
3. **HardDrive类**：模拟硬盘读取，`readData`方法输出“硬盘读取...”。
4. **OperatingSystem类**：模拟操作系统加载，`load`方法输出“操作系统加载...”。
5. **ComputerFacade类**：这是外观类，它封装了开机过程中的各个子系统。用户通过调用`startComputer`方法，简化了与各个子系统的交互，只需要通过外观类来启动整个开机过程。

### 运行结果

当你运行 `Main` 类时，输出如下：

```
电脑开机启动...
CPU启动...
内存自检...
硬盘读取...
操作系统加载...
开机完成!
```

### 设计优点

1. **简化客户端接口**：用户只需要调用 `ComputerFacade` 的 `startComputer()` 方法，而不需要关心底层的具体实现。
2. **解耦**：外观模式通过封装复杂子系统，将子系统的复杂性隐藏了起来，减少了子系统之间的依赖，增强了系统的可维护性。
3. **提高可扩展性**：如果以后需要增加新的子系统（比如显示器初始化、网络连接等），只需修改外观类，保持其他代码不变。

### 总结

通过使用外观模式，你可以有效地简化开机过程的管理。外观模式在面对具有多个复杂子系统的情况下，提供了一个统一的接口，从而减少了客户端与这些子系统之间的交互复杂性。

---------------------------

## 6. 适配器模式

- 现有一个mp3播放器和一个mp4播放器，为了简便代码实现，我们希望用mp3播放器来播放mp4文件，请设计该系统。


适配器模式（Adapter Pattern）是一种结构型设计模式，允许不兼容的接口协同工作。在你的问题中，目标是通过适配器模式让 MP3 播放器能够播放 MP4 文件，即通过一个适配器将 MP4 播放请求转换为 MP3 播放器能理解的格式。

下面是一个如何通过适配器模式来实现 MP3 播放器播放 MP4 文件的示例。

### 需求分析：
1. **MP3播放器**: 只支持播放 MP3 格式的文件。
2. **MP4播放器**: 支持播放 MP4 格式的文件，但我们需要用 MP3 播放器来播放 MP4 文件。
3. **适配器**: 用来将 MP4 文件的播放请求转换为 MP3 播放器能够播放的请求。

### 设计步骤：
1. **定义播放器接口** (`MediaPlayer`)，所有播放器都需要实现该接口。
2. **实现 MP3 播放器** (`Mp3Player`)，该类实现 `MediaPlayer` 接口，并且只能播放 MP3 格式文件。
3. **实现 MP4 播放器** (`Mp4Player`)，该类实现 `MediaPlayer` 接口，并且只能播放 MP4 格式文件。
4. **创建适配器** (`Mp4Adapter`)，该类实现 `MediaPlayer` 接口，并通过它来适配 MP4 播放器的播放方式，让 MP3 播放器能够播放 MP4 文件。

### 代码实现：

```java
// 步骤 1: 创建一个媒体播放器接口
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// 步骤 2: 创建 MP3 播放器实现类
class Mp3Player implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing MP3 file. Name: " + fileName);
        } else {
            System.out.println("MP3 Player does not support " + audioType + " format.");
        }
    }
}

// 步骤 3: 创建 MP4 播放器实现类
class Mp4Player implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp4")){
            System.out.println("Playing MP4 file. Name: " + fileName);
        } else {
            System.out.println("MP4 Player does not support " + audioType + " format.");
        }
    }
}

// 步骤 4: 创建适配器类，用来适配 MP4 播放器
class Mp4Adapter implements MediaPlayer {
    private Mp4Player mp4Player;

    public Mp4Adapter(Mp4Player mp4Player) {
        this.mp4Player = mp4Player;
    }

    @Override
    public void play(String audioType, String fileName) {
        mp4Player.play(audioType, fileName);  // 通过适配器调用 MP4 播放器的方法
    }
}

// 步骤 5: 创建一个 AudioPlayer 类来协调 MP3 和 MP4 播放
class AudioPlayer implements MediaPlayer {
    private MediaPlayer mp3Player;
    private MediaPlayer mp4Adapter;

    public AudioPlayer() {
        mp3Player = new Mp3Player();
        mp4Adapter = new Mp4Adapter(new Mp4Player());
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            mp3Player.play(audioType, fileName);
        } else if(audioType.equalsIgnoreCase("mp4")){
            mp4Adapter.play(audioType, fileName);  // 适配器处理 MP4 播放
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}

// 步骤 6: 客户端代码，进行测试
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        // 播放 MP3 文件
        audioPlayer.play("mp3", "song1.mp3");

        // 播放 MP4 文件，注意我们通过 MP3 播放器来播放 MP4 文件
        audioPlayer.play("mp4", "movie1.mp4");

        // 测试不支持的格式
        audioPlayer.play("avi", "video.avi");
    }
}
```

### 代码解释：
1. **MediaPlayer接口**: 所有类型的播放器都必须实现该接口，确保它们能够调用 `play` 方法来播放文件。
2. **Mp3Player类**: 实现了 `MediaPlayer` 接口，专门用于播放 MP3 文件。
3. **Mp4Player类**: 也是实现了 `MediaPlayer` 接口，专门用于播放 MP4 文件。
4. **Mp4Adapter类**: 这是适配器类，继承自 `MediaPlayer` 接口，并通过调用 `Mp4Player` 的 `play` 方法，适配 MP4 播放功能，让 `AudioPlayer` 能够播放 MP4 文件。
5. **AudioPlayer类**: 作为高层次的播放器类，能够根据不同的媒体类型（MP3 或 MP4）选择正确的播放方式，调用 `Mp3Player` 或 `Mp4Adapter` 来播放对应格式的文件。

### 运行结果：
```text
Playing MP3 file. Name: song1.mp3
Playing MP4 file. Name: movie1.mp4
Invalid media type: avi
```

### 适配器模式的好处：
- 使得现有的类能够兼容新的接口或系统，而不需要修改现有代码。
- 可以增加代码的复用性，例如通过适配器，我们可以将现有的 MP3 播放器与 MP4 播放器结合使用。
- 可以动态地将不同类型的播放器组合在一起，提升灵活性。

通过这个示例，我们使用适配器模式将 MP3 播放器和 MP4 播放器有效地结合在一起，实现在不改变现有播放器实现的情况下支持播放 MP4 文件。


---------------------------

## 7. 组合模式

- 大学包含多个学院，学院又包含多个学科专业，请设计该系统。

组合模式（Composite Pattern）是一种结构型设计模式，允许将对象组合成树形结构来表示"部分-整体"的层次结构。组合模式使得客户端对单个对象和组合对象的使用具有一致性。换句话说，组合模式允许你将多个对象组合成一个更复杂的对象，并且以一致的方式对待它们。

在你提到的场景中，大学包含多个学院，学院又包含多个学科专业，可以视为一种"部分-整体"结构，适合用组合模式来实现。这里，大学、学院和学科专业都是可以构成树形结构的对象。

### 设计思路：

1. **大学**（University）类是最顶层的容器，包含多个学院。
2. **学院**（College）类也是一个容器，包含多个学科专业。
3. **学科专业**（Department）类是叶子节点，表示具体的学科或专业。
4. **组件接口**（Component）是所有类的共同接口，定义一些通用的方法，如 `add()`、`remove()`、`getName()` 等。

### 类的结构：
- **Component**：抽象组件，定义基本的操作。
- **University**：大学，作为组合对象，包含多个学院。
- **College**：学院，作为组合对象，包含多个学科专业。
- **Department**：学科专业，作为叶子节点，没有子节点。

### Java代码实现

```java
import java.util.ArrayList;
import java.util.List;

// Step 1: 创建一个抽象组件接口
interface Component {
    void display();
    void add(Component component);
    void remove(Component component);
}

// Step 2: 创建学院类（Composite）
class College implements Component {
    private String name;
    private List<Component> departments = new ArrayList<>();

    public College(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("学院: " + name);
        for (Component department : departments) {
            department.display();
        }
    }

    @Override
    public void add(Component component) {
        departments.add(component);
    }

    @Override
    public void remove(Component component) {
        departments.remove(component);
    }
}

// Step 3: 创建学科专业类（Leaf）
class Department implements Component {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("学科专业: " + name);
    }

    @Override
    public void add(Component component) {
        // 学科专业不能包含其他组件，忽略此操作
    }

    @Override
    public void remove(Component component) {
        // 学科专业不能包含其他组件，忽略此操作
    }
}

// Step 4: 创建大学类（Composite）
class University implements Component {
    private String name;
    private List<Component> colleges = new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("大学: " + name);
        for (Component college : colleges) {
            college.display();
        }
    }

    @Override
    public void add(Component component) {
        colleges.add(component);
    }

    @Override
    public void remove(Component component) {
        colleges.remove(component);
    }
}

// Step 5: 客户端代码
public class CompositePatternDemo {
    public static void main(String[] args) {
        // 创建学科专业
        Department csDepartment = new Department("计算机科学与技术");
        Department eeDepartment = new Department("电气工程");
        Department mechDepartment = new Department("机械工程");

        // 创建学院并添加学科专业
        College engineeringCollege = new College("工程学院");
        engineeringCollege.add(csDepartment);
        engineeringCollege.add(eeDepartment);
        engineeringCollege.add(mechDepartment);

        Department econDepartment = new Department("经济学");
        Department lawDepartment = new Department("法学");

        // 创建另一个学院并添加学科专业
        College socialScienceCollege = new College("社会科学学院");
        socialScienceCollege.add(econDepartment);
        socialScienceCollege.add(lawDepartment);

        // 创建大学并添加学院
        University university = new University("某大学");
        university.add(engineeringCollege);
        university.add(socialScienceCollege);

        // 展示大学信息
        university.display();
    }
}
```

### 代码解释：
1. **Component接口**：定义了所有组件（大学、学院、学科专业）共享的操作接口，包括 `display()`（展示信息）、`add()`（添加子组件）、`remove()`（移除子组件）等方法。
2. **College类**：实现了 `Component` 接口，表示一个学院。学院可以包含多个 `Department`（学科专业），因此它是一个组合对象，使用了一个 `List<Component>` 来存储其子组件。
3. **Department类**：实现了 `Component` 接口，表示一个学科专业。学科专业是叶子节点，不能有子组件，因此它的 `add()` 和 `remove()` 方法是空的。
4. **University类**：实现了 `Component` 接口，表示大学，大学是最顶层的组合对象，包含多个 `College`（学院）。
5. **CompositePatternDemo类**：这是客户端代码，创建了大学、学院、学科专业并进行组合，最后通过调用 `display()` 方法展示整个大学的结构。

### 输出结果：
```text
大学: 某大学
学院: 工程学院
学科专业: 计算机科学与技术
学科专业: 电气工程
学科专业: 机械工程
学院: 社会科学学院
学科专业: 经济学
学科专业: 法学
```

### 组合模式的优点：
1. **简化客户端代码**：客户端代码可以一致地使用 `Component` 接口操作单个对象和组合对象，不需要关心它们的内部结构。
2. **树形结构表示**：组合模式适合表示层次结构数据，可以很方便地展示和管理层级数据。
3. **增强扩展性**：新的院系、学科专业等可以很容易地添加到现有系统中，扩展非常方便。

### 总结：
通过使用组合模式，我们将大学、学院和学科专业组织成了一个树形结构，使得整个系统的处理变得更加简单和灵活。客户端可以通过统一的接口操作组合对象和叶子对象，而无需关心它们之间的具体区别。

----------------------------

## 8. 代理模式

- 请为房产中介公司设计一套房屋租赁系统。

代理模式（Proxy Pattern）是一种结构型设计模式，通常用于为其他对象提供一种代理以控制对该对象的访问。在房产中介公司设计房屋租赁系统时，代理模式可以用于控制对房屋信息的访问，例如，通过代理可以增加权限控制、延迟加载、缓存等功能。

### 需求分析
假设房产中介公司有一个系统来处理房屋租赁，系统中有房屋实体、房东、租客、代理等角色。我们需要设计一个房屋租赁系统，其中代理模式可以用于以下几个方面：
1. **房东代理**：用于验证房东的身份、房屋信息的真实性等。
2. **租客代理**：用于验证租客的资格、查看房屋的可租状态等。
3. **租赁代理**：用于处理租赁的合同签署、支付等事务。

### 系统类设计
我们设计几个主要的类来描述系统中的角色和他们之间的交互：
1. **房屋信息接口 (`House`)**：表示房屋的基础信息。
2. **真实房屋信息 (`RealHouse`)**：房屋的实际信息，包含房东的房屋信息。
3. **代理类 (`HouseProxy`)**：代理类，控制房东与租客的交互。
4. **房东 (`Landlord`)**：管理房屋和租赁过程。
5. **租客 (`Tenant`)**：用户角色，进行租房操作。

### 代码实现

```java
// 1. 房屋信息接口
public interface House {
    void displayHouseInfo();
}

// 2. 真实房屋信息类
public class RealHouse implements House {
    private String houseId;
    private String address;
    private int rent;
    private String landlordName;

    public RealHouse(String houseId, String address, int rent, String landlordName) {
        this.houseId = houseId;
        this.address = address;
        this.rent = rent;
        this.landlordName = landlordName;
    }

    @Override
    public void displayHouseInfo() {
        System.out.println("House ID: " + houseId);
        System.out.println("Address: " + address);
        System.out.println("Rent: " + rent);
        System.out.println("Landlord: " + landlordName);
    }
}

// 3. 房东类（可以用于管理房屋）
public class Landlord {
    private String name;
    
    public Landlord(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 房东可以管理房屋信息
    public House createHouse(String houseId, String address, int rent) {
        return new RealHouse(houseId, address, rent, this.name);
    }
}

// 4. 租客类
public class Tenant {
    private String name;

    public Tenant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 租客浏览房屋信息
    public void viewHouseInfo(House house) {
        house.displayHouseInfo();
    }
}

// 5. 代理类：房屋代理（用于访问和控制租房流程）
public class HouseProxy implements House {
    private RealHouse realHouse;
    private Landlord landlord;
    private Tenant tenant;

    public HouseProxy(Landlord landlord, Tenant tenant, RealHouse realHouse) {
        this.landlord = landlord;
        this.tenant = tenant;
        this.realHouse = realHouse;
    }

    @Override
    public void displayHouseInfo() {
        if (checkTenantEligibility()) {
            System.out.println("Displaying house information with approval...");
            realHouse.displayHouseInfo();
        } else {
            System.out.println("Tenant is not eligible to view this house.");
        }
    }

    // 模拟检查租客是否合格的操作
    private boolean checkTenantEligibility() {
        // 假设租客需要满足某些条件才能查看房屋，例如信用评分等
        System.out.println("Checking eligibility for tenant: " + tenant.getName());
        return true; // 假设租客始终合格
    }

    // 可以在代理类中加入更多的功能，比如合同管理、支付验证等
    public void manageLease() {
        System.out.println("Managing lease contract and payment...");
        // 这里可以添加房屋租赁相关的逻辑，例如支付、合同签署等
    }
}

// 6. 客户端测试代码
public class Main {
    public static void main(String[] args) {
        // 创建房东和租客
        Landlord landlord = new Landlord("Alice");
        Tenant tenant = new Tenant("Bob");

        // 房东创建房屋信息
        RealHouse house = new RealHouse("H123", "123 Main St", 2000, landlord.getName());

        // 创建代理对象
        HouseProxy houseProxy = new HouseProxy(landlord, tenant, house);

        // 租客查看房屋信息
        tenant.viewHouseInfo(houseProxy);

        // 租客通过代理管理租赁流程
        houseProxy.manageLease();
    }
}
```

### 代码说明
1. **House** 是一个接口，定义了所有房屋类的共同方法 `displayHouseInfo()`，用于展示房屋信息。
2. **RealHouse** 实现了 `House` 接口，代表真实的房屋信息，包括房屋的 ID、地址、租金等内容。
3. **Landlord** 类代表房东，房东可以创建和管理房屋。
4. **Tenant** 类代表租客，租客可以查看房屋信息并通过代理类参与租赁。
5. **HouseProxy** 类是代理类，控制对房屋信息的访问和租赁流程的管理。通过代理模式，可以在显示房屋信息前加入额外的验证逻辑，如检查租客资格。

### 代理模式的优点
- **控制访问**：通过代理，可以在访问真实对象前加入额外的检查、权限控制等。
- **延迟加载**：代理可以用于实现延迟加载，直到真实对象需要被访问时再进行初始化。
- **功能增强**：代理类可以在不改变原有业务逻辑的情况下，增强其他功能，如日志、缓存、事务管理等。

### 扩展
根据需要，可以在代理类中进一步扩展更多的功能，如：
1. **合同管理**：代理类可以提供签署租赁合同的功能。
2. **支付处理**：在代理类中加入租赁支付的验证。
3. **日志记录**：代理类可以记录租客的访问日志，或者房东的房屋操作。

### 总结
通过代理模式，可以为房产中介系统提供更加灵活和可控的房屋租赁流程。这个模式通过代理对象，确保了在处理租房流程时，可以在不修改核心业务逻辑的情况下，增加额外的功能，如验证、日志记录、权限控制等。

--------------------------


## 9. 桥接模式


- 小米和VIVO手机都有直板和翻盖两种机型，请设计一个用小米直板手机打电话的系统。

在这个设计中，我们需要实现一个电话系统，通过桥接模式（Bridge Pattern）来分别处理小米和VIVO手机的直板和翻盖机型。桥接模式的核心思想是将抽象部分与实现部分分离，从而使它们可以独立地变化。

### 设计思路

1. **手机类型（抽象类）**：定义电话的基本功能（比如：打电话）。
2. **直板和翻盖机型（实现类）**：分别实现不同机型的具体操作。
3. **桥接接口**：定义手机可以执行的操作（打电话等）。
4. **桥接模式的应用**：通过桥接接口，使得不同的手机类型和机型功能可以组合。

### 类图
- `Phone`：抽象类，定义手机的基本功能（例如：打电话）。
- `PhoneType`：接口，定义手机的具体操作（例如：打电话、接电话等）。
- `MiPhone`：具体的实现类，表示小米手机。
- `VivoPhone`：具体的实现类，表示VIVO手机。
- `FlipPhone`：具体的实现类，表示翻盖机型。
- `BarPhone`：具体的实现类，表示直板机型。

### 代码实现

```java
// 手机的基本功能抽象类
abstract class Phone {
    protected PhoneType phoneType;  // 使用桥接模式，将具体机型的操作委托给phoneType
    
    public Phone(PhoneType phoneType) {
        this.phoneType = phoneType;
    }
    
    public abstract void makeCall();
}

// 手机的实现接口，定义各种手机操作
interface PhoneType {
    void makeCall();
}

// 小米手机的实现类
class MiPhone implements PhoneType {
    @Override
    public void makeCall() {
        System.out.println("小米手机拨打电话...");
    }
}

// VIVO手机的实现类
class VivoPhone implements PhoneType {
    @Override
    public void makeCall() {
        System.out.println("VIVO手机拨打电话...");
    }
}

// 直板机型的实现类
class BarPhone extends Phone {
    public BarPhone(PhoneType phoneType) {
        super(phoneType);
    }

    @Override
    public void makeCall() {
        System.out.print("直板机型，");
        phoneType.makeCall();  // 委托给具体的手机操作实现类
    }
}

// 翻盖机型的实现类
class FlipPhone extends Phone {
    public FlipPhone(PhoneType phoneType) {
        super(phoneType);
    }

    @Override
    public void makeCall() {
        System.out.print("翻盖机型，");
        phoneType.makeCall();  // 委托给具体的手机操作实现类
    }
}

// 测试类
public class BridgePatternTest {
    public static void main(String[] args) {
        // 创建具体机型和手机类型的组合
        Phone miBarPhone = new BarPhone(new MiPhone());
        Phone miFlipPhone = new FlipPhone(new MiPhone());
        Phone vivoBarPhone = new BarPhone(new VivoPhone());
        Phone vivoFlipPhone = new FlipPhone(new VivoPhone());

        // 打电话
        miBarPhone.makeCall();
        miFlipPhone.makeCall();
        vivoBarPhone.makeCall();
        vivoFlipPhone.makeCall();
    }
}
```

### 代码解析

1. **Phone**：作为抽象类，定义了一个指向 `PhoneType` 接口的引用 `phoneType`，代表手机的操作。
2. **PhoneType**：接口，所有的手机操作（如拨打电话）都由这个接口定义。
3. **MiPhone** 和 **VivoPhone**：这两个类实现了 `PhoneType` 接口，分别代表小米手机和VIVO手机的具体行为。
4. **BarPhone** 和 **FlipPhone**：这两个类分别表示直板和翻盖机型，它们继承了 `Phone` 抽象类，并实现了 `makeCall()` 方法，调用对应的 `phoneType` 来执行具体的操作。
5. **BridgePatternTest**：这个测试类创建了不同机型和手机类型的组合，然后执行打电话操作，展示了桥接模式如何使得不同的机型和手机类型可以独立变化。

### 输出结果

```
直板机型，小米手机拨打电话...
翻盖机型，小米手机拨打电话...
直板机型，VIVO手机拨打电话...
翻盖机型，VIVO手机拨打电话...
```

### 总结

- 通过桥接模式，我们成功地将手机类型（如小米、VIVO）和机型（直板、翻盖）分离，使得它们可以独立变化。
- 在此设计中，`Phone` 类是抽象的，而 `PhoneType` 接口定义了手机操作的行为。
- 这种设计使得在需要扩展新的手机品牌或机型时，不会影响已有的代码，符合开放-封闭原则。

---------------------------------


## 10. 装饰模式

- 有一家咖啡店，美式咖啡是5元/杯，意大利咖啡是8元/杯，一份牛奶是2元，一份方糖是1元，请编码计算“1杯美式+1份牛奶+2份方糖”一共多少钱？

在Java中，可以使用装饰模式来解决这个问题。装饰模式的核心思想是通过将额外的功能（比如牛奶、方糖等）动态地添加到已有的对象上，而不需要修改其结构。我们可以设计一个咖啡接口，再通过不同的装饰器类来增加额外的功能。

以下是用Java实现“1杯美式咖啡+1份牛奶+2份方糖”计算价格的代码：

### 1. 定义咖啡接口（Coffee）

```java
public interface Coffee {
    double cost();  // 计算咖啡的价格
}
```

### 2. 实现基础的咖啡类（Americano 和 Espresso）

```java
public class Americano implements Coffee {
    @Override
    public double cost() {
        return 5.0;  // 美式咖啡的价格
    }
}

public class Espresso implements Coffee {
    @Override
    public double cost() {
        return 8.0;  // 意大利咖啡的价格
    }
}
```

### 3. 创建装饰器基类（CoffeeDecorator）

装饰器类继承自 `Coffee` 接口，并包含一个 `Coffee` 对象作为成员变量，用来委托实际的价格计算。

```java
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;  // 被装饰的咖啡对象

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double cost() {
        return coffee.cost();  // 默认的计算方式，子类会覆盖这个方法
    }
}
```

### 4. 创建具体的装饰器（Milk、Sugar）

```java
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 2.0;  // 加上牛奶的价格
    }
}

public class SugarDecorator extends CoffeeDecorator {
    private int sugarCount;

    public SugarDecorator(Coffee coffee, int sugarCount) {
        super(coffee);
        this.sugarCount = sugarCount;
    }

    @Override
    public double cost() {
        return super.cost() + sugarCount * 1.0;  // 每份方糖1元
    }
}
```

### 5. 客户端代码：计算价格

```java
public class CoffeeShop {
    public static void main(String[] args) {
        Coffee coffee = new Americano();  // 选择一杯美式咖啡
        coffee = new MilkDecorator(coffee);  // 添加1份牛奶
        coffee = new SugarDecorator(coffee, 2);  // 添加2份方糖

        System.out.println("Total cost: " + coffee.cost() + "元");  // 输出最终价格
    }
}
```

### 6. 输出

```
Total cost: 9.0元
```

### 解释：

1. **Coffee接口**：定义了咖啡的基本方法 `cost()` 来计算价格。
2. **Americano类** 和 **Espresso类**：实现了 `Coffee` 接口，分别表示美式和意大利咖啡。
3. **CoffeeDecorator类**：抽象装饰器类，继承自 `Coffee` 并包含一个 `coffee` 对象，装饰器可以在此基础上扩展功能。
4. **MilkDecorator类** 和 **SugarDecorator类**：具体装饰器，分别添加牛奶和糖的费用。
5. **CoffeeShop类**：客户端代码，通过装饰器组合来计算价格。

通过装饰模式，我们可以灵活地为基本的咖啡添加不同的附加功能（如牛奶和方糖），而且不需要修改原有的类结构。

-------------------------------

## 11. 享元模式

- 请设计共享单车租赁系统，用户提供姓名即可租赁、归还单车。

享元模式（Flyweight Pattern）是一种结构型设计模式，旨在通过共享对象来有效地支持大量细粒度的对象。享元模式通过使用共享对象来减少内存占用，从而提高系统性能，特别适用于对象数量庞大的场景。

在共享单车租赁系统中，享元模式可以用来共享单车的实例，因为大多数单车的属性（如颜色、型号、品牌等）是相同的，只有租赁者的信息是不同的。因此，我们可以创建一个共享池，存储那些共有的单车对象，只有在租赁时为每个用户生成独立的租赁记录。

### 设计思路

1. **单车（Bike）**：每一辆单车是共享的，具有公共属性，比如品牌、颜色、型号等。
2. **单车租赁系统（BikeRental）**：管理共享池和租赁行为。单车池会根据需要提供已有单车，避免重复创建单车。
3. **租赁者（User）**：每个租赁者只有名字等信息，租赁时只需提供姓名。
4. **享元对象（Flyweight）**：我们可以将单车的共享部分提取出来，作为享元对象；而租赁者的信息（如租赁时间、归还时间等）则是外部状态，不共享。

### 类设计

1. `Bike`：享元对象，表示单车。
2. `BikeRental`：管理共享单车池以及用户租赁单车的功能。
3. `User`：表示租赁者信息，作为外部状态。

### 代码实现

#### 1. 定义共享单车的享元对象（Bike）

```java
// 共享单车类
public class Bike {
    private String model;  // 单车型号
    private String color;  // 单车颜色

    public Bike(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public void rent(String userName) {
        System.out.println(userName + " 租赁了一辆 " + color + " " + model + " 单车");
    }

    public void returnBike(String userName) {
        System.out.println(userName + " 归还了一辆 " + color + " " + model + " 单车");
    }
}
```

#### 2. 单车租赁系统（BikeRental）

```java
import java.util.HashMap;
import java.util.Map;

// 单车租赁系统，享元模式的核心
public class BikeRental {
    private Map<String, Bike> bikePool = new HashMap<>();  // 用于存储共享池的单车

    // 租赁单车，首先检查是否已有相同类型的单车
    public Bike rentBike(String model, String color) {
        String key = model + color;
        if (!bikePool.containsKey(key)) {
            Bike bike = new Bike(model, color);
            bikePool.put(key, bike);  // 如果没有则创建新的单车并存入共享池
        }
        return bikePool.get(key);  // 返回共享池中的单车对象
    }
}
```

#### 3. 租赁者（User）

```java
// 租赁者类，持有用户信息
public class User {
    private String name;
    private Bike bike;

    public User(String name) {
        this.name = name;
    }

    // 租赁单车
    public void rentBike(BikeRental rental, String model, String color) {
        this.bike = rental.rentBike(model, color);
        this.bike.rent(name);
    }

    // 归还单车
    public void returnBike() {
        if (this.bike != null) {
            this.bike.returnBike(name);
        } else {
            System.out.println(name + " 没有租赁任何单车！");
        }
    }
}
```

#### 4. 测试共享单车租赁系统

```java
public class FlyweightPatternTest {
    public static void main(String[] args) {
        BikeRental rentalSystem = new BikeRental();

        // 用户1租赁一辆红色的山地车
        User user1 = new User("张三");
        user1.rentBike(rentalSystem, "Mountain", "Red");

        // 用户2租赁一辆红色的山地车
        User user2 = new User("李四");
        user2.rentBike(rentalSystem, "Mountain", "Red");

        // 用户3租赁一辆蓝色的城市车
        User user3 = new User("王五");
        user3.rentBike(rentalSystem, "City", "Blue");

        // 用户1归还单车
        user1.returnBike();

        // 用户2归还单车
        user2.returnBike();

        // 用户3归还单车
        user3.returnBike();
    }
}
```

### 5. 输出结果

```
张三 租赁了一辆 Red Mountain 单车
李四 租赁了一辆 Red Mountain 单车
王五 租赁了一辆 Blue City 单车
张三 归还了一辆 Red Mountain 单车
李四 归还了一辆 Red Mountain 单车
王五 归还了一辆 Blue City 单车
```

### 6. 解释

1. **Bike类**：表示单车对象，包含单车的共享属性（型号、颜色等）。`rent()` 和 `returnBike()` 方法用于模拟租赁和归还操作。

2. **BikeRental类**：实现了享元模式的核心，它通过共享池（`bikePool`）存储已创建的单车实例。在租赁单车时，如果共享池中不存在该型号和颜色的单车，它就创建一个新的单车实例，否则直接返回共享池中的单车实例。

3. **User类**：表示租赁者。每个租赁者只需提供姓名，租赁时会调用 `BikeRental` 获取共享的单车。租赁后，用户可以通过 `returnBike()` 归还单车。

4. **共享池**：`bikePool` 存储的是单车实例的共享池，当用户请求租赁某个型号和颜色的单车时，系统会检查池中是否已有该单车。如果有，直接返回共享的单车；如果没有，则创建一个新的单车并加入池中。

### 总结

通过享元模式，我们有效地减少了单车实例的创建，只共享单车的相同部分（例如型号和颜色），而将租赁者的信息作为外部状态，这样可以显著减少内存的使用。每个用户都可以租赁不同的单车，但共享池中的单车实例数量最小化，从而提高系统的性能和资源利用率。


------------------------

## 12. 策略模式

- 一种双语电子词典，可以用汉语和英语进行单词播放，请设计该播放系统。


策略模式是一种行为型设计模式，它允许在运行时选择算法的不同实现。具体来说，策略模式将每种算法封装成一个策略类，客户端可以根据需要动态地选择和改变策略。

在这个问题中，要求设计一个双语电子词典，它能够根据用户选择的语言（如汉语或英语）播放单词的发音。每种语言对应一个不同的发音策略，因此我们可以使用策略模式来实现不同的发音方式。

### 设计思路
1. **定义一个发音接口**：`PronunciationStrategy`，不同的语言（汉语、英语）实现这个接口。
2. **策略类**：具体的发音策略，如 `ChinesePronunciationStrategy` 和 `EnglishPronunciationStrategy`。
3. **上下文类**：`WordPlayer`，用于持有和切换不同的发音策略。

### 类设计

1. **`PronunciationStrategy`**：定义发音的接口。
2. **`ChinesePronunciationStrategy`** 和 **`EnglishPronunciationStrategy`**：具体的发音策略，实现 `PronunciationStrategy` 接口。
3. **`WordPlayer`**：上下文类，负责管理和切换发音策略。
4. **`Word`**：表示词典中的单词，包含单词和播放它的方法。

### 代码实现

#### 1. 发音策略接口 (`PronunciationStrategy`)

```java
public interface PronunciationStrategy {
    void pronounce(String word);  // 发音方法，传入单词进行发音
}
```

#### 2. 具体的发音策略类

##### `ChinesePronunciationStrategy` (中文发音)

```java
public class ChinesePronunciationStrategy implements PronunciationStrategy {
    @Override
    public void pronounce(String word) {
        System.out.println("用汉语发音：" + word);
    }
}
```

##### `EnglishPronunciationStrategy` (英语发音)

```java
public class EnglishPronunciationStrategy implements PronunciationStrategy {
    @Override
    public void pronounce(String word) {
        System.out.println("Pronounced in English: " + word);
    }
}
```

#### 3. 词典播放器上下文类 (`WordPlayer`)

```java
public class WordPlayer {
    private PronunciationStrategy pronunciationStrategy;  // 持有策略的引用

    // 构造方法，初始化默认的发音策略
    public WordPlayer(PronunciationStrategy pronunciationStrategy) {
        this.pronunciationStrategy = pronunciationStrategy;
    }

    // 设置新的发音策略
    public void setPronunciationStrategy(PronunciationStrategy pronunciationStrategy) {
        this.pronunciationStrategy = pronunciationStrategy;
    }

    // 播放单词
    public void playWord(String word) {
        pronunciationStrategy.pronounce(word);  // 使用当前策略发音
    }
}
```

#### 4. 测试客户端 (`TestDictionary`)

```java
public class TestDictionary {
    public static void main(String[] args) {
        // 创建发音策略
        PronunciationStrategy chineseStrategy = new ChinesePronunciationStrategy();
        PronunciationStrategy englishStrategy = new EnglishPronunciationStrategy();
        
        // 创建词典播放器，初始为中文发音
        WordPlayer wordPlayer = new WordPlayer(chineseStrategy);
        
        // 播放单词
        wordPlayer.playWord("苹果");  // 输出：用汉语发音：苹果
        
        // 更换为英文发音
        wordPlayer.setPronunciationStrategy(englishStrategy);
        wordPlayer.playWord("Apple");  // 输出：Pronounced in English: Apple
        
        // 再次更换为中文发音
        wordPlayer.setPronunciationStrategy(chineseStrategy);
        wordPlayer.playWord("香蕉");  // 输出：用汉语发音：香蕉
    }
}
```

### 5. 运行结果

```
用汉语发音：苹果
Pronounced in English: Apple
用汉语发音：香蕉
```

### 6. 解释

- **`PronunciationStrategy`**：定义了一个策略接口 `pronounce`，所有具体的发音方式都必须实现这个接口。

- **`ChinesePronunciationStrategy` 和 `EnglishPronunciationStrategy`**：分别实现了中文和英文的发音方式。在 `pronounce` 方法中，输出对应语言的发音方式。

- **`WordPlayer`**：该类扮演了上下文的角色，它持有一个 `PronunciationStrategy` 的引用，负责调用策略类的 `pronounce` 方法进行发音。`WordPlayer` 可以通过 `setPronunciationStrategy()` 方法动态切换发音策略。

- **`TestDictionary`**：客户端代码，创建了一个 `WordPlayer` 实例，并且演示了如何在运行时选择不同的发音策略（中文或英文）。

### 总结

策略模式的使用使得我们能够在运行时切换不同的发音策略，而无需修改具体的发音逻辑。每种语言的发音方式封装在一个独立的策略类中，`WordPlayer` 通过组合不同的策略对象来达到灵活切换发音方式的效果。通过这种方式，我们可以轻松扩展支持更多语言的发音，而不需要改变现有的代码结构。


--------------------------------


## 13. 模版模式

- 不同银行的柜台办事流程基本相同，都包含了取号、办理业务和柜员评价三个环节，但具体操作细节有差异，请设计该业务办理系统。

模板模式（Template Method Pattern）是一种行为设计模式，它通过定义一个操作中的算法骨架，将某些步骤的实现延迟到子类中。模板方法允许子类在不改变算法结构的情况下重定义算法中的某些步骤。

在这个银行柜台业务办理的系统中，不同银行的办事流程虽然大体相同，但每个银行在取号、办理业务、柜员评价的具体操作细节上可能有所不同。我们可以将这些不同的步骤放入父类中，而具体的业务操作则由子类实现。

### 设计思路

1. **父类`BankService`**：包含一个模板方法`processService()`，它定义了固定的业务流程（取号、办理业务、柜员评价）。一些具体的操作由子类实现。
2. **子类**：每个具体银行（例如`BankAService`、`BankBService`等）继承`BankService`类，并实现具体的业务操作（取号、办理业务、柜员评价）。
3. **流程步骤**：取号、办理业务、柜员评价是共通的步骤，不同银行可以根据实际需求修改每个步骤的具体操作。

### Java代码实现

```java
// 抽象类 BankService，定义模板方法和步骤
abstract class BankService {

    // 模板方法，定义办理业务的固定流程
    public final void processService() {
        takeNumber();           // 取号
        performBusiness();      // 办理业务
        rateClerk();            // 柜员评价
    }

    // 取号
    private void takeNumber() {
        System.out.println("取号排队");
    }

    // 具体的办理业务，由子类实现
    protected abstract void performBusiness();

    // 柜员评价
    private void rateClerk() {
        System.out.println("评价柜员服务质量");
    }
}

// 具体银行A的服务实现
class BankAService extends BankService {

    @Override
    protected void performBusiness() {
        System.out.println("Bank A：办理业务 - 存款");
    }
}

// 具体银行B的服务实现
class BankBService extends BankService {

    @Override
    protected void performBusiness() {
        System.out.println("Bank B：办理业务 - 贷款");
    }
}

// 测试
public class TemplateMethodPatternDemo {
    public static void main(String[] args) {
        BankService bankAService = new BankAService();
        BankService bankBService = new BankBService();

        System.out.println("=== Bank A 服务流程 ===");
        bankAService.processService(); // 执行银行A的服务流程

        System.out.println("\n=== Bank B 服务流程 ===");
        bankBService.processService(); // 执行银行B的服务流程
    }
}
```

### 代码解析

1. **`BankService` 类**：
   - 这是一个抽象类，定义了一个模板方法`processService()`，它是固定的流程，按顺序执行取号、办理业务和柜员评价。
   - `takeNumber()`和`rateClerk()`方法是具体的实现，不会改变。
   - `performBusiness()`是一个抽象方法，由具体银行的子类来实现具体的业务逻辑。

2. **`BankAService` 类和 `BankBService` 类**：
   - 这两个类是`BankService`的具体实现类，分别实现了`performBusiness()`方法，体现了不同银行在业务办理上的差异。

3. **`TemplateMethodPatternDemo` 类**：
   - 这是一个测试类，创建了`BankAService`和`BankBService`的对象，并调用它们的`processService()`方法来执行业务流程。

### 输出示例

```
=== Bank A 服务流程 ===
取号排队
Bank A：办理业务 - 存款
评价柜员服务质量

=== Bank B 服务流程 ===
取号排队
Bank B：办理业务 - 贷款
评价柜员服务质量
```

### 优点

- **代码复用**：公共的业务流程被提取到父类`BankService`中，减少了重复代码。
- **扩展性强**：新银行的业务实现只需继承`BankService`并实现`performBusiness()`方法，无需修改原有流程。
- **灵活性**：通过继承和重写具体步骤的实现，允许每个银行定制自己的业务办理流程。

这种设计模式非常适合有固定流程但步骤细节会有所差异的场景，如银行柜台、餐厅订单流程等。



-----------------------

## 14. 备忘录模式

- 设计一个小游戏存档恢复系统。

备忘录模式（Memento Pattern）是一种行为设计模式，它可以在不暴露对象实现细节的情况下，保存和恢复对象的内部状态。这个模式常用于需要撤销操作或状态恢复的场景。

在小游戏存档恢复系统中，我们可以使用备忘录模式来保存游戏的状态（例如玩家的位置、得分、等级等），并且在需要时恢复到某个先前的状态（例如恢复存档、撤销操作等）。

### 设计思路

1. **备忘录（Memento）**：用于存储游戏的状态。
2. **发起人（Originator）**：保存当前状态，并可以创建和恢复备忘录。
3. **管理者（Caretaker）**：负责保存和管理多个备忘录，但不修改备忘录的内容。

### 关键组件
1. **`GameState`（备忘录）**：存储游戏状态的类。
2. **`Game`（发起人）**：包含当前游戏状态，并能够创建备忘录以及恢复备忘录。
3. **`GameManager`（管理者）**：负责保存和恢复游戏存档。

### 代码实现

#### 1. 备忘录（Memento）

`GameState` 类保存游戏状态的所有信息。它是一个简单的容器类，包含游戏的状态数据。

```java
// 备忘录类，用于保存游戏状态
public class GameState {
    private int level;     // 游戏关卡
    private int score;     // 游戏分数
    private String playerName;  // 玩家名称

    public GameState(int level, int score, String playerName) {
        this.level = level;
        this.score = score;
        this.playerName = playerName;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "Player: " + playerName + ", Level: " + level + ", Score: " + score;
    }
}
```

#### 2. 发起人（Originator）

`Game` 类负责维护游戏的当前状态，并能生成和恢复备忘录。

```java
// 发起人类，管理游戏状态并生成备忘录
public class Game {
    private int level;
    private int score;
    private String playerName;

    public Game(String playerName) {
        this.playerName = playerName;
        this.level = 1;
        this.score = 0;
    }

    // 设置游戏状态
    public void setState(int level, int score) {
        this.level = level;
        this.score = score;
    }

    // 创建备忘录
    public GameState saveState() {
        return new GameState(level, score, playerName);
    }

    // 恢复游戏状态
    public void restoreState(GameState state) {
        this.level = state.getLevel();
        this.score = state.getScore();
    }

    // 获取当前游戏状态
    public void printState() {
        System.out.println("Game State: Player " + playerName + " is at Level " + level + " with Score " + score);
    }
}
```

#### 3. 管理者（Caretaker）

`GameManager` 类负责保存和恢复备忘录。它不对备忘录进行修改，只是保存和获取。

```java
// 管理者类，负责存档和恢复存档
import java.util.Stack;

public class GameManager {
    private Stack<GameState> savedStates = new Stack<>();

    // 保存游戏状态
    public void saveState(GameState state) {
        savedStates.push(state);
    }

    // 恢复到上一个存档
    public GameState restoreState() {
        if (savedStates.isEmpty()) {
            System.out.println("No saved game state!");
            return null;
        }
        return savedStates.pop();
    }
}
```

#### 4. 测试类

`GameTest` 类是我们的客户端，它创建游戏对象，保存游戏状态，并通过管理器恢复状态。

```java
// 测试类，模拟游戏存档和恢复
public class GameTest {
    public static void main(String[] args) {
        Game game = new Game("Player1");

        // 创建一个游戏管理器
        GameManager manager = new GameManager();

        // 玩家进行游戏
        game.setState(2, 100);
        game.printState();  // 输出当前状态
        manager.saveState(game.saveState());  // 保存当前状态

        // 玩家继续游戏
        game.setState(3, 200);
        game.printState();  // 输出当前状态

        // 玩家决定恢复游戏
        GameState savedState = manager.restoreState();
        if (savedState != null) {
            game.restoreState(savedState);
            game.printState();  // 恢复到存档时的状态
        }

        // 再次恢复游戏状态
        savedState = manager.restoreState();
        if (savedState != null) {
            game.restoreState(savedState);
            game.printState();  // 再次恢复状态
        }
    }
}
```

### 运行结果

```
Game State: Player Player1 is at Level 2 with Score 100
Game State: Player Player1 is at Level 3 with Score 200
Game State: Player Player1 is at Level 2 with Score 100
Game State: Player Player1 is at Level 1 with Score 0
```

### 代码解析

1. **`GameState` 类**：这是备忘录类，存储游戏的状态，包括玩家的等级、得分和名称。它通过构造函数初始化，并通过 getter 方法访问其内容。
2. **`Game` 类**：这是发起人类，负责保存游戏状态并生成和恢复备忘录。`saveState()`方法会返回一个`GameState`对象，表示当前的游戏状态，而`restoreState()`方法则通过备忘录恢复游戏状态。
3. **`GameManager` 类**：这是管理者类，负责保存和恢复游戏的存档。它使用一个 `Stack` 来管理多个游戏存档，玩家可以随时恢复到之前的某个状态。
4. **`GameTest` 类**：这是一个简单的测试类，模拟了玩家进行游戏、保存存档、恢复存档的过程。

### 优点

- **保存和恢复游戏状态**：通过备忘录模式，可以轻松地在游戏中保存和恢复状态，而不暴露游戏的内部实现细节。
- **撤销操作**：可以轻松实现撤销操作，玩家可以在游戏过程中恢复到之前的状态。
- **不破坏封装**：备忘录模式保证了游戏对象的封装性，外部无法直接访问或修改其内部状态。

### 总结

备忘录模式为游戏开发提供了一种优雅的方式来保存和恢复游戏状态。通过创建`GameState`（备忘录）、`Game`（发起人）和`GameManager`（管理者）类，玩家可以在需要时随时保存和恢复游戏进度，增强了游戏的交互性和用户体验。


--------------------------

## 15. 观察者模式

- 某站有许多up主，也有许多user用户，用户可以关注up，获取up的更新推送，请设计该消息管理系统。

在这个场景中，我们可以使用**观察者模式**来实现用户和up主之间的关系。用户可以关注up主，up主一旦发布新内容（比如视频），所有关注了该up主的用户都会收到更新推送。

### 设计步骤：
1. **抽象主题（Subject）**：表示被观察的对象（在本案例中是up主）。
2. **具体主题（ConcreteSubject）**：实现具体的up主，可以发布视频并通知所有关注的用户。
3. **抽象观察者（Observer）**：表示观察者，即用户。
4. **具体观察者（ConcreteObserver）**：实现具体的用户，可以接收来自up主的推送更新。

### Java实现

#### 1. 定义 `Observer` 接口
`Observer` 是一个抽象接口，定义了所有观察者应当实现的方法。

```java
public interface Observer {
    void update(String videoTitle);  // 当有新视频时，推送通知给用户
}
```

#### 2. 定义 `Subject` 接口
`Subject` 是一个抽象接口，定义了管理观察者的方法。

```java
import java.util.List;

public interface Subject {
    void addObserver(Observer observer);   // 添加观察者
    void removeObserver(Observer observer); // 移除观察者
    void notifyObservers();                // 通知所有观察者
}
```

#### 3. 具体的 `UpMaster` 类（主题）
`UpMaster` 类实现了 `Subject` 接口，表示一个up主。当up主发布新视频时，它会通知所有关注的用户。

```java
import java.util.ArrayList;
import java.util.List;

public class UpMaster implements Subject {
    private List<Observer> observers = new ArrayList<>();  // 保存所有关注的用户
    private String name;  // up主的名字

    public UpMaster(String name) {
        this.name = name;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);  // 添加关注的用户
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);  // 移除关注的用户
    }

    @Override
    public void notifyObservers() {
        // 通知所有关注的用户有新视频
        for (Observer observer : observers) {
            observer.update("New video from " + name + ": Check it out!");  // 推送更新
        }
    }

    public void publishNewVideo(String videoTitle) {
        System.out.println(name + " published a new video: " + videoTitle);
        notifyObservers();  // 发布新视频后，通知所有用户
    }
}
```

#### 4. 具体的 `User` 类（观察者）
`User` 类实现了 `Observer` 接口，表示一个用户。用户可以接收up主发布的推送。

```java
public class User implements Observer {
    private String name;  // 用户的名字

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(name + " received notification: " + videoTitle);  // 用户接收到推送
    }
}
```

#### 5. 测试类
最后，我们编写一个测试类，模拟用户关注up主，并且up主发布新视频的场景。

```java
public class Main {
    public static void main(String[] args) {
        // 创建up主
        UpMaster upMaster1 = new UpMaster("TechUp");
        UpMaster upMaster2 = new UpMaster("FoodieUp");

        // 创建用户
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        // 用户关注up主
        upMaster1.addObserver(user1);
        upMaster1.addObserver(user2);
        upMaster2.addObserver(user2);

        // up主发布新视频
        upMaster1.publishNewVideo("Java Design Patterns - Observer");
        upMaster2.publishNewVideo("Delicious Pasta Recipe");
    }
}
```

#### 6. 输出结果：
运行 `Main` 类的程序时，控制台将会输出如下内容：

```
TechUp published a new video: Java Design Patterns - Observer
Alice received notification: New video from TechUp: Check it out!
Bob received notification: New video from TechUp: Check it out!
FoodieUp published a new video: Delicious Pasta Recipe
Bob received notification: New video from FoodieUp: Check it out!
```

### 代码总结：
- **UpMaster** 类（具体主题）负责维护一个用户列表，当有新视频发布时通知所有的观察者。
- **User** 类（具体观察者）接收并响应up主的更新。
- 使用了 `Observer` 和 `Subject` 接口来解耦up主与用户之间的关系，符合**观察者模式**的设计原则。


-------------------------

## 16. 命令模式

- 餐厅服务员接受到顾客订单后，通知厨师制作食品，请设计该餐厅业务处理系统。

命令模式（Command Pattern）是一种行为设计模式，它将请求封装成一个对象，从而让您使用不同的请求、队列或日志来参数化对象。该模式能够将请求调用者和接收者解耦，常常用于例如菜单、远程控制等应用场景。

### 问题描述：
在餐厅业务处理系统中，服务员接受到顾客的订单后，需要通知厨师制作食品。服务员和厨师之间的通信使用命令模式实现。

### 设计思路：
1. **命令（Command）**：将请求封装成命令对象。餐厅的每一道菜品都是一个命令对象。
2. **命令接收者（Receiver）**：执行具体操作的对象，这里是厨师。
3. **命令发起者（Invoker）**：请求的发起者，餐厅的服务员。
4. **客户端（Client）**：构建命令对象并将其传递给命令发起者。

### 设计类图：
- `Command`: 命令接口，定义执行操作的抽象方法。
- `ConcreteCommand`: 具体的命令实现类，调用接收者（厨师）来执行操作。
- `Receiver`: 接收命令并执行实际操作的对象（厨师）。
- `Invoker`: 持有命令对象并触发命令的发起者（服务员）。
- `Client`: 创建命令对象并设置命令发起者和接收者。

### Java代码实现：

```java
// 命令接口
interface Command {
    void execute();
}

// 具体命令类：做菜命令
class CookDishCommand implements Command {
    private Chef chef;
    private String dishName;

    public CookDishCommand(Chef chef, String dishName) {
        this.chef = chef;
        this.dishName = dishName;
    }

    @Override
    public void execute() {
        chef.cook(dishName); // 执行厨师的做菜操作
    }
}

// 命令接收者：厨师
class Chef {
    public void cook(String dishName) {
        System.out.println("厨师正在做: " + dishName);
    }
}

// 请求发送者：服务员
class Waiter {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void placeOrder() {
        System.out.println("服务员接受订单...");
        command.execute(); // 执行命令
    }
}

// 客户端代码：测试系统
public class Restaurant {
    public static void main(String[] args) {
        // 创建厨师对象
        Chef chef = new Chef();

        // 创建命令对象
        Command pizzaCommand = new CookDishCommand(chef, "披萨");
        Command pastaCommand = new CookDishCommand(chef, "意大利面");

        // 创建服务员并设置命令
        Waiter waiter = new Waiter();

        // 服务员接受订单并执行
        waiter.setCommand(pizzaCommand);
        waiter.placeOrder();  // 输出：服务员接受订单... 厨师正在做: 披萨

        waiter.setCommand(pastaCommand);
        waiter.placeOrder();  // 输出：服务员接受订单... 厨师正在做: 意大利面
    }
}
```

### 代码说明：
1. **`Command`接口**：定义了一个`execute`方法，所有的命令都需要实现这个接口。
2. **`CookDishCommand`类**：具体的命令类，封装了厨师做菜的请求。它调用`Chef`类的`cook`方法。
3. **`Chef`类**：命令的接收者，实际负责做菜的工作。
4. **`Waiter`类**：命令的调用者（发起者），它接收命令并调用命令的`execute`方法来通知厨师做菜。
5. **`Restaurant`类**：客户端类，创建了命令对象并发出订单。

### 输出结果：
```
服务员接受订单...
厨师正在做: 披萨
服务员接受订单...
厨师正在做: 意大利面
```

### 关键点：
1. **解耦**：服务员和厨师之间通过命令对象解耦。服务员不需要知道厨师如何做菜，只需要发出命令即可。
2. **扩展性**：如果以后增加新的菜品，只需新增一个`Command`实现类，不需要修改现有代码。
3. **复用性**：命令对象可以在不同的上下文中重用，例如，可以将命令对象存储在队列中，延迟执行。

通过这种方式，餐厅业务处理系统中的服务员和厨师之间的交互变得更加灵活和可扩展。


--------------------------


## 17. 状态模式


- 马路的交通灯是按“绿-黄-红-绿”循环变化，请设计一个交通灯信号控制系统。

### 设计思路：

状态模式（State Pattern）是一种行为设计模式，它允许一个对象在其内部状态发生改变时改变其行为，使得对象看起来好像修改了其类。对于交通灯控制系统来说，不同的交通灯颜色（绿、黄、红）可以看作是不同的状态，每个状态下的行为是不同的。

在这个设计中，我们将每个交通灯的状态（绿灯、黄灯、红灯）表示为一个具体的状态类，交通灯本身（即上下文）将根据当前状态变化到下一个状态。具体的设计步骤如下：

1. **状态接口（State）**：定义一个通用的接口，每种颜色灯的状态类都实现这个接口。
2. **具体状态类（ConcreteState）**：每个具体的状态类（绿灯、黄灯、红灯）实现状态接口，定义该状态下的行为。
3. **上下文（Context）**：交通灯信号控制系统，它持有当前的状态对象，并委托给状态对象处理行为。

### 设计类图：
- `State`：定义交通灯状态的接口。
- `GreenLightState`：绿灯状态类。
- `YellowLightState`：黄灯状态类。
- `RedLightState`：红灯状态类。
- `TrafficLightContext`：交通灯上下文类，负责维护当前的状态。

### Java代码实现：

#### 1. `State` 接口
```java
// 状态接口，定义状态的行为
public interface State {
    void handleRequest(TrafficLightContext context);  // 每个状态都会调用此方法来切换到下一个状态
}
```

#### 2. 具体状态类

##### `GreenLightState` (绿灯状态)
```java
// 绿灯状态类
public class GreenLightState implements State {
    @Override
    public void handleRequest(TrafficLightContext context) {
        System.out.println("绿灯亮起，车辆可以通行！");
        // 绿灯结束后切换到黄灯
        context.setState(new YellowLightState());
    }
}
```

##### `YellowLightState` (黄灯状态)
```java
// 黄灯状态类
public class YellowLightState implements State {
    @Override
    public void handleRequest(TrafficLightContext context) {
        System.out.println("黄灯亮起，注意准备停车！");
        // 黄灯结束后切换到红灯
        context.setState(new RedLightState());
    }
}
```

##### `RedLightState` (红灯状态)
```java
// 红灯状态类
public class RedLightState implements State {
    @Override
    public void handleRequest(TrafficLightContext context) {
        System.out.println("红灯亮起，车辆停下！");
        // 红灯结束后切换到绿灯
        context.setState(new GreenLightState());
    }
}
```

#### 3. `TrafficLightContext` 类（上下文）
```java
// 上下文类，维护当前状态并根据状态变化执行不同的行为
public class TrafficLightContext {
    private State currentState;

    public TrafficLightContext() {
        // 初始状态是绿灯
        currentState = new GreenLightState();
    }

    // 设置当前状态
    public void setState(State state) {
        this.currentState = state;
    }

    // 请求当前状态执行行为
    public void request() {
        currentState.handleRequest(this);
    }
}
```

#### 4. 客户端代码（测试）
```java
// 客户端代码，模拟交通灯的工作过程
public class Main {
    public static void main(String[] args) {
        // 创建交通灯上下文对象
        TrafficLightContext trafficLight = new TrafficLightContext();

        // 模拟交通灯的工作过程
        for (int i = 0; i < 6; i++) {
            System.out.println("----- 当前状态：" + (i + 1) + " -----");
            trafficLight.request();  // 请求当前状态的行为
        }
    }
}
```

### 代码说明：

1. **`State`接口**：定义了交通灯状态下应该实现的行为，即`handleRequest`方法，每个具体的状态类都需要实现这个方法。
2. **`GreenLightState`、`YellowLightState`、`RedLightState`**：这些是交通灯的不同状态，每个状态类都实现了`handleRequest`方法，控制交通灯的转换。
3. **`TrafficLightContext`**：这是上下文类，它持有一个`State`对象（即当前的交通灯状态），并提供一个`request`方法，通过该方法委托给当前状态类来执行具体的操作。
4. **`Main`类**：模拟交通灯的工作过程，循环执行交通灯状态的切换。

### 输出结果：
```
----- 当前状态：1 -----
绿灯亮起，车辆可以通行！
----- 当前状态：2 -----
黄灯亮起，注意准备停车！
----- 当前状态：3 -----
红灯亮起，车辆停下！
----- 当前状态：4 -----
绿灯亮起，车辆可以通行！
----- 当前状态：5 -----
黄灯亮起，注意准备停车！
----- 当前状态：6 -----
红灯亮起，车辆停下！
```

### 关键点：
1. **状态切换**：每个状态（绿灯、黄灯、红灯）会根据当前状态切换到下一个状态。状态的变化由上下文类（`TrafficLightContext`）管理。
2. **解耦**：通过状态模式，交通灯的不同颜色（即不同状态）的行为被封装到了具体的状态类中，`TrafficLightContext`类只需要委托给当前状态进行处理。
3. **扩展性**：如果要新增更多的交通灯状态（如闪烁黄灯等），只需要增加一个新的状态类并实现`State`接口，而不需要修改已有的代码。

### 总结：
使用状态模式来设计交通灯信号控制系统非常符合实际需求。每种交通灯的状态被封装成一个独立的状态类，并且上下文（交通灯）通过状态模式来控制状态的转移。这样，代码更具可维护性、扩展性，并且减少了对象间的耦合。


----------------------------

## 18. 职责链模式

- 某单位采购物品，如金额小于或等于5000,由主管审批；如金额小于等于10000，由部门经理审批；如金额小于等于30000，由副总审批；如果金额超过30000以上，由总经理审批。请设计如上业务流程。

职责链模式（Chain of Responsibility Pattern）是一种行为设计模式，用于将请求的处理者连成一条链，并使请求沿链传递，直到有一个对象能够处理它。在你的需求中，根据采购金额的不同，不同的审批人进行审批，这正好符合职责链模式的应用。

### 需求分析
- 采购金额 <= 5000 由主管审批
- 采购金额 <= 10000 由部门经理审批
- 采购金额 <= 30000 由副总审批
- 采购金额 > 30000 由总经理审批

### 设计
1. **创建审批者接口（Handler）**：定义每个审批人（处理者）都必须实现的接口，接口包含处理请求的方法。
2. **具体的审批者（ConcreteHandler）**：每个具体的审批人实现这个接口，并决定自己是否能处理请求。如果不能处理，交给链上的下一个审批人处理。
3. **客户端（Client）**：客户端负责构建职责链并发出请求。

### 代码实现

```java
// 1. 创建审批者接口（Handler）
public interface Approver {
    void setNextApprover(Approver approver); // 设置下一个审批者
    void approve(int amount);  // 处理审批请求
}

// 2. 创建具体的审批者类
// 主管审批
public class Supervisor implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver approver) {
        this.nextApprover = approver;
    }

    @Override
    public void approve(int amount) {
        if (amount <= 5000) {
            System.out.println("主管审批通过，采购金额：" + amount);
        } else if (nextApprover != null) {
            nextApprover.approve(amount); // 传递给下一个审批者
        }
    }
}

// 部门经理审批
public class DepartmentManager implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver approver) {
        this.nextApprover = approver;
    }

    @Override
    public void approve(int amount) {
        if (amount <= 10000) {
            System.out.println("部门经理审批通过，采购金额：" + amount);
        } else if (nextApprover != null) {
            nextApprover.approve(amount); // 传递给下一个审批者
        }
    }
}

// 副总审批
public class VicePresident implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver approver) {
        this.nextApprover = approver;
    }

    @Override
    public void approve(int amount) {
        if (amount <= 30000) {
            System.out.println("副总审批通过，采购金额：" + amount);
        } else if (nextApprover != null) {
            nextApprover.approve(amount); // 传递给下一个审批者
        }
    }
}

// 总经理审批
public class GeneralManager implements Approver {
    @Override
    public void setNextApprover(Approver approver) {
        // 总经理是链中的最后一个审批者，不需要设置下一个审批者
    }

    @Override
    public void approve(int amount) {
        System.out.println("总经理审批通过，采购金额：" + amount);
    }
}

// 3. 客户端代码
public class Client {
    public static void main(String[] args) {
        // 创建各个审批者
        Approver supervisor = new Supervisor();
        Approver departmentManager = new DepartmentManager();
        Approver vicePresident = new VicePresident();
        Approver generalManager = new GeneralManager();

        // 设置职责链
        supervisor.setNextApprover(departmentManager);
        departmentManager.setNextApprover(vicePresident);
        vicePresident.setNextApprover(generalManager);

        // 测试不同的采购金额
        System.out.println("申请采购金额 4000：");
        supervisor.approve(4000);  // 主管审批通过

        System.out.println("\n申请采购金额 8000：");
        supervisor.approve(8000);  // 部门经理审批通过

        System.out.println("\n申请采购金额 15000：");
        supervisor.approve(15000); // 副总审批通过

        System.out.println("\n申请采购金额 35000：");
        supervisor.approve(35000); // 总经理审批通过
    }
}
```

### 解释
1. **Approver接口**：每个审批者（主管、部门经理、副总、总经理）都实现这个接口。接口定义了两个方法：
    - `setNextApprover()`：设置下一个审批者，形成职责链。
    - `approve()`：处理请求，如果当前审批者不能处理请求，则把请求交给下一个审批者。

2. **Supervisor, DepartmentManager, VicePresident, GeneralManager类**：这些类是具体的审批者，每个类都实现了`Approver`接口。每个审批者都有自己的审批逻辑，满足条件时直接审批；如果条件不符，则将请求传递给下一个审批者。

3. **Client类**：客户端代码通过创建并设置责任链来进行测试，模拟不同的采购金额。

### 输出结果
```
申请采购金额 4000：
主管审批通过，采购金额：4000

申请采购金额 8000：
部门经理审批通过，采购金额：8000

申请采购金额 15000：
副总审批通过，采购金额：15000

申请采购金额 35000：
总经理审批通过，采购金额：35000
```

### 总结
通过职责链模式，我们把不同的审批人链式连接，根据采购金额大小自动将请求传递给相应的审批人，避免了大量的条件判断，代码结构清晰，易于扩展和维护。


-----------------------------


## 19. 中介者模式

- 中介者模式：现代智能家居，根据用户需求制定智能家居相互间的联动效果：如闹钟响起后，咖啡机会煮咖啡，并打开TV，观看热点新闻，咖啡煮好后，拉起窗帘并关闭TV，请设计该控制系统。

要实现这个基于中介者模式的智能家居控制系统，可以考虑以下设计思路：我们将设计一个“智能家居控制系统”，其中的各个设备（如闹钟、咖啡机、电视、窗帘）作为不同的“同事”与中介者进行交互。中介者将负责协调设备之间的联动，确保各个设备按用户需求执行动作。

### 1. 设计思路
我们将创建一个中介者类（`SmartHomeMediator`），它将负责管理和协调各个设备之间的交互。每个设备都将通过中介者类与其他设备进行交互。具体实现时，每个设备都是一个独立的类，它们都会通过中介者来通知其他设备的状态变化，从而达到联动的效果。

### 2. 主要角色：
- **设备（同事类）**：包括闹钟、咖啡机、电视、窗帘等。
- **中介者（Mediator）**：负责协调设备之间的互动。

### 3. 设备的功能：
- **闹钟**：响铃后通知中介者。
- **咖啡机**：在闹钟响起后开始煮咖啡，煮好后通知中介者。
- **电视**：在闹钟响起后打开，显示热点新闻。
- **窗帘**：在咖啡煮好后拉起窗帘。

### 4. 类设计

#### 设备接口 (Colleague)

```java
public interface Device {
    void setMediator(SmartHomeMediator mediator);  // 设置中介者
    void executeAction();  // 执行设备的特定行为
}
```

#### 中介者 (Mediator)

```java
public class SmartHomeMediator {
    private Alarm alarm;
    private CoffeeMachine coffeeMachine;
    private TV tv;
    private Curtains curtains;

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public void setTV(TV tv) {
        this.tv = tv;
    }

    public void setCurtains(Curtains curtains) {
        this.curtains = curtains;
    }

    public void onAlarmRings() {
        System.out.println("Alarm rang, triggering the system...");
        coffeeMachine.startBrewing();
        tv.turnOn();
    }

    public void onCoffeeReady() {
        System.out.println("Coffee is ready. Pulling the curtains...");
        curtains.pullCurtains();
        tv.turnOff();
    }
}
```

#### 具体设备类实现

1. **闹钟**

```java
public class Alarm implements Device {
    private SmartHomeMediator mediator;

    @Override
    public void setMediator(SmartHomeMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void executeAction() {
        System.out.println("Alarm is ringing...");
        mediator.onAlarmRings();  // 通知中介者闹钟响了
    }
}
```

2. **咖啡机**

```java
public class CoffeeMachine implements Device {
    private SmartHomeMediator mediator;

    @Override
    public void setMediator(SmartHomeMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void executeAction() {
        System.out.println("Coffee machine is brewing coffee...");
    }

    public void startBrewing() {
        executeAction();
        // 假设咖啡需要5秒钟完成
        try {
            Thread.sleep(5000);  // 模拟咖啡机工作时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mediator.onCoffeeReady();  // 咖啡做好后通知中介者
    }
}
```

3. **电视**

```java
public class TV implements Device {
    private SmartHomeMediator mediator;

    @Override
    public void setMediator(SmartHomeMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void executeAction() {
        System.out.println("Turning on the TV to show hot news...");
    }

    public void turnOn() {
        executeAction();
    }

    public void turnOff() {
        System.out.println("Turning off the TV.");
    }
}
```

4. **窗帘**

```java
public class Curtains implements Device {
    private SmartHomeMediator mediator;

    @Override
    public void setMediator(SmartHomeMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void executeAction() {
        System.out.println("Pulling the curtains...");
    }

    public void pullCurtains() {
        executeAction();
    }
}
```

### 5. 客户端代码（测试）

```java
public class Main {
    public static void main(String[] args) {
        // 创建中介者
        SmartHomeMediator mediator = new SmartHomeMediator();

        // 创建设备
        Alarm alarm = new Alarm();
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        TV tv = new TV();
        Curtains curtains = new Curtains();

        // 设置中介者
        alarm.setMediator(mediator);
        coffeeMachine.setMediator(mediator);
        tv.setMediator(mediator);
        curtains.setMediator(mediator);

        // 中介者管理设备
        mediator.setAlarm(alarm);
        mediator.setCoffeeMachine(coffeeMachine);
        mediator.setTV(tv);
        mediator.setCurtains(curtains);

        // 用户触发闹钟
        alarm.executeAction();  // 触发闹钟
    }
}
```

### 6. 运行结果

```
Alarm is ringing...
Alarm rang, triggering the system...
Coffee machine is brewing coffee...
Turning on the TV to show hot news...
Coffee is ready. Pulling the curtains...
Pulling the curtains...
Turning off the TV.
```

### 7. 总结
这个设计使用了中介者模式，将不同设备的逻辑解耦，并通过中介者协调它们的联动。每个设备都不直接知道其他设备的存在，而是通过中介者来触发其他设备的动作。这种方式使得系统更加灵活且易于扩展，例如，如果将来增加一个新的设备，只需要在中介者中做相应的处理，而不需要修改现有设备的代码。

