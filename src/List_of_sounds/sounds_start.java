
package List_of_sounds;

import galacticskywars.GameScreen;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class sounds_start extends Thread{
    private AudioInputStream mainAudio;
    private AudioInputStream playingAudio;
    private AudioInputStream click_select_aircraft;
    private AudioInputStream click_play_button;
    
    private Clip playAudioMain;
    private Clip playAudioClickSelect;
    private Clip playAudioClickPlayButton;

    public sounds_start() {
        try{
            this.mainAudio = AudioSystem.getAudioInputStream(new File("S675/audio/main_menu.wav")); 
            this.click_select_aircraft = AudioSystem.getAudioInputStream(new File("S675/audio/tap.wav"));
            this.click_play_button = AudioSystem.getAudioInputStream(new File("S675/audio/pew_pew.wav"));
            this.playingAudio = AudioSystem.getAudioInputStream(new File("S675/audio/"));
            this.playAudioMain = AudioSystem.getClip();
            this.playAudioClickSelect = AudioSystem.getClip();
            this.playAudioClickPlayButton = AudioSystem.getClip();
            
        }catch(Exception e){}

    }
    
    
    public void mainSound(){
        try {
            if(this.playAudioMain.isOpen() && GameScreen.checkPlay == true){
                this.playAudioMain.close();
            }
            else{
                this.playAudioMain.open(this.mainAudio);
                this.playAudioMain.start();
            }
        } catch (Exception e) {}        
    }
    
    public void clickSelectAircraft(){
        try {
            if (this.playAudioClickSelect.isOpen()) {
                // Đặt vị trí của Clip về đầu trước khi phát lại
                this.playAudioClickSelect.setFramePosition(0);
                // Bắt đầu phát âm thanh
                this.playAudioClickSelect.start();
            } 
            else {
                // Nếu Clip chưa được mở, mở nó trước
                this.playAudioClickSelect.open(this.click_select_aircraft);
                // Đặt vị trí của Clip về đầu
                this.playAudioClickSelect.setFramePosition(0);
                // Bắt đầu phát âm thanh
                this.playAudioClickSelect.start();
            }
        } catch (Exception e) {} 
    }
    
    public void playSound(){
        try {
            if (this.playAudioClickPlayButton.isOpen()) {
                // Đặt vị trí của Clip về đầu trước khi phát lại
                this.playAudioClickPlayButton.setFramePosition(0);
                // Bắt đầu phát âm thanh
                this.playAudioClickPlayButton.start();
            } 
            else {
                // Nếu Clip chưa được mở, mở nó trước
                this.playAudioClickPlayButton.open(this.click_play_button);
                // Đặt vị trí của Clip về đầu
                this.playAudioClickPlayButton.setFramePosition(0);
                // Bắt đầu phát âm thanh
                this.playAudioClickPlayButton.start();
            }
        } catch (Exception e) {} 
    }

}
