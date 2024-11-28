package org.example.Composite_Pattern;

import java.util.ArrayList;
import java.util.List;

// Step 1: 创建一个抽象组件接口
interface Component {
    void display();
    void add(Component component);
    void remove(Component component);
}

// Step 2: 创建学院类（Composite）
class College implements Component {
    private String name;
    private List<Component> departments = new ArrayList<>();

    public College(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("学院: " + name);
        for (Component department : departments) {
            department.display();
        }
    }

    @Override
    public void add(Component component) {
        departments.add(component);
    }

    @Override
    public void remove(Component component) {
        departments.remove(component);
    }
}

// Step 3: 创建学科专业类（Leaf）
class Department implements Component {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("学科专业: " + name);
    }

    @Override
    public void add(Component component) {
        // 学科专业不能包含其他组件，忽略此操作
    }

    @Override
    public void remove(Component component) {
        // 学科专业不能包含其他组件，忽略此操作
    }
}

// Step 4: 创建大学类（Composite）
class University implements Component {
    private String name;
    private List<Component> colleges = new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("大学: " + name);
        for (Component college : colleges) {
            college.display();
        }
    }

    @Override
    public void add(Component component) {
        colleges.add(component);
    }

    @Override
    public void remove(Component component) {
        colleges.remove(component);
    }
}

// Step 5: 客户端代码
public class CompositePatternDemo {
    public static void main(String[] args) {
        // 创建学科专业
        Department csDepartment = new Department("计算机科学与技术");
        Department eeDepartment = new Department("电气工程");
        Department mechDepartment = new Department("机械工程");

        // 创建学院并添加学科专业
        College engineeringCollege = new College("工程学院");
        engineeringCollege.add(csDepartment);
        engineeringCollege.add(eeDepartment);
        engineeringCollege.add(mechDepartment);

        Department econDepartment = new Department("经济学");
        Department lawDepartment = new Department("法学");

        // 创建另一个学院并添加学科专业
        College socialScienceCollege = new College("社会科学学院");
        socialScienceCollege.add(econDepartment);
        socialScienceCollege.add(lawDepartment);

        // 创建大学并添加学院
        University university = new University("某大学");
        university.add(engineeringCollege);
        university.add(socialScienceCollege);

        // 展示大学信息
        university.display();
    }
}
