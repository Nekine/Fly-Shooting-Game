
package galacticskywars;

import List_of_ammos.Level1_Ammo;
import List_of_fighter_aircrafts.CombatAircraft;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import List_of_game_maps.*;


public class GameScreen extends JPanel implements Runnable{
    Thread thread;
    private Image background;
    private Map1 map1;
    
    public StartScreen start;
    public static boolean checkPlay;
    

    public GameScreen() {
        thread = new Thread(this);
        this.start = new StartScreen();
        this.map1 = new Map1();
        GameScreen.checkPlay = false;
        this.setBounds(0, 0, 1000, 800); // khai báo kích thước của Jpanel
        thread.start();
    }
    
    @Override
    public void paint(Graphics g){
        this.paintBG(g);
        this.start.paint(g);
        this.start.playAudio_Main();
        if(GameScreen.checkPlay){
            this.map1.paint(g);
        }
    }
    
    public void paintBG(Graphics g){
        try{
            this.background = ImageIO.read(new File("images_game/vutru.jpg"));
            g.drawImage(this.background, 0, 0, 1000, 800, null);
        }catch(Exception e){}   
    }

    @Override
    public void run() {
        while(true){
            repaint();   
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // chuyển item ảnh
            if(CombatAircraft.item == 4){
                CombatAircraft.item = 0;
            }
            else{
                CombatAircraft.item++;
            }
            
            // di chuyển may bay khi start game
            if(GameScreen.checkPlay){
                if(CombatAircraft.GO_UP < 70){
                    CombatAircraft.GO_UP += 5;
                }
                
                if(CombatAircraft.GO_UP == 70){
                    Map1.checkStart = true;
                }
            }
            
            // di chuyển máy bay
            if(GameScreen.checkPlay && Map1.checkStart){
                if(CombatAircraft.checkMove == 1){
                    CombatAircraft.GO_LEFT += 10;
                    if(CombatAircraft.itemLeft < 4){
                        CombatAircraft.itemLeft++;
                    }
                    CombatAircraft.itemRight = 0;
                 }
                 else if(CombatAircraft.checkMove == 2){
                    CombatAircraft.GO_RIGHT += 10;
                    if(CombatAircraft.itemRight < 4){
                        CombatAircraft.itemRight++;
                    }
                    CombatAircraft.itemLeft = 0;
                 }
                 else if(CombatAircraft.checkMove == 3){
                    CombatAircraft.GO_UP += 10;
                    CombatAircraft.itemLeft = 0;
                    CombatAircraft.itemRight = 0;
                 }
                 else if(CombatAircraft.checkMove == 4){
                    CombatAircraft.GO_DOWN += 10;
                    CombatAircraft.itemLeft = 0;
                    CombatAircraft.itemRight = 0;
                 }
            }
            
            // bắn đạn 
            if(GameScreen.checkPlay && Map1.checkStart){
                for(int i=0; i<8; i++){
                    if(i == 0){
                        if(Level1_Ammo.shooting[i] > 650){
                            Level1_Ammo.shooting[i] = 0;
                        }
                        else{
                            Level1_Ammo.shooting[i] += 10;
                        }
                    }
                    else{
                        if(Level1_Ammo.shooting[i-1] > 80){
                            if(Level1_Ammo.shooting[i] > 650){
                                Level1_Ammo.shooting[i] = 0;
                            }
                            else{
                                Level1_Ammo.shooting[i] += 10;
                            }
                        }
                    }
                }
            }
        }
    }
    
    
}
