package List_of_space_flies;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Level2_Flies {
    private Image flyIdle[][];
    private Image flyDown[][];
    private Image ammoFly[];
    
    public static int itemIdle;
    public static int itemDown;
    public static int GO_DOWN;
    
    public static int checkDie[];
    
    public Level2_Flies(){
        this.flyDown = new Image[4][5];
        this.flyIdle = new Image[4][6];
        this.ammoFly = new Image[24];
        
        Level2_Flies.checkDie = new int[24];
        
        Level2_Flies.GO_DOWN = 0;
        Level2_Flies.itemIdle = 0;
        Level2_Flies.itemDown = 0;
        
        for(int i=0; i<24; i++){
            Level2_Flies.checkDie[i] = 0;
            try{
                this.ammoFly[i] = ImageIO.read(new File("S675/EnemyBullet.png"));
            }catch(Exception e){}
        }
       
        for(int i=0; i<4; i++){
            for(int j=0; j<6; j++){
                try{
                    if(i<5){
                        this.flyDown[i][j] = ImageIO.read(new File("S675/aircraft/botamnhiet_" + (i+1) + "_atk_down_" + j + ".png"));
                    }
                    this.flyIdle[i][j] = ImageIO.read(new File("S675/aircraft/botamnhiet_" + (i+1) + "_idle_" + j + ".png"));
                }catch(Exception e){}
            }
        }
    }
    
    public void paint(Graphics g){
        for(int i=0; i<8; i++){
            if(i<4){
                if(Level2_Flies.checkDie[i] < 6){
                    g.drawImage(this.flyIdle[i][Level2_Flies.itemIdle], 60+i*110, Level2_Flies.GO_DOWN, 90, 70, null);
                }
                if(Level2_Flies.checkDie[i+8] < 6){
                    g.drawImage(this.flyIdle[3-i][Level2_Flies.itemIdle], 60+i*110, Level2_Flies.GO_DOWN+90, 90, 70, null);
                }
                if(Level2_Flies.checkDie[i+16] < 6){
                    g.drawImage(this.flyIdle[i][Level2_Flies.itemIdle], 60+i*110, Level2_Flies.GO_DOWN+180, 90, 70, null);
                }
            }
            else {
                if(Level2_Flies.checkDie[i] < 6){
                    g.drawImage(this.flyIdle[i%4][Level2_Flies.itemIdle], 60+i*110, Level2_Flies.GO_DOWN, 90, 70, null);
                }
                if(Level2_Flies.checkDie[i+8] < 6){
                    g.drawImage(this.flyIdle[3-(i%4)][Level2_Flies.itemIdle], 60+i*110, Level2_Flies.GO_DOWN+90, 90, 70, null);
                }
                if(Level2_Flies.checkDie[i+16] < 6){
                    g.drawImage(this.flyIdle[i%4][Level2_Flies.itemIdle], 60+i*110, Level2_Flies.GO_DOWN+180, 90, 70, null);
                }
            }
        }
    }
    
    public void paintMove(Graphics g){
        for(int i=0; i<8; i++){
            if(i<4){
                if(Level2_Flies.checkDie[i] < 6){
                    g.drawImage(this.flyDown[i][Level2_Flies.itemDown], 60+i*110, Level2_Flies.GO_DOWN, 90, 70, null);
                }
                if(Level2_Flies.checkDie[i+8] < 6){
                    g.drawImage(this.flyDown[3-i][Level2_Flies.itemDown], 60+i*110, Level2_Flies.GO_DOWN+90, 90, 70, null);
                }
                if(Level2_Flies.checkDie[i+16] < 6){
                    g.drawImage(this.flyDown[i][Level2_Flies.itemDown], 60+i*110, Level2_Flies.GO_DOWN+180, 90, 70, null);
                }
            }
            else {
                if(Level2_Flies.checkDie[i] < 6){
                    g.drawImage(this.flyDown[i%4][Level2_Flies.itemDown], 60+i*110, Level2_Flies.GO_DOWN, 90, 70, null);
                }
                if(Level2_Flies.checkDie[i+8] < 6){
                    g.drawImage(this.flyDown[3-(i%4)][Level2_Flies.itemDown], 60+i*110, Level2_Flies.GO_DOWN+90, 90, 70, null);
                }
                if(Level2_Flies.checkDie[i+16] < 6){
                    g.drawImage(this.flyDown[i%4][Level2_Flies.itemDown], 60+i*110, Level2_Flies.GO_DOWN+180, 90, 70, null);
                }
            }
        }
    }
    
    public void moveFlies(){
        if(Level2_Flies.GO_DOWN < 50){
            Level2_Flies.GO_DOWN++;
        }
    }
}
