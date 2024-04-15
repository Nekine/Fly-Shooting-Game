package List_of_fighter_aircrafts;

import List_of_ammos.*;
import List_of_game_maps.*;
import List_of_sounds.aircraft_sounds;
import List_of_space_flies.*;
import galacticskywars.GameScreen;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class CombatAircraft {
    public AmmoBlu1 ammo; 
    private aircraft_sounds aircraftDie;
    
    private Image[][] aircrafts;
    private Image[][] planeLeft;
    private Image[][] planeRight;
    private Image[] die;
    
    public static int GO_UP = 0;
    public static int GO_DOWN = 0;
    public static int GO_LEFT = 0;
    public static int GO_RIGHT = 0;
    
    public static int checkMove;
    public static int checkDie;
    public static int index;
    public static int item;
    public static int itemLeft;
    public static int itemRight;
    public static int itemDie;
    
    public CombatAircraft() {
        this.ammo = new AmmoBlu1();
        this.aircraftDie = new aircraft_sounds();
        
        this.aircrafts = new Image[5][5];
        this.planeLeft = new Image[5][5];
        this.planeRight = new Image[5][5];
        this.die = new Image[8];
        
        CombatAircraft.checkMove = 0;
        CombatAircraft.index = 0;
        CombatAircraft.item = 0;
        CombatAircraft.itemLeft = 0;
        CombatAircraft.itemRight = 0;
        CombatAircraft.itemDie = 0;
        CombatAircraft.checkDie = 0;
        
        for(int j=0; j<5; j++){
            for(int i=0; i<5; i++){
                try{
                    this.aircrafts[j][i] = ImageIO.read(new File("S675/aircraft/aircraft" + j + "_Idle_" + i + ".png"));
                    this.planeLeft[j][i] = ImageIO.read(new File("S675/aircraft/aircraft" + j + "_Twirling_left_" + i + ".png"));
                    this.planeRight[j][i] = ImageIO.read(new File("S675/aircraft/aircraft" + j + "_Twirling_right_" + i + ".png"));
                }catch(Exception e){}
            } 
        }
        
        for(int i=0; i<8; i++){
            try{
                this.die[i] = ImageIO.read(new File("S675/aircraft/P104090/splash_ldodge.img (" + (i+1) + ").png"));
            }catch(Exception e){}
        }
    }
    
    public void paint(Graphics g){
        if(!Map1.checkWin && Map1.checkStart){
           this.ammo.paint(g);
        }
        else if(!Map2.checkWin && Map2.checkStart){
           this.ammo.paint2(g);
        }
        else if(!Map3.checkWin && Map3.checkStart){
            this.ammo.paint3(g);
        }
        else if(!Map4.checkWin && Map4.checkStart){
            this.ammo.paint3(g);
        }
        else{}
        
        if(!GameScreen.checkPlay){
            g.drawImage(this.aircrafts[CombatAircraft.index][CombatAircraft.item], 430, 460, 120, 90, null);
        }
        else{
            if(CombatAircraft.checkDie >= 3){
                this.aircraftDie.aircraftDieSound();
                g.drawImage(this.die[CombatAircraft.itemDie], 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT, 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN, 100, 100, null);
            } else {
                g.drawImage(this.aircrafts[CombatAircraft.index][CombatAircraft.item], 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT, 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN, 90, 67, null);
            }
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
        // Ammo
        if(!Map1.checkWin && Map1.checkStart){
           this.ammo.paint(g);
        }
        else if(!Map2.checkWin && Map2.checkStart){
           this.ammo.paint2(g);
        }
        else if(!Map3.checkWin && Map3.checkStart){
            this.ammo.paint3(g);
        }
        else if(!Map4.checkWin && Map4.checkStart){
            this.ammo.paint3(g);
        }
        else{}
    }
    
    public void moveToThePlane(){
        if (CombatAircraft.checkMove == 1) {
            CombatAircraft.GO_LEFT += 10;
            if (CombatAircraft.itemLeft < 4) {
                CombatAircraft.itemLeft++;
            }
            CombatAircraft.itemRight = 0;
        } else if (CombatAircraft.checkMove == 2) {
            CombatAircraft.GO_RIGHT += 10;
            if (CombatAircraft.itemRight < 4) {
                CombatAircraft.itemRight++;
            }
            CombatAircraft.itemLeft = 0;
        } else if (CombatAircraft.checkMove == 3) {
            CombatAircraft.GO_UP += 10;
            CombatAircraft.itemLeft = 0;
            CombatAircraft.itemRight = 0;
        } else if (CombatAircraft.checkMove == 4) {
            CombatAircraft.GO_DOWN += 10;
            CombatAircraft.itemLeft = 0;
            CombatAircraft.itemRight = 0;
            
            for(int i=0; i<8; i++){
                AmmoBlu1.shooting[i] += 10;
            }
        }
    }
    
    public void shootingAmmo(){
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                AmmoBlu1.shooting[i] += 10;

                if (AmmoBlu1.shooting[i]+CombatAircraft.GO_UP-CombatAircraft.GO_DOWN > 750) { // nếu đạn bay ra ngoài phạm vi màn hình thì sẽ sét giá trị về 0 để quay lại
                    AmmoBlu1.shooting[i] = 0;
                    AmmoBlu1.flyHitCheck[i] = false;
                    AmmoBlu1.left[i] = CombatAircraft.GO_LEFT;
                    AmmoBlu1.right[i] = CombatAircraft.GO_RIGHT;
                }
            } else {
                if (AmmoBlu1.shooting[i - 1] > 80 || AmmoBlu1.shooting[i] > 200) { // nếu bạn trước bay được 1 đoạn thì mới cho đạn sau bắn ra hoặc nếu khi đạn bay ra được 1 đoạn nhất định rồi
                    AmmoBlu1.shooting[i] += 10;

                    if (AmmoBlu1.shooting[i]+CombatAircraft.GO_UP-CombatAircraft.GO_DOWN > 750) {
                        AmmoBlu1.shooting[i] = 0;
                        AmmoBlu1.flyHitCheck[i] = false;
                        AmmoBlu1.left[i] = CombatAircraft.GO_LEFT;
                        AmmoBlu1.right[i] = CombatAircraft.GO_RIGHT;
                    }
                }
            }
        }
    }
    
    public void checkDieAircraft1(){
        int xAircraft = 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT;
        int yAircraft = 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN;
        
        for(int i=17; i>=0; i--){
            if(Level1_Flies.checkDie[i] < 3){
                int xFly;
                int yFly;
                if(i < 9){
                    xFly = 50+i*100;
                    yFly = Level1_Flies.GO_DOWN;
                } else {
                    xFly = 50+(i-9)*100;
                    yFly = Level1_Flies.GO_DOWN+90;
                }
                
                if(((xFly+70>xAircraft && xFly+70<xAircraft+80) || (xFly<=xAircraft+80 && xFly>=xAircraft)) && (yFly+60>=yAircraft)){
                    CombatAircraft.checkDie += 2;
                }   
            }
        }
    }
    
    public void checkDieAircraft2(){
        int xAircraft = 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT;
        int yAircraft = 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN;
        
        for(int i=0; i<8; i++){
            int xAmmo = 60+i*120;
            int yAmmo = Level2_Flies.GO_DOWN+Level2_Flies.shooting[Level2_Flies.fliesShootingBullets[i]];
            
            if((xAmmo+10<=xAircraft+70 && xAmmo>=xAircraft) && (yAmmo>=yAircraft && yAmmo+10<=yAircraft+60)){
                CombatAircraft.checkDie++;
                Level2_Flies.aircraftHitCheck[i] = true;
            }
        }
    }
    
    public void checkDieAircraft3(){
        int xAircraft = 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT;
        int yAircraft = 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN;
        
        for(int j=0; j<5; j++){
                for(int z=0; z<6; z++){
                    int xMeteorite = 60+z*150;
                    int yMeteorite = Level3_MeteoriteChunks.GO_DOWN[j];
                    
                    if(xAircraft>=xMeteorite && xAircraft<=xMeteorite+Level3_MeteoriteChunks.size[z+j*6]*50 && yAircraft<=yMeteorite+Level3_MeteoriteChunks.size[z+j*6]*50-20){
                            CombatAircraft.checkDie = 3;
                            return;
                        }
                }
            }
    }
}


