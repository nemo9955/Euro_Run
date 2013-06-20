package game.Extra;

import game.Start;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MenuState extends BasicGameState {

    private final byte ID;
    private Button     start;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start = new Button(100, 100, "start.png");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (start.clikOn(gc))
            sbg.enterState(Start.GAMEPLAYSTATE);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        start.render(gc, sbg, g);
    }

    public MenuState() {
        ID = Start.MENUSTATE;
    }

    @Override
    public int getID() {
        return ID;
    }

}
