package game.Extra;

import game.Start;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class OptionState extends BasicGameState {

    private final byte ID;
    private Button     back;
    private Image      img;

    private TextArea   buton[] = new TextArea[4];

    /*
     * jump 0
     * roll 1
     * slide 2
     * pause 3
     */

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        back = new Button(100, 500, "back.png");
        buton[0] = new TextArea(gc, 200, 100, "Saritura :", Res.jump);
        buton[1] = new TextArea(gc, 200, 150, "Roll :", Res.roll);
        buton[2] = new TextArea(gc, 200, 200, "Slide :", Res.slide);
        buton[3] = new TextArea(gc, 200, 250, "Puse :", Res.pause);
        img = new Image("res/meniu/Optiuni.png");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (back.clikOn(gc))
            sbg.enterState(Start.MENUSTATE);
    }


    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Res.jump = buton[0].getVal();
        Res.roll = buton[1].getVal();
        Res.slide = buton[2].getVal();
        Res.pause = buton[3].getVal();
    }


    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw();
        g.setBackground(Color.black);
        back.render(gc, sbg, g);
        for (TextArea ton : buton)
            ton.render(gc, g);
    }

    public void keyPressed(int key, char c) {

        for (TextArea ton : buton)
            if (ton.hasFocus()) {
                ton.setVal(key);
            }

    }

    public OptionState() {
        ID = Start.OPTIONSTATE;
    }

    @Override
    public int getID() {
        return ID;
    }
}
