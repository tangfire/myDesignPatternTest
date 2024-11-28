package org.example.Template_Pattern;

// 抽象类 BankService，定义模板方法和步骤
abstract class BankService {

    // 模板方法，定义办理业务的固定流程
    public final void processService() {
        takeNumber();           // 取号
        performBusiness();      // 办理业务
        rateClerk();            // 柜员评价
    }

    // 取号
    private void takeNumber() {
        System.out.println("取号排队");
    }

    // 具体的办理业务，由子类实现
    protected abstract void performBusiness();

    // 柜员评价
    private void rateClerk() {
        System.out.println("评价柜员服务质量");
    }
}

// 具体银行A的服务实现
class BankAService extends BankService {

    @Override
    protected void performBusiness() {
        System.out.println("Bank A：办理业务 - 存款");
    }
}

// 具体银行B的服务实现
class BankBService extends BankService {

    @Override
    protected void performBusiness() {
        System.out.println("Bank B：办理业务 - 贷款");
    }
}

// 测试
public class TemplateMethodPatternDemo {
    public static void main(String[] args) {
        BankService bankAService = new BankAService();
        BankService bankBService = new BankBService();

        System.out.println("=== Bank A 服务流程 ===");
        bankAService.processService(); // 执行银行A的服务流程

        System.out.println("\n=== Bank B 服务流程 ===");
        bankBService.processService(); // 执行银行B的服务流程
    }
}
