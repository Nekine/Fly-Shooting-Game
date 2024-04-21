package galacticskywars;

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
    private Map2 map2;
    private Map3 map3;
    private Map4 map4;

    public StartScreen start;
    public static boolean checkPlay;

    public GameScreen() {
        thread = new Thread(this);
        this.start = new StartScreen();
        this.map1 = new Map1();
        this.map2 = new Map2();
        this.map3 = new Map3();
        this.map4 = new Map4();
        GameScreen.checkPlay = false;
        this.setBounds(0, 0, 1000, 800); // khai báo kích thước của Jpanel
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        this.paintBG(g);
        this.start.paint(g);
        this.start.playAudio_Main();
        if (GameScreen.checkPlay && !Map1.switch_to_map2) {
            this.map1.paint(g);
        }
        else if(Map1.switch_to_map2 && !Map2.switch_to_map3){
            this.map2.paint(g);
        }
        else if(Map2.switch_to_map3 && !Map3.switch_to_map4){
            this.map3.paint(g);
        }
        else if(Map3.switch_to_map4 && !Map4.switch_to_map5){
            this.map4.paint(g);
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
            if(CombatAircraft.itemDie < 7){
                // chuyển item ảnh aircraft
                long currentTimeMillis = Math.round((System.currentTimeMillis() % 1000)/ 100);
                if(currentTimeMillis % 2 == 0){
                    if (CombatAircraft.item == 4) {
                        CombatAircraft.item = 0;
                    } else {
                        CombatAircraft.item++;
                    }
                }

                if(CombatAircraft.checkDie >= 3 && currentTimeMillis % 3 == 0){
                    if(CombatAircraft.itemDie<7){
                        CombatAircraft.itemDie++;
                    }
                }
                // chuyển item ảnh fly
                if(currentTimeMillis % 2 == 0){
                    if(!Map1.checkWin){
                        if(Level1_Flies.itemMove == 3){
                            Level1_Flies.itemMove = 0;
                        }
                        else{
                            Level1_Flies.itemMove++;
                        }
                    }
                    else if(!Map2.checkWin){
                        if(Level2_Flies.GO_DOWN < 50){
                            if(Level2_Flies.itemDown == 4){
                                Level2_Flies.itemDown = 0;
                            }
                            else {
                                Level2_Flies.itemDown++;
                            }
                        }
                        else{
                            if(Level2_Flies.itemIdle == 4){
                                Level2_Flies.itemIdle = 0;
                            }
                            else {
                                Level2_Flies.itemIdle++;
                            }
                        }
                    }
                    else if(!Map4.checkWin){
                        if(Level4_FinalBoss.item == 7){
                            Level4_FinalBoss.item = 0;
                        }
                        else{
                            Level4_FinalBoss.item++;
                        }
                    }
                }
                // di chuyển may bay khi start game
                if(!Map1.checkWin){
                    this.map1.startGame();
                }
                else if(Map1.switch_to_map2 && !Map2.checkStart){
                    this.map2.startGame();
                }
                else if(Map2.switch_to_map3 && !Map3.checkStart){
                    this.map3.startGame();
                }
                else if(Map3.switch_to_map4 && !Map4.checkStart){
                    this.map4.startGame();
                }

                // di chuyển ruồi khi start game
                if(Map1.checkStart && !Map1.checkWin){
                    this.map1.fly.moveFlies();
                }
                else if(Map2.checkStart && !Map2.checkWin){
                    this.map2.fly.moveFlies();
                }
                else if(Map3.checkStart && !Map3.checkWin){
                    this.map3.meteorite.moveMeteorite();
                }
                else if(Map4.checkStart && !Map4.checkWin){
                    this.map4.boss.moveFly();
                }

                // di chuyển máy bay
                if(Map1.checkStart){
                    this.start.aircraft.moveToThePlane();
                }

                // bắn đạn 
                if(Map1.checkStart){
                    this.start.aircraft.shootingAmmo();
                }
                
                // ruôì bắn đạn
                if(Level2_Flies.GO_DOWN >= 50){
                    this.map2.fly.shootingAmmoFlies();
                }
                else if(Level4_FinalBoss.DO_DOWN >= 30){
                    this.map4.boss.shootingAmmo();
                }

                // kiểm tra đạn bắn trúng ruồi
                if(!Map1.checkWin){
                    this.start.aircraft.ammo.bulletHitsFly1();
                }
                else if(!Map2.checkWin && Map2.checkStart){
                    this.start.aircraft.ammo.bulletHitsFly2();
                }
                else if(!Map3.checkWin && Map3.checkStart){
                    this.start.aircraft.ammo.bulletHitsMeteorite3();
                }
                else if(!Map4.checkWin && Map4.checkStart){
                    this.start.aircraft.ammo.bulletHitsFly4();
                }

                // kiểm tra máy bay bị chết
                if(!Map1.checkWin){
                    this.start.aircraft.checkDieAircraft1();
                }
                else if(!Map2.checkWin && Map2.checkStart){
                    this.start.aircraft.checkDieAircraft2();
                }
                else if(!Map3.checkWin && Map3.checkStart){
                    this.start.aircraft.checkDieAircraft3();
                }
                else if(!Map4.checkWin && Map4.checkStart){
                    this.start.aircraft.checkDieAircraft4();
                }
                
                // kiểm tra thắng màn chơi
                if(Map1.checkWin && !Map1.switch_to_map2){
                    this.map1.win();
                }
                else if(Map2.checkWin && !Map2.switch_to_map3){
                    this.map2.win();
                }
                else if(Map3.checkWin && !Map3.switch_to_map4){
                    this.map3.win();
                }
                else  if(Map4.checkWin && !Map4.switch_to_map5){
                    this.map4.win();
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
