
package galacticskywars;

import List_of_fighter_aircrafts.CombatAircraft;
import List_of_game_maps.Map4;
import List_of_game_maps.winGame;
import List_of_sounds.*;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;



public class StartScreen{
    private Image welcomeGame;
    private Image nextIcon;
    private Image prevIcon;
    private Image playButton;
    private sounds_start audio;
    
    public CombatAircraft aircraft;

    public StartScreen() {
        try{
            this.welcomeGame = ImageIO.read(new File("images_game/WOS.png"));
            this.nextIcon = ImageIO.read(new File("S332/Sprite/bt-next-ICON-BOOSTER-VIP-BOARD.png"));
            this.prevIcon = ImageIO.read(new File("S332/Sprite/bt-next-ICON-BOOSTER-VIP-BOARD-2.png"));
            this.playButton = ImageIO.read(new File("S675/aircraft/play_button.png"));
            
        }catch(Exception e){}
        
        this.aircraft = new CombatAircraft();
        this.audio = new sounds_start();
    }
     
    public void paint(Graphics g){ 
        g.drawImage(this.welcomeGame, 190, 50, 600, 400, null);
        g.drawImage(this.prevIcon, 360, 500, 30, 40, null);
        g.drawImage(this.nextIcon, 590, 500, 30, 40, null);
        if(!Map4.switch_to_map5 || winGame.check){
            g.drawImage(this.playButton, 430, 600, 120, 60, null);
        }
        this.aircraft.paint(g);
    }
       
    public void playAudio_Main(){
        this.audio.mainSound();
    }
    
    public void playAudio_ClickSelectAircraft(){
        this.audio.clickSelectAircraft();
    }
    
    public void playAudio_ClickPlayButton(){
        this.audio.playSound();
    }
}
