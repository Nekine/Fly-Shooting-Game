
package galacticskywars;

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
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // chuyển item ảnh
            if(StartScreen.item == 4){
                StartScreen.item = 0;
            }
            else{
                StartScreen.item++;
            }
            
            // di chuyển may bay
            if(GameScreen.checkPlay){
                if(Map1.up < 70){
                    Map1.up += 5;
                }
            }
        }
    }
    
    
}
