
package List_of_game_maps;

import List_of_fighter_aircraft.*;
import galacticskywars.StartScreen;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Map1 {
    private Image background;
    private CombatAircraft aircraft;
    
    public static int left, right, up, down;

    public Map1() {
        this.aircraft = new CombatAircraft();
        Map1.left = Map1.right = Map1.up = Map1.down = 0;
    }
    
    public void paint(Graphics g){
        this.paintBG(g);
        this.aircraft.paint(g, StartScreen.index, StartScreen.item);
    }
    
    public void paintBG(Graphics g){
        try{
            this.background = ImageIO.read(new File("S675/World3_Space.png"));
            g.drawImage(this.background, 0, 0, 1000, 800, null);
        }catch(Exception e){}   
    }
}
