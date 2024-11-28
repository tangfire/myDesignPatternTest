package org.example.Mediator_Pattern;

interface Device {
    void setMediator(SmartHomeMediator mediator);  // 设置中介者
    void executeAction();  // 执行设备的特定行为
}


class SmartHomeMediator {
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

class Alarm implements Device {
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


class CoffeeMachine implements Device {
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


class TV implements Device {
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

class Curtains implements Device {
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
