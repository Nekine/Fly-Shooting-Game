package List_of_game_maps;

import List_of_ammos.AmmoBlu1;
import List_of_fighter_aircrafts.*;
import List_of_space_flies.*;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Map4 {
    private Image background;
    private CombatAircraft aircraft;
    
    public Level4_FinalBoss boss;
        
    public static boolean checkStart;
    public static boolean checkWin;
    public static boolean switch_to_map5;
    
    public Map4(){
        Map4.checkStart = false;
        Map4.checkWin = false;
        Map4.switch_to_map5 = false;
        
        this.boss = new Level4_FinalBoss();
        
        this.aircraft = new CombatAircraft();
        try{
            this.background = ImageIO.read(new File("S675/15.png"));
        }catch(Exception e){}
    }
    
    public void paint(Graphics g){
        g.drawImage(this.background, 0, 0, 1000, 800, null);
        
        if(CombatAircraft.checkMove == 0){
            this.aircraft.paint(g);
        }
        else{
            this.aircraft.painMove(g);
        }
        
        if(Map4.checkStart){
            this.fourthRound(g);
        }
    }
    
    public void startGame(){
        CombatAircraft.GO_LEFT = 0;
        CombatAircraft.GO_RIGHT = 0;
        
        if (Map3.switch_to_map4) {
            if (CombatAircraft.GO_UP < 70) {
                CombatAircraft.GO_UP += 5;
            }

            if (CombatAircraft.GO_UP == 70) {
                Map4.checkStart = true;   
            }
        }
    }
    
    private void fourthRound(Graphics g){
       this.boss.paint(g);
       
       // vẽ đạn Boss
        if(Level4_FinalBoss.DO_DOWN >= 30){
            this.boss.paintAmmoFly(g);
        }
    }
    
    public void win(){
        if(700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN > -70){
            CombatAircraft.GO_UP += 15;
        }
        else{
            Map4.switch_to_map5 = true;
            CombatAircraft.GO_UP = 0;
            CombatAircraft.GO_DOWN = 0;
        }
        
        for(int i=0; i<8; i++){
            AmmoBlu1.shooting[i] = 0;
            AmmoBlu1.left[i] = 0;
            AmmoBlu1.right[i] = 0;
            AmmoBlu1.flyHitCheck[i] = false;
        }
    }
}
