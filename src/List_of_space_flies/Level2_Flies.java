package List_of_space_flies;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Level2_Flies {
    private Image flies[][];
    private Image flyDown[][];
    private Image flyUp[][];
    private Image ammoFly;
    
    public static int item;
    public static int itemMove;
    public static int GO_UP;
    public static int GO_DOWN;
    
    public static int checkDie[];
    
    public Level2_Flies(){
        this.flyUp = new Image[3][4];
        this.flyDown = new Image[3][4];
        
        Level1_Flies.checkDie = new int[18];
        
        for(int i=0; i<18; i++){
            Level1_Flies.checkDie[i] = 0;
        }
        
        Level1_Flies.itemMove = 0;
        Level1_Flies.GO_UP = 0;
        Level1_Flies.GO_DOWN = 0;
       
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                try{
                    this.flyUp[i][j] = ImageIO.read(new File("S675/aircraft/bosungmay" + (i+1) + "_atk_up_" + j + ".png"));
                    this.flyDown[i][j] = ImageIO.read(new File("S675/aircraft/bosungmay" + (i+1) + "_atk_down_" + j + ".png"));
                }catch(Exception e){}
            }
        }
        
        try{
            this.ammoFly = ImageIO.read(new File("S675/EnemyBullet.png"));
        }catch(Exception e){}
    }
    
    public void paint(Graphics g){
        for(int i=0; i<9; i++){
            if(i < 3){
                if(Level1_Flies.checkDie[i] < 3){
                    g.drawImage(this.flyDown[i][Level1_Flies.itemMove], 50+i*100, Level1_Flies.GO_DOWN, 80, 60, null);
                }
                if(Level1_Flies.checkDie[i+9] < 3){
                    g.drawImage(this.flyDown[2-i][Level1_Flies.itemMove], 50+i*100, Level1_Flies.GO_DOWN+90, 80, 60, null);
                }
            }
            else if(i < 6){
                if(Level1_Flies.checkDie[i] < 3){
                    g.drawImage(this.flyDown[i%3][Level1_Flies.itemMove], 50+i*100, Level1_Flies.GO_DOWN, 80, 60, null);
                }
                if(Level1_Flies.checkDie[i+9] < 3){
                    g.drawImage(this.flyDown[2-(i%3)][Level1_Flies.itemMove], 50+i*100, Level1_Flies.GO_DOWN+90, 80, 60, null);
                }
            }
            else{
                if(Level1_Flies.checkDie[i] < 3){
                    g.drawImage(this.flyDown[i%6][Level1_Flies.itemMove], 50+i*100, Level1_Flies.GO_DOWN, 80, 60, null);
                }
                if(Level1_Flies.checkDie[i+9] < 3){
                    g.drawImage(this.flyDown[2-(i%6)][Level1_Flies.itemMove], 50+i*100, Level1_Flies.GO_DOWN+90, 80, 60, null);
                }
            }
        }
    }
}
