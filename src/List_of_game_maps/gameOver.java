package List_of_game_maps;

import List_of_ammos.AmmoBlu1;
import List_of_fighter_aircrafts.CombatAircraft;
import java.awt.Graphics;
import java.awt.Image;
import List_of_sounds.*;
import java.io.File;
import javax.imageio.ImageIO;


public class gameOver {
    private Image gameover;
    private sounds_gameOver sounds;
    
    public gameOver(){
        this.sounds = new sounds_gameOver();
        
        try{
            this.gameover = ImageIO.read(new File("images_game/gameover.png"));
        }catch(Exception e){}
    }
    
    public void paint(Graphics g){
        g.drawImage(this.gameover, 0, 0, 1000, 800, null);
        this.sounds.playSound();
        
        CombatAircraft.GO_UP = 0;
        CombatAircraft.GO_DOWN = 0;
        CombatAircraft.GO_UP = 0;
        CombatAircraft.GO_DOWN = 0;
        
        for(int i=0; i<8; i++){
            AmmoBlu1.shooting[i] = 0;
            AmmoBlu1.left[i] = 0;
            AmmoBlu1.right[i] = 0;
            AmmoBlu1.flyHitCheck[i] = false;
        }
    }
}
