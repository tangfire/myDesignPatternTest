package org.example.Memento_Pattern;


import java.util.Stack;

// 备忘录类，用于保存游戏状态
class GameState {
    private int level;     // 游戏关卡
    private int score;     // 游戏分数
    private String playerName;  // 玩家名称

    public GameState(int level, int score, String playerName) {
        this.level = level;
        this.score = score;
        this.playerName = playerName;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "Player: " + playerName + ", Level: " + level + ", Score: " + score;
    }
}


// 发起人类，管理游戏状态并生成备忘录
class Game {
    private int level;
    private int score;
    private String playerName;

    public Game(String playerName) {
        this.playerName = playerName;
        this.level = 1;
        this.score = 0;
    }

    // 设置游戏状态
    public void setState(int level, int score) {
        this.level = level;
        this.score = score;
    }

    // 创建备忘录
    public GameState saveState() {
        return new GameState(level, score, playerName);
    }

    // 恢复游戏状态
    public void restoreState(GameState state) {
        this.level = state.getLevel();
        this.score = state.getScore();
    }

    // 获取当前游戏状态
    public void printState() {
        System.out.println("Game State: Player " + playerName + " is at Level " + level + " with Score " + score);
    }
}


// 管理者类，负责存档和恢复存档

class GameManager {
    private Stack<GameState> savedStates = new Stack<>();

    // 保存游戏状态
    public void saveState(GameState state) {
        savedStates.push(state);
    }

    // 恢复到上一个存档
    public GameState restoreState() {
        if (savedStates.isEmpty()) {
            System.out.println("No saved game state!");
            return null;
        }
        return savedStates.pop();
    }
}




// 测试类，模拟游戏存档和恢复
public class GameTest {
    public static void main(String[] args) {
        Game game = new Game("Player1");

        // 创建一个游戏管理器
        GameManager manager = new GameManager();

        // 玩家进行游戏
        game.setState(2, 100);
        game.printState();  // 输出当前状态
        manager.saveState(game.saveState());  // 保存当前状态

        // 玩家继续游戏
        game.setState(3, 200);
        game.printState();  // 输出当前状态

        // 玩家决定恢复游戏
        GameState savedState = manager.restoreState();
        if (savedState != null) {
            game.restoreState(savedState);
            game.printState();  // 恢复到存档时的状态
        }

        // 再次恢复游戏状态
        savedState = manager.restoreState();
        if (savedState != null) {
            game.restoreState(savedState);
            game.printState();  // 再次恢复状态
        }
    }
}
