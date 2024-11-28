
## 4．建造者模式

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


