
package List_of_game_maps;

import List_of_fighter_aircrafts.CombatAircraft;
import List_of_space_flies.*;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Map1 {
    private Image background;  
    private CombatAircraft aircraft;
    private boolean checkWin;
    
    public Level1_Flies fly;
    
    public static boolean checkStart;

    public Map1() {
        this.checkWin = true;
        
        Map1.checkStart = false;
        
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
    
    public void firstRound(Graphics g){
        this.fly.paint(g);
        
        boolean check = false;
        for(int i=0; i<18; i++){
            if(Level1_Flies.checkDie[i] < 3){
                check = true;
            }
        }
        if(!check){
            this.checkWin = true;
        }
    }
    
    public void win(){
        if(this.checkWin){
            if(700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN > 0){
                CombatAircraft.GO_UP += 15;
            }
        }
    }
}
