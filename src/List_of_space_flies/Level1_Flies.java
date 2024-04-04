package List_of_space_flies;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Level1_Flies {
    private Image flies[][];
    
    public static int item;
    
    public Level1_Flies(){
        this.flies = new Image[3][2];
        
        Level1_Flies.item = 0;
        
        for(int i=0; i<3; i++){
            for(int j=0; j<2; j++){
                try{
                    this.flies[i][j] = ImageIO.read(new File("S675/aircraft/bosungmay" + (i+1) +"_hold_" + j + ".png"));
                }catch(Exception e){}
            }
        }
    }
    
    public void paint(Graphics g){
        for(int i=0; i<9; i++){
            if(i < 3){
                g.drawImage(this.flies[i][Level1_Flies.item], 50+i*100, 30, 80, 60, null);
                g.drawImage(this.flies[2-i][Level1_Flies.item], 50+i*100, 120, 80, 60, null);
            }
            else if(i < 6){
                g.drawImage(this.flies[i%3][Level1_Flies.item], 50+i*100, 30, 80, 60, null);
                g.drawImage(this.flies[2-(i%3)][Level1_Flies.item], 50+i*100, 120, 80, 60, null);
            }
            else{
                g.drawImage(this.flies[i%6][Level1_Flies.item], 50+i*100, 30, 80, 60, null);
                g.drawImage(this.flies[2-(i%6)][Level1_Flies.item], 50+i*100, 120, 80, 60, null);
            }
        }
    }
}
