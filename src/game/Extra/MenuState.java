package game.Extra;

import game.Start;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MenuState extends BasicGameState {

    private final byte ID;
    private Button     start;
    private Button     options;
    private Image img ;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start = new Button(100, 100, "start.png");
        options = new Button(100, 200, "options.png");
        img = new Image ("res/meniu/Meniu.png");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        
        if (start.clikOn(gc))
            sbg.enterState(Start.GAMEPLAYSTATE);
        if (options.clikOn(gc))
            sbg.enterState(Start.OPTIONSTATE);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        img.draw();
        start.render(gc, sbg, g);
        options.render(gc, sbg, g);
        
    }

    public MenuState() {
        ID = Start.MENUSTATE;
    }

    @Override
    public int getID() {
        return ID;
    }

}
