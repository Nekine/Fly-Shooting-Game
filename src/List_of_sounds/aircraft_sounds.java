package List_of_sounds;

import List_of_fighter_aircrafts.CombatAircraft;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class aircraft_sounds extends Thread{
    private AudioInputStream gunshot;
    private AudioInputStream aitcraftDie;
    
    private Clip playSound_gunshot;
    private Clip playSound_aircraftDie;
    
    public aircraft_sounds(){
        try{
            this.gunshot = AudioSystem.getAudioInputStream(new File("S675/audio/umgah-backzip.wav")); 
            this.aitcraftDie = AudioSystem.getAudioInputStream(new File("S675/audio/player_die.wav"));
            
            this.playSound_gunshot = AudioSystem.getClip();
            this.playSound_aircraftDie = AudioSystem.getClip();
        }catch(Exception e){}
    }
    
    public void gunshotSound(){
        try {
            if (this.playSound_gunshot.isOpen()) {
                // Đặt vị trí của Clip về đầu trước khi phát lại
                this.playSound_gunshot.setFramePosition(0);
                // Bắt đầu phát âm thanh
                this.playSound_gunshot.start();
            } 
            else {
                // Nếu Clip chưa được mở, mở nó trước
                this.playSound_gunshot.open(this.gunshot);
                // Đặt vị trí của Clip về đầu
                this.playSound_gunshot.setFramePosition(0);
                // Bắt đầu phát âm thanh
                this.playSound_gunshot.start();
            }
        } catch (Exception e) {}
    }
    
    public void aircraftDieSound(){
        try {
            if(this.playSound_aircraftDie.isOpen() && CombatAircraft.itemDie == 7){
                this.playSound_aircraftDie.close();
            }
            else{
                this.playSound_aircraftDie.open(this.aitcraftDie);
                this.playSound_aircraftDie.start();
            }
        } catch (Exception e) {}
    }
}
