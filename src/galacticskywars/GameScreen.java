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

    @Override
    public void run() {
        while (true) {
            // chuyển item ảnh aircraft
            long currentTimeMillis = Math.round((System.currentTimeMillis() % 1000)/ 100);
            if(currentTimeMillis % 2 == 0){
                if (CombatAircraft.item == 4) {
                    CombatAircraft.item = 0;
                } else {
                    CombatAircraft.item++;
                }
            }
            
            if(CombatAircraft.checkDie && currentTimeMillis % 3 == 0){
                if(CombatAircraft.itemDie<7){
                    CombatAircraft.itemDie++;
                }
            }
            
            if(!CombatAircraft.checkDie){
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
                this.start.startGame();

                // di chuyển ruồi khi start game
                this.map1.fly.moveFlies();

                // di chuyển máy bay
                this.start.aircraft.moveToThePlane();

                // bắn đạn 
                this.start.aircraft.shootingAmmo();

                // kiểm tra đạn bắn trúng ruồi
                this.start.aircraft.ammo.bulletHitsFly();

                // kiểm tra máy bay bị chết
                this.start.aircraft.checkDieAircraft();
                
                // kiểm tra thắng màn chơi
                if(Map1.checkStart){
                    this.map1.win();
                }
            }
            
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
