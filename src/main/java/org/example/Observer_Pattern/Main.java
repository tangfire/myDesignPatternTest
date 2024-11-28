package org.example.Observer_Pattern;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String videoTitle);  // 当有新视频时，推送通知给用户
}

interface Subject {
    void addObserver(Observer observer);   // 添加观察者
    void removeObserver(Observer observer); // 移除观察者
    void notifyObservers();                // 通知所有观察者
}



class UpMaster implements Subject {
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


class User implements Observer {
    private String name;  // 用户的名字

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(name + " received notification: " + videoTitle);  // 用户接收到推送
    }
}


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
