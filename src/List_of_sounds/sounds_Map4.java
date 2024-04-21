
package List_of_sounds;

import List_of_fighter_aircrafts.CombatAircraft;
import List_of_game_maps.*;
import List_of_space_flies.Level4_FinalBoss;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class sounds_Map4 {
    private AudioInputStream warningBoss;
    private AudioInputStream bossDie;
    private AudioInputStream bossBG;
    
    private Clip PlayWarnigBoss;
    private Clip playBossDie;
    private Clip PlayBossBG;
    
    public sounds_Map4(){
        try{
            this.warningBoss = AudioSystem.getAudioInputStream(new File("S675/audio/warning_boss.wav"));
            this.bossBG = AudioSystem.getAudioInputStream(new File("S675/audio/boss_bg.wav"));
            this.bossDie = AudioSystem.getAudioInputStream(new File("S675/audio/bossdie.wav"));
            this.PlayWarnigBoss = AudioSystem.getClip();
            this.PlayBossBG = AudioSystem.getClip();
            this.playBossDie = AudioSystem.getClip();
        }catch(Exception e){}
    }
    
    public void playSounds(){
        try{
            if(this.PlayWarnigBoss.isOpen() && Level4_FinalBoss.DO_DOWN >= 30){
                this.PlayWarnigBoss.close();
                //
                if(this.PlayBossBG.isOpen() && (Map4.checkWin || CombatAircraft.checkDie >= 3)){
                    this.PlayBossBG.close();
                }
                else{
                    this.PlayBossBG.open(this.bossBG);
                    this.PlayBossBG.start();
                }
                //
                if(Level4_FinalBoss.checkDie >= 100){
                    this.playBossDie.open(this.bossDie);
                    this.playBossDie.start();
                }
            }
            else{
                this.PlayWarnigBoss.open(this.warningBoss);
                this.PlayWarnigBoss.start();
            }
        }catch(Exception e){}
    }
    
}
