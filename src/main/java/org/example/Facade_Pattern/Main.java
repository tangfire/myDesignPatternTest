package org.example.Facade_Pattern;

// Step 1: 模拟CPU启动
class CPU {
    public void start() {
        System.out.println("CPU启动...");
    }
}

// Step 2: 模拟内存自检
class Memory {
    public void selfCheck() {
        System.out.println("内存自检...");
    }
}

// Step 3: 模拟硬盘读取
class HardDrive {
    public void readData() {
        System.out.println("硬盘读取...");
    }
}

// Step 4: 模拟操作系统加载
class OperatingSystem {
    public void load() {
        System.out.println("操作系统加载...");
    }
}

// Step 5: 外观类，简化开机过程
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    private OperatingSystem os;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
        this.os = new OperatingSystem();
    }

    // 提供统一的开机接口
    public void startComputer() {
        System.out.println("电脑开机启动...");
        cpu.start();          // 启动CPU
        memory.selfCheck();   // 内存自检
        hardDrive.readData(); // 硬盘读取
        os.load();            // 加载操作系统
        System.out.println("开机完成!");
    }
}

// 测试代码
public class Main {
    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        // 用户调用外观类，简化开机过程
        computerFacade.startComputer();
    }
}
