package org.example.Command_Pattern;

// 命令接口
interface Command {
    void execute();
}

// 具体命令类：做菜命令
class CookDishCommand implements Command {
    private Chef chef;
    private String dishName;

    public CookDishCommand(Chef chef, String dishName) {
        this.chef = chef;
        this.dishName = dishName;
    }

    @Override
    public void execute() {
        chef.cook(dishName); // 执行厨师的做菜操作
    }
}

// 命令接收者：厨师
class Chef {
    public void cook(String dishName) {
        System.out.println("厨师正在做: " + dishName);
    }
}

// 请求发送者：服务员
class Waiter {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void placeOrder() {
        System.out.println("服务员接受订单...");
        command.execute(); // 执行命令
    }
}

// 客户端代码：测试系统
public class Restaurant {
    public static void main(String[] args) {
        // 创建厨师对象
        Chef chef = new Chef();

        // 创建命令对象
        Command pizzaCommand = new CookDishCommand(chef, "披萨");
        Command pastaCommand = new CookDishCommand(chef, "意大利面");

        // 创建服务员并设置命令
        Waiter waiter = new Waiter();

        // 服务员接受订单并执行
        waiter.setCommand(pizzaCommand);
        waiter.placeOrder();  // 输出：服务员接受订单... 厨师正在做: 披萨

        waiter.setCommand(pastaCommand);
        waiter.placeOrder();  // 输出：服务员接受订单... 厨师正在做: 意大利面
    }
}
