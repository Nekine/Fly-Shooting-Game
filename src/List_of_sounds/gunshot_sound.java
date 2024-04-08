package List_of_sounds;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class gunshot_sound extends Thread{
    private AudioInputStream gunshot;
    
    private Clip playSound;
    
    public gunshot_sound(){
        try{
            this.gunshot = AudioSystem.getAudioInputStream(new File("S675/audio/umgah-backzip.wav")); 
            
            this.playSound = AudioSystem.getClip();
        }catch(Exception e){}
    }
    
    public void gunshotSound(){
        try {
            if (this.playSound.isOpen()) {
                // Đặt vị trí của Clip về đầu trước khi phát lại
                this.playSound.setFramePosition(0);
                // Bắt đầu phát âm thanh
                this.playSound.start();
            } 
            else {
                // Nếu Clip chưa được mở, mở nó trước
                this.playSound.open(this.gunshot);
                // Đặt vị trí của Clip về đầu
                this.playSound.setFramePosition(0);
                // Bắt đầu phát âm thanh
                this.playSound.start();
            }
        } catch (Exception e) {}
    }
}
