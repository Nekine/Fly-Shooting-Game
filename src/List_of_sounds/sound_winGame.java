
package List_of_sounds;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class sound_winGame {
    private AudioInputStream win;
    
    private Clip playWin;
    
    public sound_winGame(){
        try{
            this.win = AudioSystem.getAudioInputStream(new File("S675/audio/win.wav"));
            this.playWin = AudioSystem.getClip();
        }catch(Exception e){}
    }
    
    public void playSound(){
        try{
            this.playWin.open(this.win);
            this.playWin.start();
        }catch(Exception e){}
    }
}
