package game.Extra;

import game.Start;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class OptionState extends BasicGameState {

    private final byte ID;
    private Button     back;

    private TextArea   jump;
    private TextArea   roll;
    private TextArea   slide;
    private TextArea   pause;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        back = new Button(100, 500, "back.png");
        jump = new TextArea(gc, 200, 100, "Saritura :", Res.jump);
        roll = new TextArea(gc, 200, 150, "Roll :", Res.roll);
        slide = new TextArea(gc, 200, 200, "Slide :", Res.slide);
        pause = new TextArea(gc, 200, 250, "Puse :", Res.pause);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (back.clikOn(gc))
            sbg.enterState(Start.MENUSTATE);
        jump.update(gc);
        roll.update(gc);
        slide.update(gc);
        pause.update(gc);
    }


    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Res.jump = jump.getVal();
        Res.roll = roll.getVal();
        Res.slide = slide.getVal();
        Res.pause = pause.getVal();
    }


    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        back.render(gc, sbg, g);
        jump.render(gc, g);
        roll.render(gc, g);
        slide.render(gc, g);
        pause.render(gc, g);
    }

    public OptionState() {
        ID = Start.OPTIONSTATE;
    }

    @Override
    public int getID() {
        return ID;
    }

}
