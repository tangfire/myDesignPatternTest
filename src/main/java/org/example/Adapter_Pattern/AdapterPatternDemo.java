package org.example.Adapter_Pattern;

// 步骤 1: 创建一个媒体播放器接口
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// 步骤 2: 创建 MP3 播放器实现类
class Mp3Player implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing MP3 file. Name: " + fileName);
        } else {
            System.out.println("MP3 Player does not support " + audioType + " format.");
        }
    }
}

// 步骤 3: 创建 MP4 播放器实现类
class Mp4Player implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp4")){
            System.out.println("Playing MP4 file. Name: " + fileName);
        } else {
            System.out.println("MP4 Player does not support " + audioType + " format.");
        }
    }
}

// 步骤 4: 创建适配器类，用来适配 MP4 播放器
class Mp4Adapter implements MediaPlayer {
    private Mp4Player mp4Player;

    public Mp4Adapter(Mp4Player mp4Player) {
        this.mp4Player = mp4Player;
    }

    @Override
    public void play(String audioType, String fileName) {
        mp4Player.play(audioType, fileName);  // 通过适配器调用 MP4 播放器的方法
    }
}

// 步骤 5: 创建一个 AudioPlayer 类来协调 MP3 和 MP4 播放
class AudioPlayer implements MediaPlayer {
    private MediaPlayer mp3Player;
    private MediaPlayer mp4Adapter;

    public AudioPlayer() {
        mp3Player = new Mp3Player();
        mp4Adapter = new Mp4Adapter(new Mp4Player());
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            mp3Player.play(audioType, fileName);
        } else if(audioType.equalsIgnoreCase("mp4")){
            mp4Adapter.play(audioType, fileName);  // 适配器处理 MP4 播放
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}

// 步骤 6: 客户端代码，进行测试
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        // 播放 MP3 文件
        audioPlayer.play("mp3", "song1.mp3");

        // 播放 MP4 文件，注意我们通过 MP3 播放器来播放 MP4 文件
        audioPlayer.play("mp4", "movie1.mp4");

        // 测试不支持的格式
        audioPlayer.play("avi", "video.avi");
    }
}
