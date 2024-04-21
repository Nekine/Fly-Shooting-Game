
package List_of_game_maps;

import List_of_fighter_aircrafts.CombatAircraft;
import List_of_sounds.*;
import java.awt.Graphics;


public class winGame {
    private sound_winGame soundWin;
    
    public winGame(){
        this.soundWin = new sound_winGame();
    }
    
    public void paint(Graphics g){
        g.drawImage(CombatAircraft.aircrafts[CombatAircraft.index][CombatAircraft.item], 430, 700-CombatAircraft.GO_UP , 120, 90, null);
        this.soundWin.playSound();
    }
    
    public void startGame(){
        CombatAircraft.GO_LEFT = 0;
        CombatAircraft.GO_RIGHT = 0;
        CombatAircraft.GO_DOWN = 0;
        
        if (Map3.switch_to_map4) {
            if (CombatAircraft.GO_UP < 240) {
                CombatAircraft.GO_UP += 5;
            }
        }
    }
}
