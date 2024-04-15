package List_of_game_maps;

import List_of_ammos.AmmoBlu1;
import List_of_fighter_aircrafts.*;
import List_of_space_flies.*;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Map3 {
    private Image background;
    private CombatAircraft aircraft;
    
    public Level3_MeteoriteChunks meteorite;
    
    public static boolean checkStart;
    public static boolean checkWin;
    public static boolean switch_to_map4;
    public static int count;
    
    public Map3(){
        Map3.checkStart = false;
        Map3.checkWin = false;
        Map3.switch_to_map4 = false;
        Map3.count = 0;
        
        this.aircraft = new CombatAircraft();
        this.meteorite = new Level3_MeteoriteChunks();
        try{
            this.background = ImageIO.read(new File("S675/planet1_BG2.png"));
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
        
        if(Map3.checkStart){
            this.thirdRound(g);
        }
    }
    
    public void startGame(){
        CombatAircraft.GO_LEFT = 0;
        CombatAircraft.GO_RIGHT = 0;
        
        if (Map2.switch_to_map3) {
            if (CombatAircraft.GO_UP < 70) {
                CombatAircraft.GO_UP += 5;
            }

            if (CombatAircraft.GO_UP == 70) {
                Map3.checkStart = true;   
                Map3.checkWin = true; // NOTE
            }
        }
    }
    
    private void thirdRound(Graphics g){
        this.meteorite.paint(g);
       
        if(Map3.count >= 20){
            Map3.checkWin =  true;
        }
    }
    
    public void win(){
        if(700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN > -70){
            CombatAircraft.GO_UP += 15;
        }
        else{
            Map3.switch_to_map4 = true;
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
