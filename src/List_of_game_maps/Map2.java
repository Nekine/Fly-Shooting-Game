package List_of_game_maps;

import List_of_ammos.AmmoBlu1;
import List_of_fighter_aircrafts.*;
import List_of_space_flies.Level2_Flies;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Map2 {
    private Image background;
    private CombatAircraft aircraft;
    
    public Level2_Flies fly;
    
    public static boolean checkStart;
    public static boolean checkWin;
    public static boolean switch_to_map3;
    
    public Map2(){
        Map2.checkStart = false;
        Map2.checkWin = false;
        Map2.switch_to_map3 = false;
        
        this.aircraft = new CombatAircraft();
        this.fly = new Level2_Flies();
        
        try{
            this.background = ImageIO.read(new File("S675/World3_BG1.png"));
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
        
        if(Map2.checkStart){
            this.secondRound(g);
        }
    }
    
    public void startGame(){
        CombatAircraft.GO_LEFT = 0;
        CombatAircraft.GO_RIGHT = 0;
        
        if (Map1.switch_to_map2) {
            if (CombatAircraft.GO_UP < 70) {
                CombatAircraft.GO_UP += 5;
            }

            if (CombatAircraft.GO_UP == 70) {
                Map2.checkStart = true;   
                //Map2.checkWin = true; // NOTE
            }
        }
    }
    
    private void secondRound(Graphics g){
        if(Level2_Flies.GO_DOWN < 50){
                this.fly.paintMove(g);
            }
            else{
                this.fly.paint(g);
                this.fly.paintAmmoFly(g);
        }
        
        boolean check = false;
        for(int i=0; i<24; i++){
            if(Level2_Flies.checkDie[i] < 6){
                check = true;
            }
        }
        if(!check){
            Map2.checkWin = true;
        }
    }
    
    public void win(){
        if(700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN > -70){
            CombatAircraft.GO_UP += 15;
        }
        else{
            Map2.switch_to_map3 = true;
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
