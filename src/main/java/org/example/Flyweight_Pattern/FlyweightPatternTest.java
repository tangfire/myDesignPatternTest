package org.example.Flyweight_Pattern;


import java.util.HashMap;
import java.util.Map;

// 共享单车类
class Bike {
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


// 单车租赁系统，享元模式的核心
class BikeRental {
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

// 租赁者类，持有用户信息
class User {
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
