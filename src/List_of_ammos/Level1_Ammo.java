package List_of_ammos;

import List_of_fighter_aircrafts.CombatAircraft;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import List_of_sounds.*;
import List_of_space_flies.Level1_Flies;

public class Level1_Ammo {
    private Image ammo[][][];
    private aircraft_sounds gunshot;
    
    public static int left[], right[];
    public static int index;
    public static int item;
    public static int shooting[];
    public static boolean flyHitCheck[];

    public Level1_Ammo() {
        this.ammo = new Image[4][3][8];
        this.gunshot = new aircraft_sounds();
        Level1_Ammo.shooting = new int[8];
        Level1_Ammo.flyHitCheck = new boolean[8];
        
        Level1_Ammo.left = new int[8];
        Level1_Ammo.right = new int[8];
        
        Level1_Ammo.index = 0;
        Level1_Ammo.item = 0;
        for(int i=0; i<8; i++){
            Level1_Ammo.shooting[i] = 0;
            Level1_Ammo.flyHitCheck[i] = false;
            Level1_Ammo.left[i] = 0;
            Level1_Ammo.right[i] = 0;
        }
        
        for(int i=2; i<6; i++){
            for(int j=1; j<4; j++){
                for(int z=0; z<8; z++){
                    try{
                        this.ammo[i-2][j-1][z] = ImageIO.read(new File("S675/aircraft/player2_bullet" + i + "_" + j + ".png"));
                    }catch(Exception e){}
                }
            }
        }
    }
    
    public void paint(Graphics g){
        for(int i=0; i<8; i++){ 
            if(Level1_Ammo.shooting[i] == 0){
                this.gunshot.gunshotSound();
            }
            
            if(i == 0){
                if(!Level1_Ammo.flyHitCheck[i]){
                    g.drawImage(this.ammo[Level1_Ammo.index][Level1_Ammo.item][i], 465-Level1_Ammo.left[i]+Level1_Ammo.right[i], 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-Level1_Ammo.shooting[i], 20, 60, null);
                }
            }
            else{
                if(Level1_Ammo.shooting[i-1] > 70 || Level1_Ammo.shooting[i] > 200){
                    if(!Level1_Ammo.flyHitCheck[i]){
                        g.drawImage(this.ammo[Level1_Ammo.index][Level1_Ammo.item][i], 465-Level1_Ammo.left[i]+Level1_Ammo.right[i], 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-Level1_Ammo.shooting[i], 20, 60, null);
                    }
                }
            }
        }
    }
    
    public void bulletHitsFly1(){        
        for(int i=0; i<8; i++){
            int xAmmo = 465-Level1_Ammo.left[i]+Level1_Ammo.right[i];
            int yAmmo = 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-Level1_Ammo.shooting[i];
            
            for(int j=17; j>=0; j--){
                int xFly;
                int yFly;
                if(j < 9){
                    xFly = 50+j*100;
                    yFly = Level1_Flies.GO_DOWN;
                } else {
                    xFly = 50+(j-9)*100;
                    yFly = Level1_Flies.GO_DOWN+90;
                }
                
                if(xAmmo>=xFly && xAmmo<=xFly+60 && yAmmo<=yFly+60 && Level1_Flies.checkDie[j]<3 && !Level1_Ammo.flyHitCheck[i]){
                    Level1_Flies.checkDie[j]++;
                    Level1_Ammo.flyHitCheck[i] = true;
                    break;
                }
            }
        }
    }
}
