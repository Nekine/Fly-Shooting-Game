
package galacticskywars;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;


public class FrameScreen extends JFrame implements MouseListener{
    GameScreen game;
    
    public FrameScreen(){
        this.game = new GameScreen();
        add(this.game);
        addMouseListener(this);
        
        this.setSize(1000, 800);
        this.setTitle("Galactic Sky Wars");
        this.setLocationRelativeTo(null); // hiển thị GUI giữa màn hình
        this.setLayout(null);
        this.setVisible(true); // muốn hiển thị GUI lên
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ấn vào button X sẽ tắt GUI
    }

    public static void main(String[] args) {
        FrameScreen frame = new FrameScreen();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        if(x>=360 && x<=400 && y>=510 && y<=580){
            this.game.start.playAudio_ClickSelectAircraft();
            if(StartScreen.index == 0){
                StartScreen.index = 4;
            }
            else{
                StartScreen.index--;
            }
        }
        else if(x>=590 && x<=630 && y>=510 && y<=580){
            this.game.start.playAudio_ClickSelectAircraft();
            if(StartScreen.index == 4){
                StartScreen.index = 0;
            }
            else{
                StartScreen.index++;
            }
        }
        else if(x>=410 && x<=570 && y>=660 && y<=700){
            this.game.start.playAudio_ClickPlayButton();
            GameScreen.checkPlay = true;
        }
        
        this.game.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
