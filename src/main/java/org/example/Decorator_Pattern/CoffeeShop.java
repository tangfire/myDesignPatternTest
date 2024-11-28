package org.example.Decorator_Pattern;

interface Coffee {
    double cost();  // 计算咖啡的价格
}


class Americano implements Coffee {
    @Override
    public double cost() {
        return 5.0;  // 美式咖啡的价格
    }
}

class Espresso implements Coffee {
    @Override
    public double cost() {
        return 8.0;  // 意大利咖啡的价格
    }
}


abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;  // 被装饰的咖啡对象

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double cost() {
        return coffee.cost();  // 默认的计算方式，子类会覆盖这个方法
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 2.0;  // 加上牛奶的价格
    }
}

class SugarDecorator extends CoffeeDecorator {
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



public class CoffeeShop {
    public static void main(String[] args) {
        Coffee coffee = new Americano();  // 选择一杯美式咖啡
        coffee = new MilkDecorator(coffee);  // 添加1份牛奶
        coffee = new SugarDecorator(coffee, 2);  // 添加2份方糖

        System.out.println("Total cost: " + coffee.cost() + "元");  // 输出最终价格
    }
}
