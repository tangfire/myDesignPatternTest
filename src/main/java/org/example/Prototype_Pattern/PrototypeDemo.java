package org.example.Prototype_Pattern;

import java.util.HashMap;
import java.util.Map;

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
