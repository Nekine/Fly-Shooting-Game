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
import List_of_space_flies.*;

public class GameScreen extends JPanel implements Runnable {

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
    public void paint(Graphics g) {
        this.paintBG(g);
        this.start.paint(g);
        this.start.playAudio_Main();
        if (GameScreen.checkPlay) {
            this.map1.paint(g);
        }
    }

    public void paintBG(Graphics g) {
        try {
            this.background = ImageIO.read(new File("images_game/vutru.jpg"));
            g.drawImage(this.background, 0, 0, 1000, 800, null);
        } catch (Exception e) {
        }
    }
    
    private void startGame(){
        if (GameScreen.checkPlay) {
            if (CombatAircraft.GO_UP < 70) {
                CombatAircraft.GO_UP += 5;
            }

            if (CombatAircraft.GO_UP == 70) {
                Map1.checkStart = true;
            }
        }
    }
    
    private void moveFlies(){
        if(Map1.checkStart){
            if(Level1_Flies.GO_DOWN < 50){
                Level1_Flies.GO_DOWN += 1;
            }
        }
    }
    
    private void moveToThePlane(){
        if (GameScreen.checkPlay && Map1.checkStart) {
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
                    Level1_Ammo.shooting[i] += 10;
                }
            }
        }
    }
    
    private void shootingAmmo(){
        if (GameScreen.checkPlay && Map1.checkStart) {
            for (int i = 0; i < 8; i++) {
                if (i == 0) {
                    Level1_Ammo.shooting[i] += 10;

                    if (Level1_Ammo.shooting[i]+CombatAircraft.GO_UP-CombatAircraft.GO_DOWN > 750) { // nếu đạn bay ra ngoài phạm vi màn hình thì sẽ sét giá trị về 0 để quay lại
                        Level1_Ammo.shooting[i] = 0;
                        Level1_Ammo.flyHitCheck[i] = false;
                        Level1_Ammo.left[i] = CombatAircraft.GO_LEFT;
                        Level1_Ammo.right[i] = CombatAircraft.GO_RIGHT;
                    }
                } else {
                    if (Level1_Ammo.shooting[i - 1] > 80 || Level1_Ammo.shooting[i] > 200) { // nếu bạn trước bay được 1 đoạn thì mới cho đạn sau bắn ra hoặc nếu khi đạn bay ra được 1 đoạn nhất định rồi
                        Level1_Ammo.shooting[i] += 10;

                        if (Level1_Ammo.shooting[i]+CombatAircraft.GO_UP-CombatAircraft.GO_DOWN > 750) {
                            Level1_Ammo.shooting[i] = 0;
                            Level1_Ammo.flyHitCheck[i] = false;
                            Level1_Ammo.left[i] = CombatAircraft.GO_LEFT;
                            Level1_Ammo.right[i] = CombatAircraft.GO_RIGHT;
                        }
                    }
                }
            }
        }
    }
    
    private void bulletHitsFly(){        
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
    
    

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

            // chuyển item ảnh aircraft
            long currentTimeMillis = Math.round((System.currentTimeMillis() % 1000)/ 100);
            if(currentTimeMillis % 2 == 0){
                if (CombatAircraft.item == 4) {
                    CombatAircraft.item = 0;
                } else {
                    CombatAircraft.item++;
                }
            }
            
            // chuyển item ảnh fly
            if(currentTimeMillis % 2 == 0){
                if(Level1_Flies.item == 1){
                    Level1_Flies.item = 0;
                } else {
                    Level1_Flies.item++;
                }
            }
            
            if(Level1_Flies.GO_DOWN < 50 && currentTimeMillis % 2 == 0){
                if(Level1_Flies.itemMove == 3){
                    Level1_Flies.itemMove = 0;
                }
                else{
                    Level1_Flies.itemMove++;
                }
            }

            // di chuyển may bay khi start game
            this.startGame();
            
            // di chuyển ruồi khi start game
            this.moveFlies();
            
            // di chuyển máy bay
            this.moveToThePlane();

            // bắn đạn 
            this.shootingAmmo();
            
            // kiểm tra đạn bắn trúng ruồi
            this.bulletHitsFly();
        }
    }

}
