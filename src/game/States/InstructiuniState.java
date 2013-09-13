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

public class InstructiuniState extends BasicGameState {

    private final byte  ID;
    private Button      back;
    private Image       img;
    private TextArea    buton[] = new TextArea[6];

    private final short start   = 40;
    private final short raport  = 35;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image( "res/meniu/instructiuniFundal.png" );
        back = new Button( 100, 500, "back.png" );

        buton[0] = new TextArea( gc, 200, start +raport *1, "Stanga :", Res.stanga );
        buton[1] = new TextArea( gc, 200, start +raport *2, "Dreapta :", Res.dreapta );
        buton[2] = new TextArea( gc, 200, start +raport *3, "Saritura :", Res.jump );
        buton[3] = new TextArea( gc, 200, start +raport *4, "Rostogolire :", Res.roll );
        buton[4] = new TextArea( gc, 200, start +raport *5, "Alunecare :", Res.slide );
        buton[5] = new TextArea( gc, 200, start +raport *6, "Pauza :", Res.pause );

        for (TextArea ton : buton )
            ton.getTxt().setBackgroundColor( Color.transparent );
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {

        buton[0].setVal( Res.stanga );
        buton[1].setVal( Res.dreapta );
        buton[2].setVal( Res.jump );
        buton[3].setVal( Res.roll );
        buton[4].setVal( Res.slide );
        buton[5].setVal( Res.pause );
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if ( back.clikOn( gc ) )
            sbg.enterState( Start.MENUSTATE );
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw();
        back.render( gc, sbg, g );
        for (TextArea ton : buton )
            ton.render( gc, g );
    }

    public InstructiuniState() {
        ID = Start.INSTRUCTIUNISTATE;
    }

    @Override
    public int getID() {
        return ID;
    }


}
