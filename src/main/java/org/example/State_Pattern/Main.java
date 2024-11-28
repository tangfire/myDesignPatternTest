package org.example.State_Pattern;


// 状态接口，定义状态的行为
interface State {
    void handleRequest(TrafficLightContext context);  // 每个状态都会调用此方法来切换到下一个状态
}


// 绿灯状态类
class GreenLightState implements State {
    @Override
    public void handleRequest(TrafficLightContext context) {
        System.out.println("绿灯亮起，车辆可以通行！");
        // 绿灯结束后切换到黄灯
        context.setState(new YellowLightState());
    }
}


// 黄灯状态类
class YellowLightState implements State {
    @Override
    public void handleRequest(TrafficLightContext context) {
        System.out.println("黄灯亮起，注意准备停车！");
        // 黄灯结束后切换到红灯
        context.setState(new RedLightState());
    }
}


// 红灯状态类
class RedLightState implements State {
    @Override
    public void handleRequest(TrafficLightContext context) {
        System.out.println("红灯亮起，车辆停下！");
        // 红灯结束后切换到绿灯
        context.setState(new GreenLightState());
    }
}


// 上下文类，维护当前状态并根据状态变化执行不同的行为
class TrafficLightContext {
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
