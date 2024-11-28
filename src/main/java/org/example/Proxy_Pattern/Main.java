package org.example.Proxy_Pattern;

// 1. 房屋信息接口
interface House {
    void displayHouseInfo();
}

// 2. 真实房屋信息类
class RealHouse implements House {
    private String houseId;
    private String address;
    private int rent;
    private String landlordName;

    public RealHouse(String houseId, String address, int rent, String landlordName) {
        this.houseId = houseId;
        this.address = address;
        this.rent = rent;
        this.landlordName = landlordName;
    }

    @Override
    public void displayHouseInfo() {
        System.out.println("House ID: " + houseId);
        System.out.println("Address: " + address);
        System.out.println("Rent: " + rent);
        System.out.println("Landlord: " + landlordName);
    }
}

// 3. 房东类（可以用于管理房屋）
class Landlord {
    private String name;
    
    public Landlord(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 房东可以管理房屋信息
    public House createHouse(String houseId, String address, int rent) {
        return new RealHouse(houseId, address, rent, this.name);
    }
}

// 4. 租客类
class Tenant {
    private String name;

    public Tenant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 租客浏览房屋信息
    public void viewHouseInfo(House house) {
        house.displayHouseInfo();
    }
}

// 5. 代理类：房屋代理（用于访问和控制租房流程）
class HouseProxy implements House {
    private RealHouse realHouse;
    private Landlord landlord;
    private Tenant tenant;

    public HouseProxy(Landlord landlord, Tenant tenant, RealHouse realHouse) {
        this.landlord = landlord;
        this.tenant = tenant;
        this.realHouse = realHouse;
    }

    @Override
    public void displayHouseInfo() {
        if (checkTenantEligibility()) {
            System.out.println("Displaying house information with approval...");
            realHouse.displayHouseInfo();
        } else {
            System.out.println("Tenant is not eligible to view this house.");
        }
    }

    // 模拟检查租客是否合格的操作
    private boolean checkTenantEligibility() {
        // 假设租客需要满足某些条件才能查看房屋，例如信用评分等
        System.out.println("Checking eligibility for tenant: " + tenant.getName());
        return true; // 假设租客始终合格
    }

    // 可以在代理类中加入更多的功能，比如合同管理、支付验证等
    public void manageLease() {
        System.out.println("Managing lease contract and payment...");
        // 这里可以添加房屋租赁相关的逻辑，例如支付、合同签署等
    }
}

// 6. 客户端测试代码
public class Main {
    public static void main(String[] args) {
        // 创建房东和租客
        Landlord landlord = new Landlord("Alice");
        Tenant tenant = new Tenant("Bob");

        // 房东创建房屋信息
        RealHouse house = new RealHouse("H123", "123 Main St", 2000, landlord.getName());

        // 创建代理对象
        HouseProxy houseProxy = new HouseProxy(landlord, tenant, house);

        // 租客查看房屋信息
        tenant.viewHouseInfo(houseProxy);

        // 租客通过代理管理租赁流程
        houseProxy.manageLease();
    }
}
