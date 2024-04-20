package List_of_space_flies;

import List_of_fighter_aircrafts.CombatAircraft;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Level4_FinalBoss {
    private Image boss[];
    private Image ammoBoss[];
    private int yAmmo[];
    private int xAmmo[];
    private int xAircraft[];
    private int yAircraft[];
    
    public static int DO_DOWN;
    public static int checkDie;
    public static int item;
    
    
    public Level4_FinalBoss(){
        Level4_FinalBoss.DO_DOWN = -500;
        Level4_FinalBoss.checkDie = 0;
        Level4_FinalBoss.item = 0;
        
        this.boss = new Image[8];
        this.ammoBoss = new Image[6];
        this.yAmmo = new int[6];
        this.xAmmo = new int[6];
        this.xAircraft = new int[6];
        this.yAircraft = new int[6];
        
        for(int i=0; i<8; i++){
            try{
                this.boss[i] = ImageIO.read(new File("S675/aircraft/Boss3_Idle_" + i + ".png"));
            }catch(Exception e){}
        }
        
        for(int i=0; i<6; i++){
            this.xAircraft[i] = 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT;
            this.yAircraft[i] = 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN;
            
            this.yAmmo[i] = 340;
            this.setXAmmo(i, this.xAircraft[i], this.yAircraft[i]);
            
            try{
                this.ammoBoss[i] = ImageIO.read(new File("S675/aircraft/Boss3_shield_core_" + (i%2) + ".png"));
            }catch(Exception e){}
        }
    }
    
    public void paint(Graphics g){
        g.drawImage(this.boss[Level4_FinalBoss.item], 170, Level4_FinalBoss.DO_DOWN, null);
    }
    
    public void paintAmmoFly(Graphics g){
        for(int i=0; i<6; i++){                   
            if(i == 0){
                g.drawImage(this.ammoBoss[i], this.xAmmo[i], this.yAmmo[i], 60, 60, null);
            }
            else{
                if(this.yAmmo[i-1] > 410 || this.yAmmo[i] > 400){
                    g.drawImage(this.ammoBoss[i], this.xAmmo[i], this.yAmmo[i], 60, 60, null);
                }
            }
        }
    }
    
    public void moveFly(){
        if(Level4_FinalBoss.DO_DOWN < 30){
            Level4_FinalBoss.DO_DOWN += 10;
        }
    }
    
    public void shootingAmmo(){
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                this.yAmmo[i] += 5;

                if (this.yAmmo[i] > 750) { // nếu đạn bay ra ngoài phạm vi màn hình thì sẽ sét giá trị về 0 để quay lại
                    this.yAmmo[i] = 340;
                    
                    this.xAircraft[i] = 430-CombatAircraft.GO_LEFT+CombatAircraft.GO_RIGHT;
                    this.yAircraft[i] = 700-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN;
                }
                
                this.setXAmmo(i, this.xAircraft[i], this.yAircraft[i]);
            } else {
                if (this.yAmmo[i - 1] > 410 || this.yAmmo[i] > 400) { // nếu bạn trước bay được 1 đoạn thì mới cho đạn sau bắn ra hoặc nếu khi đạn bay ra được 1 đoạn nhất định rồi
                    this.yAmmo[i] += 5;

                    if (this.yAmmo[i] > 750) {
                        this.yAmmo[i] = 340;
                        
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
            this.xAmmo[index] = 453 + Math.abs((int) ((int) (this.yAmmo[index]-340)/tan));
        }
        else{
            this.xAmmo[index] = 453 - Math.abs((int) ((int) (this.yAmmo[index]-340)/tan));
        }
    }
}
