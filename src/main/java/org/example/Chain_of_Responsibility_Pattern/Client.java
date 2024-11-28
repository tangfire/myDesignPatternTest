package org.example.Chain_of_Responsibility_Pattern;

// 1. 创建审批者接口（Handler）
interface Approver {
    void setNextApprover(Approver approver); // 设置下一个审批者
    void approve(int amount);  // 处理审批请求
}

// 2. 创建具体的审批者类
// 主管审批
class Supervisor implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver approver) {
        this.nextApprover = approver;
    }

    @Override
    public void approve(int amount) {
        if (amount <= 5000) {
            System.out.println("主管审批通过，采购金额：" + amount);
        } else if (nextApprover != null) {
            nextApprover.approve(amount); // 传递给下一个审批者
        }
    }
}

// 部门经理审批
class DepartmentManager implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver approver) {
        this.nextApprover = approver;
    }

    @Override
    public void approve(int amount) {
        if (amount <= 10000) {
            System.out.println("部门经理审批通过，采购金额：" + amount);
        } else if (nextApprover != null) {
            nextApprover.approve(amount); // 传递给下一个审批者
        }
    }
}

// 副总审批
class VicePresident implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver approver) {
        this.nextApprover = approver;
    }

    @Override
    public void approve(int amount) {
        if (amount <= 30000) {
            System.out.println("副总审批通过，采购金额：" + amount);
        } else if (nextApprover != null) {
            nextApprover.approve(amount); // 传递给下一个审批者
        }
    }
}

// 总经理审批
class GeneralManager implements Approver {
    @Override
    public void setNextApprover(Approver approver) {
        // 总经理是链中的最后一个审批者，不需要设置下一个审批者
    }

    @Override
    public void approve(int amount) {
        System.out.println("总经理审批通过，采购金额：" + amount);
    }
}

// 3. 客户端代码
public class Client {
    public static void main(String[] args) {
        // 创建各个审批者
        Approver supervisor = new Supervisor();
        Approver departmentManager = new DepartmentManager();
        Approver vicePresident = new VicePresident();
        Approver generalManager = new GeneralManager();

        // 设置职责链
        supervisor.setNextApprover(departmentManager);
        departmentManager.setNextApprover(vicePresident);
        vicePresident.setNextApprover(generalManager);

        // 测试不同的采购金额
        System.out.println("申请采购金额 4000：");
        supervisor.approve(4000);  // 主管审批通过

        System.out.println("\n申请采购金额 8000：");
        supervisor.approve(8000);  // 部门经理审批通过

        System.out.println("\n申请采购金额 15000：");
        supervisor.approve(15000); // 副总审批通过

        System.out.println("\n申请采购金额 35000：");
        supervisor.approve(35000); // 总经理审批通过
    }
}
