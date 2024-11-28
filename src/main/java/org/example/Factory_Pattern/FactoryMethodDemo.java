package org.example.Factory_Pattern;

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