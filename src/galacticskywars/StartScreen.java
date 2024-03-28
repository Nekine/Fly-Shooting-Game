
package galacticskywars;

import List_of_sounds.*;
import List_of_fighter_aircraft.*;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;



public class StartScreen{
    private Image welcomeGame;
    private Image nextIcon;
    private Image prevIcon;
    private Image playButton;
    private CombatAircraft aircraft;
    private sounds_start audio;
    
    public static int index;
    public static int item;

    public StartScreen() {
        try{
            this.welcomeGame = ImageIO.read(new File("images_game/WOS.png"));
            this.nextIcon = ImageIO.read(new File("S332/Sprite/bt-next-ICON-BOOSTER-VIP-BOARD.png"));
            this.prevIcon = ImageIO.read(new File("S332/Sprite/bt-next-ICON-BOOSTER-VIP-BOARD-2.png"));
            this.playButton = ImageIO.read(new File("S332/Sprite/play.png"));
            
        }catch(Exception e){}
        
        this.aircraft = new CombatAircraft();
        this.audio = new sounds_start();
        StartScreen.index = 0;
        StartScreen.item = 0;
    }
     
    public void paint(Graphics g){ 
        g.drawImage(this.welcomeGame, 190, 50, 600, 400, null);
        g.drawImage(this.prevIcon, 360, 500, 30, 40, null);
        g.drawImage(this.nextIcon, 590, 500, 30, 40, null);
        g.drawImage(this.playButton, 340, 530, 290, 250, null);
        if(!GameScreen.checkPlay){
            this.aircraft.paint(g, StartScreen.index, StartScreen.item);
        }
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
