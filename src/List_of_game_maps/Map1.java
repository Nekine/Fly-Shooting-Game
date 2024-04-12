
package List_of_game_maps;

import List_of_ammos.Level1_Ammo;
import List_of_fighter_aircrafts.CombatAircraft;
import List_of_space_flies.*;
import galacticskywars.GameScreen;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Map1 {
    private Image background;  
    private CombatAircraft aircraft;
    
    public Level1_Flies fly;
    
    public static boolean checkStart;
    public static boolean checkWin;
    public static boolean switch_to_map2;

    public Map1() {
        Map1.checkWin = false;
        Map1.checkStart = false;
        Map1.switch_to_map2 = false;
        
        this.aircraft = new CombatAircraft();
        this.fly = new Level1_Flies();
    }
    
    public void paint(Graphics g){
        this.paintBG(g);
        if(CombatAircraft.checkMove == 0){
            this.aircraft.paint(g);
        }
        else{
            this.aircraft.painMove(g);
        }
        if(Map1.checkStart){
            this.firstRound(g);
        }
       
    }
    
    public void paintBG(Graphics g){
        try{
            this.background = ImageIO.read(new File("S675/World3_Space.png"));
            g.drawImage(this.background, 0, 0, 1000, 800, null);
        }catch(Exception e){}   
    }
    
    public void startGame(){
        if (GameScreen.checkPlay) {
            if (CombatAircraft.GO_UP < 70) {
                CombatAircraft.GO_UP += 5;
            }

            if (CombatAircraft.GO_UP == 70) {
                Map1.checkStart = true;
                Map1.checkWin = true; // NOTE
            }
        }
    }
    
    public void firstRound(Graphics g){
        this.fly.paint(g);
        
        boolean check = false;
        for(int i=0; i<18; i++){
            if(Level1_Flies.checkDie[i] < 3){
                check = true;
            }
        }
        if(!check){
            Map1.checkWin = true;
        }
    }
    
    public void win(){
        if(700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN > -70){
            CombatAircraft.GO_UP += 15;
        }
        else{
            Map1.switch_to_map2 = true;
            CombatAircraft.GO_UP = 0;
            CombatAircraft.GO_DOWN = 0;
        }
        
        for(int i=0; i<8; i++){
            Level1_Ammo.shooting[i] = 0;
            Level1_Ammo.left[i] = 0;
            Level1_Ammo.right[i] = 0;
            Level1_Ammo.flyHitCheck[i] = false;
        }
    }
}
