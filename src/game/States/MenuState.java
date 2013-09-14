package game.States;

import game.Start;
import game.Extra.Button;

import org.newdawn.slick.Animation;
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
    private Button     instr;
    private Image      img;

    private Animation  anim;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start = new Button( 100, 100, "start.png" );
        options = new Button( 100, 200, "options.png" );
        instr = new Button( 100, 300, "instructiuni.png" );
        img = new Image( "res/meniu/Meniu.png" );

        Image logo[] = new Image[42];

        for (int i = 0 ; i <42 ; i ++ )
            logo[i] = new Image( String.format( "res/logo/Logo0%s", ( i <=9 ? "0" +i : i ) ) );

        anim = new Animation( logo, 100, true );
        anim.setPingPong( true );
        anim.setDuration( 0, 2000 );
        anim.setDuration( 41, 2000 );
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        anim.start();
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        anim.stop();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        anim.update( delta );

        if ( start.clikOn( gc ) )
            sbg.enterState( Start.GAMEPLAYSTATE );
        if ( options.clikOn( gc ) )
            sbg.enterState( Start.OPTIONSTATE );
        if ( instr.clikOn( gc ) )
            sbg.enterState( Start.INSTRUCTIUNISTATE );
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground( Color.black );
        img.draw();

        anim.draw( 630, 10, 150, 150 );

        start.render( gc, sbg, g );
        options.render( gc, sbg, g );
        instr.render( gc, sbg, g );

    }

    public MenuState() {
        ID = Start.MENUSTATE;
    }

    @Override
    public int getID() {
        return ID;
    }

}
