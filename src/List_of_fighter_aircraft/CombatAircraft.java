package List_of_fighter_aircraft;

import List_of_game_maps.Map1;
import galacticskywars.GameScreen;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class CombatAircraft {
    private Image[][] aircrafts;

    public CombatAircraft() {
        this.aircrafts = new Image[5][5];
        
        for(int j=0; j<5; j++){
            for(int i=0; i<5; i++){
                try{
                    this.aircrafts[j][i] = ImageIO.read(new File("S675/aircraft/aircraft" + j + "_Idle_" + i + ".png"));
                }catch(Exception e){}
            } 
        }
    }
    
    public void paint(Graphics g, int index_1, int index_2){
        if(!GameScreen.checkPlay){
            g.drawImage(this.aircrafts[index_1][index_2], 430, 460, 120, 90, null);
            System.out.println("ok-1");
        }
        else{
            g.drawImage(this.aircrafts[index_1][index_2], 430, 700 - Map1.up, 90, 67, null);
            System.out.println("ok-2");
        }
    }
}


