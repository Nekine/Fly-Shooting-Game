
package List_of_sounds;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class sounds_gameOver {
    private AudioInputStream gameover;
    
    private Clip playGameOver;
    
    public sounds_gameOver(){
        try{
            this.gameover = AudioSystem.getAudioInputStream(new File("S675/audio/gameover.wav"));
            this.playGameOver = AudioSystem.getClip();
        }catch(Exception e){}
    }
    
    public void playSound(){
        try{
            this.playGameOver.open(this.gameover);
            this.playGameOver.start();
        }catch(Exception e){}
    }
}
