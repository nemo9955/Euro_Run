package game.States;

import game.Start;
import game.Extra.Button;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class CreditState extends BasicGameState {

    private final byte ID;
    private Button     back;


    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        back = new Button( 100, 500, "back.png" );
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground( Color.white );
        g.setColor( Color.black );
        g.drawString( "Acest joc a fost realizat pentru a promova campania Euroscola\n" +
        		"din cadrul Colegiului National Ecaterina Teodoroiu .\n\n" +
        		"Programarea realizata de Mogoi Adrian .\n" +
        		"Grafica jocului facuta de Voica Lucian , Mihaela Popa si Mogoi Adrian.\n" +
        		"Informatiile necesare procurate de Cocheci Cosmin si Ionescu Diana .\n" +
        		"Asistenta oferita de Caramete Elvis si Draica Cristian .\n" +
        		"Multumiri membrilor echipei pentru ajutor si suport .\n" +
        		"" +
        		"" +
        		"", 100, 100 );
        back.render( gc, sbg, g );
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta   ) throws SlickException {
        if ( back.clikOn( gc ) )
            sbg.enterState( Start.MENUSTATE );
    }

    @Override
    public int getID() {
        return ID;
    }

    public CreditState() {
        ID = Start.CREDITSTATE;
    }


}
