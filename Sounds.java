import javax.sound.sampled.*;
import java.io.*;
import java.util.HashMap;

public class Sounds { //Easy access to sounds
    private static Clip meowHappy, meowSad, backgroundMusic;
    private static Clip clickSound;
    private static boolean soundsOn, musicOn;
    public static void loadSounds(){
        try{
            AudioInputStream temp = AudioSystem.getAudioInputStream(new File("meowHappy.wav").getAbsoluteFile());
            meowHappy = AudioSystem.getClip();
            meowHappy.open(temp);
        }catch (Exception e){
            System.out.println("Error " + e);
        }

        try{
            AudioInputStream temp = AudioSystem.getAudioInputStream(new File("meowAngry.wav").getAbsoluteFile());
            meowSad = AudioSystem.getClip();
            meowSad.open(temp);
        }catch (Exception e){
            System.out.println("Error " + e);
        }

        try{
            AudioInputStream temp = AudioSystem.getAudioInputStream(new File("backgroundMusic.wav").getAbsoluteFile());
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(temp);
            FloatControl gain = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(-10.0f);
        }catch (Exception e){
            System.out.println("Error " + e);
            e.printStackTrace();
        }
        try{
            AudioInputStream temp = AudioSystem.getAudioInputStream(new File("click.wav").getAbsoluteFile());
            clickSound = AudioSystem.getClip();
            clickSound.open(temp);
        }catch (Exception e){
            System.out.println("Error " + e);
        }

        soundsOn = true;
        musicOn = true;
    }
    public static boolean changeSoundsOn(){ //Changes master sound to play or mute
        soundsOn = !soundsOn;
        if (!soundsOn){
            backgroundMusic.stop();
        }
        else{
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
        return soundsOn;
    }
    public static void changeMusicOn(){ //Changes music to play or mute
        musicOn = !musicOn;
        if (!musicOn){
            backgroundMusic.stop();
        }
        else{
            if (soundsOn){
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else{
                musicOn = false;
            }
        }
    }
    public static boolean getSoundsOn(){
        return soundsOn;
    }
    public static boolean getMusicOn(){
        return musicOn;
    }
    public static void playMeowHappy(){
        meowHappy.setFramePosition(0);
        meowHappy.start();
    }
    public static void playMeowAngry(){
        meowSad.setFramePosition(0);
        meowSad.start();
    }
    public static void playClick(){
        clickSound.setFramePosition(0);
        clickSound.start();
    }
    public static void stopClick(){
        clickSound.stop();
    }
    public static void startBackgroundMusic(){
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stopBackgroundMusic(){
        backgroundMusic.stop();
    }
}
