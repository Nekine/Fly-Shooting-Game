package List_of_space_flies;

import List_of_game_maps.Map3;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;


public class Level3_MeteoriteChunks {
    private Image meteorite[];
    
    public static int GO_DOWN[];
    public static int checkDie[];
    public static int size[];

    public Level3_MeteoriteChunks() {
        this.meteorite = new Image[30];
        Level3_MeteoriteChunks.size = new int[30];
        Level3_MeteoriteChunks.GO_DOWN = new int[5];
        Level3_MeteoriteChunks.checkDie = new int[30];
        
        for(int i=0; i<5; i++){
            Level3_MeteoriteChunks.GO_DOWN[i] = -100 - (200*i);
        }

        Random random = new Random();
        for(int i=0; i<30; i++){
            int randomNumber = random.nextInt(2) + 2;
            Level3_MeteoriteChunks.size[i] = randomNumber;
            
            Level3_MeteoriteChunks.checkDie[i] = 0;
            
            try{
                this.meteorite[i] = ImageIO.read(new File("S675/hazzard2.png"));
            }catch(Exception e){}
        }
    }
    
    public void paint(Graphics g){
        for(int i=0; i<5; i++){
            for(int j=0; j<6; j++){
                if(Level3_MeteoriteChunks.checkDie[j+i*6] < 4){
                    g.drawImage(this.meteorite[j], 60+j*150, Level3_MeteoriteChunks.GO_DOWN[i], 50*Level3_MeteoriteChunks.size[j+i*6], 50*Level3_MeteoriteChunks.size[j+i*6], null);
                }
            }
        }
    }
    
    private void randomSizeMeteorite(int index){
        Random random = new Random();
        for(int i=0; i<6; i++){
            int randomNumber = random.nextInt(2) + 2;
            Level3_MeteoriteChunks.size[i+index] = randomNumber;
            
            Level3_MeteoriteChunks.checkDie[i+index] = 0;
        }
    }
    
    public void moveMeteorite(){
        for(int i=0; i<5; i++){
            if(Level3_MeteoriteChunks.GO_DOWN[i] > 800){
                Level3_MeteoriteChunks.GO_DOWN[i] = -200;
                this.randomSizeMeteorite(i*6);
                Map3.count++;
            }
            else{
                Level3_MeteoriteChunks.GO_DOWN[i]++;
            }
        }   
    }
}
