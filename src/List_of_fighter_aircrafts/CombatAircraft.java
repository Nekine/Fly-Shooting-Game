package List_of_fighter_aircrafts;

import List_of_game_maps.Map1;
import galacticskywars.GameScreen;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class CombatAircraft {
    private Image[][] aircrafts;
    private Image[][] planeLeft;
    private Image[][] planeRight;
    
    public static int GO_UP = 0;
    public static int GO_DOWN = 0;
    public static int GO_LEFT = 0;
    public static int GO_RIGHT = 0;
    
    public static int checkMove;
    public static int index;
    public static int item;
    public static int itemLeft;
    public static int itemRight;
    
    public CombatAircraft() {
        this.aircrafts = new Image[5][5];
        this.planeLeft = new Image[5][5];
        this.planeRight = new Image[5][5];
        
        CombatAircraft.checkMove = 0;
        CombatAircraft.index = 0;
        CombatAircraft.item = 0;
        CombatAircraft.itemLeft = 0;
        CombatAircraft.itemRight = 0;
        
        for(int j=0; j<5; j++){
            for(int i=0; i<5; i++){
                try{
                    this.aircrafts[j][i] = ImageIO.read(new File("S675/aircraft/aircraft" + j + "_Idle_" + i + ".png"));
                    this.planeLeft[j][i] = ImageIO.read(new File("S675/aircraft/aircraft" + j + "_Twirling_left_" + i + ".png"));
                    this.planeRight[j][i] = ImageIO.read(new File("S675/aircraft/aircraft" + j + "_Twirling_right_" + i + ".png"));
                }catch(Exception e){}
            } 
        }
    }
    
    public void paint(Graphics g){
        if(!GameScreen.checkPlay){
            g.drawImage(this.aircrafts[CombatAircraft.index][CombatAircraft.item], 430, 460, 120, 90, null);
        }
        else{
            g.drawImage(this.aircrafts[CombatAircraft.index][CombatAircraft.item], 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT, 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN, 90, 67, null);
        }
    }
    
    public void painMove(Graphics g){
        if(CombatAircraft.checkMove == 1){
            g.drawImage(this.planeLeft[CombatAircraft.index][CombatAircraft.itemLeft], 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT, 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN, 90, 67, null);
        }
        else if(CombatAircraft.checkMove == 2){
            g.drawImage(this.planeRight[CombatAircraft.index][CombatAircraft.itemRight], 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT, 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN, 90, 67, null);
        }
        else if(CombatAircraft.checkMove == 3){
            g.drawImage(this.aircrafts[CombatAircraft.index][CombatAircraft.item], 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT, 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN, 90, 67, null);
        }
        else if(CombatAircraft.checkMove == 4){
            g.drawImage(this.aircrafts[CombatAircraft.index][CombatAircraft.item], 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT, 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN, 90, 67, null);
        }
    }
}


