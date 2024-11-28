package org.example.Strategy_Pattern;

interface PronunciationStrategy {
    void pronounce(String word);  // 发音方法，传入单词进行发音
}


class ChinesePronunciationStrategy implements PronunciationStrategy {
    @Override
    public void pronounce(String word) {
        System.out.println("用汉语发音：" + word);
    }
}

class EnglishPronunciationStrategy implements PronunciationStrategy {
    @Override
    public void pronounce(String word) {
        System.out.println("Pronounced in English: " + word);
    }
}

class WordPlayer {
    private PronunciationStrategy pronunciationStrategy;  // 持有策略的引用

    // 构造方法，初始化默认的发音策略
    public WordPlayer(PronunciationStrategy pronunciationStrategy) {
        this.pronunciationStrategy = pronunciationStrategy;
    }

    // 设置新的发音策略
    public void setPronunciationStrategy(PronunciationStrategy pronunciationStrategy) {
        this.pronunciationStrategy = pronunciationStrategy;
    }

    // 播放单词
    public void playWord(String word) {
        pronunciationStrategy.pronounce(word);  // 使用当前策略发音
    }
}




public class TestDictionary {
    public static void main(String[] args) {
        // 创建发音策略
        PronunciationStrategy chineseStrategy = new ChinesePronunciationStrategy();
        PronunciationStrategy englishStrategy = new EnglishPronunciationStrategy();
        
        // 创建词典播放器，初始为中文发音
        WordPlayer wordPlayer = new WordPlayer(chineseStrategy);
        
        // 播放单词
        wordPlayer.playWord("苹果");  // 输出：用汉语发音：苹果
        
        // 更换为英文发音
        wordPlayer.setPronunciationStrategy(englishStrategy);
        wordPlayer.playWord("Apple");  // 输出：Pronounced in English: Apple
        
        // 再次更换为中文发音
        wordPlayer.setPronunciationStrategy(chineseStrategy);
        wordPlayer.playWord("香蕉");  // 输出：用汉语发音：香蕉
    }
}
