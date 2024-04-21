package List_of_space_flies;

import List_of_fighter_aircrafts.CombatAircraft;
import List_of_sounds.*;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Level4_FinalBoss {
    private Image boss[];
    private Image ammoBoss[];
    private Image bossDie[];
    private int xAircraft[];
    private int yAircraft[];
    private sounds_Map4 sounds;
    
    public static int DO_DOWN;
    public static int checkDie;
    public static int item;
    public static int itemDie;
    
    public static int yAmmo[];
    public static int xAmmo[];
    public static boolean aircraftHitCheck[];
    
    public Level4_FinalBoss(){
        Level4_FinalBoss.DO_DOWN = -500;
        Level4_FinalBoss.checkDie = 0;
        Level4_FinalBoss.item = 0;
        Level4_FinalBoss.itemDie = 0;
        
        this.boss = new Image[8];
        this.ammoBoss = new Image[4];
        this.bossDie = new Image[8];
        this.sounds = new sounds_Map4();
        
        Level4_FinalBoss.yAmmo = new int[4];
        Level4_FinalBoss.xAmmo = new int[4];
        Level4_FinalBoss.aircraftHitCheck = new boolean[4];
        this.xAircraft = new int[4];
        this.yAircraft = new int[4];
        
        for(int i=0; i<8; i++){
            try{
                this.boss[i] = ImageIO.read(new File("S675/aircraft/Boss3_Idle_" + i + ".png"));
            }catch(Exception e){}
            
            try{
                this.bossDie[i] = ImageIO.read(new File("S675/aircraft/P104090/splash_ldodge.img (" + (i+1) + ").png"));
            }catch(Exception e){}
        }
        
        for(int i=0; i<4; i++){
            this.xAircraft[i] = 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT;
            this.yAircraft[i] = 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN;
            
            Level4_FinalBoss.aircraftHitCheck[i] = false;
            Level4_FinalBoss.yAmmo[i] = 340;
            this.setXAmmo(i, this.xAircraft[i], this.yAircraft[i]);
            
            try{
                this.ammoBoss[i] = ImageIO.read(new File("S675/aircraft/Boss3_shield_core_" + (i%2) + ".png"));
            }catch(Exception e){}
        }
    }
    
    public void paint(Graphics g){
        if(Level4_FinalBoss.checkDie < 100){
            g.drawImage(this.boss[Level4_FinalBoss.item], 170, Level4_FinalBoss.DO_DOWN, null);
        }
        else{
            g.drawImage(this.bossDie[Level4_FinalBoss.itemDie], 200, 50, 600, 600, null);
        }
    }
    
    public void paintAmmoFly(Graphics g){
        for(int i=0; i<4; i++){                   
            if(i == 0){
                if(!Level4_FinalBoss.aircraftHitCheck[i]){
                    g.drawImage(this.ammoBoss[i], Level4_FinalBoss.xAmmo[i], Level4_FinalBoss.yAmmo[i], 60, 60, null);
                }
            }
            else{
                if(Level4_FinalBoss.yAmmo[i-1] > 440 || Level4_FinalBoss.yAmmo[i] > 400){
                    if(!Level4_FinalBoss.aircraftHitCheck[i]){
                        g.drawImage(this.ammoBoss[i], Level4_FinalBoss.xAmmo[i], Level4_FinalBoss.yAmmo[i], 60, 60, null);
                    }
                }
            }
        }
    }
    
    public void moveFly(){
        if(Level4_FinalBoss.DO_DOWN < 30){
            Level4_FinalBoss.DO_DOWN += 5;
        }
    }
    
    public void shootingAmmo(){
        for (int i = 0; i<4; i++) {
            if (i == 0) {
                Level4_FinalBoss.yAmmo[i] += 8;

                if (Level4_FinalBoss.yAmmo[i] > 750) { // nếu đạn bay ra ngoài phạm vi màn hình thì sẽ sét giá trị về 0 để quay lại
                    Level4_FinalBoss.yAmmo[i] = 340;
                    Level4_FinalBoss.aircraftHitCheck[i] = false;
                    
                    this.xAircraft[i] = 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT;
                    this.yAircraft[i] = 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN;
                }
                
                this.setXAmmo(i, this.xAircraft[i], this.yAircraft[i]);
            } else {
                if (Level4_FinalBoss.yAmmo[i - 1] > 440 || Level4_FinalBoss.yAmmo[i] > 400) { // nếu bạn trước bay được 1 đoạn thì mới cho đạn sau bắn ra hoặc nếu khi đạn bay ra được 1 đoạn nhất định rồi
                    Level4_FinalBoss.yAmmo[i] += 8;

                    if (Level4_FinalBoss.yAmmo[i] > 750) {
                        Level4_FinalBoss.yAmmo[i] = 340;
                        Level4_FinalBoss.aircraftHitCheck[i] = false;
                        
                        this.xAircraft[i] = 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT;
                        this.yAircraft[i] = 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN; 
                    }
                    
                    this.setXAmmo(i, this.xAircraft[i], this.yAircraft[i]);
                }
            }
        }
    }
    
    private void setXAmmo(int index, int xAircraft, int yAircraft){
        int tu, mau;
        
        if(xAircraft > 453){
            mau = xAircraft - 453;
        }
        else{
            mau = 453 - xAircraft;
        }
        
        if(yAircraft > 340){
            tu = yAircraft - 340;
        }
        else{
            tu = 340 - yAircraft;
        }
        
        double tan = (double) tu/mau;
        if(xAircraft > 453){
            Level4_FinalBoss.xAmmo[index] = 453 + Math.abs((int) ((int) (Level4_FinalBoss.yAmmo[index]-340)/tan));
        }
        else{
            Level4_FinalBoss.xAmmo[index] = 453 - Math.abs((int) ((int) (Level4_FinalBoss.yAmmo[index]-340)/tan));
        }
    }
}
