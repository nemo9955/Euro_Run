package game.States;

import game.Start;
import game.Extra.Button;
import game.Extra.Res;
import game.Extra.TextArea;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class OptionState extends BasicGameState {

    private final byte  ID;
    private Button      back;
    private Image       img;

    private final short start   = 30;
    private final short raport  = 30;

    private TextArea    buton[] = new TextArea[6];

    /*
     * stanga 0
     * dreapta 1
     * jump 2
     * roll 3
     * slide 4
     * pause 5
     */

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        back = new Button( 100, 500, "back.png" );
        buton[0] = new TextArea( gc, 200, start +raport *1, "Stanga :", Res.stanga );
        buton[1] = new TextArea( gc, 200, start +raport *2, "Dreapta :", Res.dreapta );
        buton[2] = new TextArea( gc, 200, start +raport *3, "Saritura :", Res.jump );
        buton[3] = new TextArea( gc, 200, start +raport *4, "Rostogolire :", Res.roll );
        buton[4] = new TextArea( gc, 200, start +raport *5, "Alunecare :", Res.slide );
        buton[5] = new TextArea( gc, 200, start +raport *6, "Pauza :", Res.pause );
        img = new Image( "res/meniu/Optiuni.png" );
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if ( back.clikOn( gc ) )
            sbg.enterState( Start.MENUSTATE );
    }


    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Res.stanga = buton[0].getVal();
        Res.dreapta = buton[1].getVal();
        Res.jump = buton[2].getVal();
        Res.roll = buton[3].getVal();
        Res.slide = buton[4].getVal();
        Res.pause = buton[5].getVal();
    }


    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw();
        g.setBackground( Color.black );
        back.render( gc, sbg, g );
        for (TextArea ton : buton )
            ton.render( gc, g );
    }

    public void keyPressed(int key, char c) {

        for (TextArea ton : buton )
            if ( ton.hasFocus() ) {
                ton.setVal( key );
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
