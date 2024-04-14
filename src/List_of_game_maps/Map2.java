package List_of_game_maps;

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
    
    public Map2(){
        Map2.checkStart = false;
        Map2.checkWin = false;
        
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
            if(Level2_Flies.GO_DOWN < 50){
                this.fly.paintMove(g);
            }
            else{
                this.fly.paint(g);
            }
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
            }
        }
    }
}
