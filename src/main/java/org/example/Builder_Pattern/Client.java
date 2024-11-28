package org.example.Builder_Pattern;

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
