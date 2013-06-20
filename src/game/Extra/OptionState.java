package game.Extra;

import game.Start;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class OptionState extends BasicGameState {

    private final byte ID ;
    private Button back ;
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        back = new Button(100 , 500 ,"back.png");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (back.clikOn(gc))
            sbg.enterState(Start.MENUSTATE);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        back.render(gc, sbg, g);
    }

    public OptionState(){
        ID = Start.OPTIONSTATE;
    }
    
    @Override
    public int getID() {
        return ID;
    }

}
