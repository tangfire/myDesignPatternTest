package org.example.Bridge_Pattern;

// 手机的基本功能抽象类
abstract class Phone {
    protected PhoneType phoneType;  // 使用桥接模式，将具体机型的操作委托给phoneType
    
    public Phone(PhoneType phoneType) {
        this.phoneType = phoneType;
    }
    
    public abstract void makeCall();
}

// 手机的实现接口，定义各种手机操作
interface PhoneType {
    void makeCall();
}

// 小米手机的实现类
class MiPhone implements PhoneType {
    @Override
    public void makeCall() {
        System.out.println("小米手机拨打电话...");
    }
}

// VIVO手机的实现类
class VivoPhone implements PhoneType {
    @Override
    public void makeCall() {
        System.out.println("VIVO手机拨打电话...");
    }
}

// 直板机型的实现类
class BarPhone extends Phone {
    public BarPhone(PhoneType phoneType) {
        super(phoneType);
    }

    @Override
    public void makeCall() {
        System.out.print("直板机型，");
        phoneType.makeCall();  // 委托给具体的手机操作实现类
    }
}

// 翻盖机型的实现类
class FlipPhone extends Phone {
    public FlipPhone(PhoneType phoneType) {
        super(phoneType);
    }

    @Override
    public void makeCall() {
        System.out.print("翻盖机型，");
        phoneType.makeCall();  // 委托给具体的手机操作实现类
    }
}

// 测试类
public class BridgePatternTest {
    public static void main(String[] args) {
        // 创建具体机型和手机类型的组合
        Phone miBarPhone = new BarPhone(new MiPhone());
        Phone miFlipPhone = new FlipPhone(new MiPhone());
        Phone vivoBarPhone = new BarPhone(new VivoPhone());
        Phone vivoFlipPhone = new FlipPhone(new VivoPhone());

        // 打电话
        miBarPhone.makeCall();
        miFlipPhone.makeCall();
        vivoBarPhone.makeCall();
        vivoFlipPhone.makeCall();
    }
}
