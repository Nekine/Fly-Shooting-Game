package galacticskywars;

import List_of_fighter_aircrafts.CombatAircraft;
import List_of_game_maps.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;


public class FrameScreen extends JFrame implements MouseListener{
    GameScreen game;
    
    public FrameScreen(){
        this.game = new GameScreen();
        add(this.game);
        addMouseListener(this);
        this.addKeyListener(new handler());
        
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
        
        if(x>=360 && x<=400 && y>=510 && y<=580 && !GameScreen.checkPlay){
            this.game.start.playAudio_ClickSelectAircraft();
            if(CombatAircraft.index == 0){
                CombatAircraft.index = 4;
            }
            else{
                CombatAircraft.index--;
            }
        }
        else if(x>=590 && x<=630 && y>=510 && y<=580 && !GameScreen.checkPlay){
            this.game.start.playAudio_ClickSelectAircraft();
            if(CombatAircraft.index == 4){
                CombatAircraft.index = 0;
            }
            else{
                CombatAircraft.index++;
            }
        }
        else if(x>=440 && x<=550 && y>=630 && y<=680 && !GameScreen.checkPlay){
            this.game.start.playAudio_ClickPlayButton();
            GameScreen.checkPlay = true;
        }
        else if(x>=260 && x<=330 && y>=710 && y<=740 && CombatAircraft.checkDie >= 3){
            this.game.start.playAudio_ClickPlayButton();
            CombatAircraft.checkDie = 0;
            CombatAircraft.itemDie = 0;
            Map1.checkWin = false;
            Map1.checkStart = false;
            Map1.switch_to_map2 = false;
            Map2.checkWin = false;
            Map2.checkStart = false;
            Map2.switch_to_map3 = false;
            Map3.checkWin = false;
            Map3.checkStart = false;
            Map3.switch_to_map4 = false;
            Map4.checkWin = false;
            Map4.checkStart = false;
            Map4.switch_to_map5 = false;
        }
        else if(x>=650 && x<=710 && y>=710 && y<=740 && CombatAircraft.checkDie >= 3){
            this.game.start.playAudio_ClickPlayButton();
            GameScreen.checkPlay = false;
            CombatAircraft.checkDie = 0;
            CombatAircraft.itemDie = 0;
            Map1.checkWin = false;
            Map1.checkStart = false;
            Map1.switch_to_map2 = false;
            Map2.checkWin = false;
            Map2.checkStart = false;
            Map2.switch_to_map3 = false;
            Map3.checkWin = false;
            Map3.checkStart = false;
            Map3.switch_to_map4 = false;
            Map4.checkWin = false;
            Map4.checkStart = false;
            Map4.switch_to_map5 = false;
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
    
    
    private class handler implements KeyListener /*interface lắng nghe sự kiện bấm phim*/{

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP){
                CombatAircraft.checkMove = 3;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                CombatAircraft.checkMove = 4;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                CombatAircraft.checkMove = 1;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                CombatAircraft.checkMove = 2;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            CombatAircraft.checkMove = 0;
        }
        
    }
}