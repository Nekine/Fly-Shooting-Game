
package List_of_game_maps;

import List_of_ammos.*;
import List_of_fighter_aircrafts.CombatAircraft;
import List_of_space_flies.*;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Map1 {
    private Image background;
    private CombatAircraft aircraft;
    private Level1_Ammo ammo;
    private Level1_Flies fly;
    
    public static boolean checkStart;

    public Map1() {
        Map1.checkStart = false;
        
        this.aircraft = new CombatAircraft();
        this.ammo = new Level1_Ammo();
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
            this.ammo.paint(g);
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
    }
}
