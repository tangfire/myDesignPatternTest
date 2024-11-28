package org.example.Abstract_Factory_Pattern;

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
