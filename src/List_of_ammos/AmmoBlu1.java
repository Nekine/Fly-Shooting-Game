package List_of_ammos;

import List_of_fighter_aircrafts.CombatAircraft;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import List_of_sounds.*;
import List_of_space_flies.*;

public class AmmoBlu1 {
    private Image ammo[][][];
    private aircraft_sounds gunshot;
    
    public static int left[], right[];
    public static int index;
    public static int item;
    public static int shooting[];
    public static boolean flyHitCheck[];

    public AmmoBlu1() {
        this.ammo = new Image[4][3][8];
        
        this.gunshot = new aircraft_sounds();
        AmmoBlu1.shooting = new int[8];
        AmmoBlu1.flyHitCheck = new boolean[8];
        
        AmmoBlu1.left = new int[8];
        AmmoBlu1.right = new int[8];
        
        AmmoBlu1.index = 0;
        AmmoBlu1.item = 0;
        for(int i=0; i<8; i++){
            AmmoBlu1.shooting[i] = 0;
            AmmoBlu1.flyHitCheck[i] = false;
            AmmoBlu1.left[i] = 0;
            AmmoBlu1.right[i] = 0;
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
            if(AmmoBlu1.shooting[i] == 0){
                this.gunshot.gunshotSound();
            }
            
            if(i == 0){
                if(!AmmoBlu1.flyHitCheck[i]){
                    g.drawImage(this.ammo[AmmoBlu1.index][AmmoBlu1.item][i], 465-AmmoBlu1.left[i]+AmmoBlu1.right[i], 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-AmmoBlu1.shooting[i], 20, 60, null);
                }
            }
            else{
                if(AmmoBlu1.shooting[i-1] > 70 || AmmoBlu1.shooting[i] > 200){
                    if(!AmmoBlu1.flyHitCheck[i]){
                        g.drawImage(this.ammo[AmmoBlu1.index][AmmoBlu1.item][i], 465-AmmoBlu1.left[i]+AmmoBlu1.right[i], 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-AmmoBlu1.shooting[i], 20, 60, null);
                    }
                }
            }
        }
    }
    
    public void paint2(Graphics g){
        for(int i=0; i<8; i++){ 
            if(AmmoBlu1.shooting[i] == 0){
                this.gunshot.gunshotSound();
            }
            
            if(i == 0){
                if(!AmmoBlu1.flyHitCheck[i]){
                    g.drawImage(this.ammo[AmmoBlu1.index][AmmoBlu1.item][i], 465-AmmoBlu1.left[i]+AmmoBlu1.right[i]-10, 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-AmmoBlu1.shooting[i], 20, 60, null);
                    g.drawImage(this.ammo[AmmoBlu1.index][AmmoBlu1.item][i], 465-AmmoBlu1.left[i]+AmmoBlu1.right[i]+10, 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-AmmoBlu1.shooting[i], 20, 60, null);
                }
            }
            else{
                if(AmmoBlu1.shooting[i-1] > 70 || AmmoBlu1.shooting[i] > 200){
                    if(!AmmoBlu1.flyHitCheck[i]){
                        g.drawImage(this.ammo[AmmoBlu1.index][AmmoBlu1.item][i], 465-AmmoBlu1.left[i]+AmmoBlu1.right[i]-10, 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-AmmoBlu1.shooting[i], 20, 60, null);
                        g.drawImage(this.ammo[AmmoBlu1.index][AmmoBlu1.item][i], 465-AmmoBlu1.left[i]+AmmoBlu1.right[i]+10, 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-AmmoBlu1.shooting[i], 20, 60, null);
                    }
                }
            }
        }
    }
    
    public void bulletHitsFly1(){        
        for(int i=0; i<8; i++){
            int xAmmo = 465-AmmoBlu1.left[i]+AmmoBlu1.right[i];
            int yAmmo = 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-AmmoBlu1.shooting[i];
            
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
                
                if(xAmmo>=xFly && xAmmo<=xFly+60 && yAmmo<=yFly+60 && Level1_Flies.checkDie[j]<3 && !AmmoBlu1.flyHitCheck[i]){
                    Level1_Flies.checkDie[j]++;
                    AmmoBlu1.flyHitCheck[i] = true;
                    break;
                }
            }
        }
    }
    
    public void bulletHitsFly2(){        
        for(int i=0; i<8; i++){
            int xAmmo = 465-AmmoBlu1.left[i]+AmmoBlu1.right[i]-10;
            int yAmmo = 650-CombatAircraft.GO_UP+CombatAircraft.GO_DOWN-AmmoBlu1.shooting[i];
            
            for(int j=23; j>=0; j--){
                int xFly;
                int yFly;
                if(j < 8){
                    xFly = 30+j*120;
                    yFly = Level2_Flies.GO_DOWN;
                }
                else if(j < 16) {
                    xFly = 30+(j-8)*120;
                    yFly = Level2_Flies.GO_DOWN+90;
                }
                else{
                    xFly = 30+(j-16)*120;
                    yFly = Level2_Flies.GO_DOWN+180;
                }
                
                if(xAmmo>=xFly && xAmmo<=xFly+80 && yAmmo<=yFly+80 && Level2_Flies.checkDie[j]<6 && !AmmoBlu1.flyHitCheck[i]){
                    Level2_Flies.checkDie[j] += 3;
                    AmmoBlu1.flyHitCheck[i] = true;
                    break;
                }
            }
        }
    }
}
