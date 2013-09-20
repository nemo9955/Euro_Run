package game.States;

import game.Start;
import game.Extra.Button;
import game.Player.DummyPlayer;
import game.Player.Player;
import game.World.Block;
import game.World.BlockMers;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MenuState extends BasicGameState {

    private final byte  ID;
    private Button      start;
    private Button      options;
    private Button      instr;
    private Image       img;
    private Animation   anim;
    private Rectangle   zonAnim;

    private Player      man;
    public static Block mers;

    public static Music imn;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start = new Button( 280, 120, "start.png" );
        options = new Button( 610, 330, "options.png" );
        instr = new Button( 50, 330, "instructiuni.png" );
        img = new Image( "res/meniu/Meniu.png" );

        Image logo[] = new Image[42];

        imn = new Music( "res/sunet/imn.wav" );

        for (int i = 0 ; i <42 ; i ++ )
            logo[i] = new Image( String.format( "res/logo/Logo0%s", ( i <=9 ? "0" +i : i ) ) );

        anim = new Animation( logo, 100, true );
        anim.setPingPong( true );
        anim.setDuration( 0, 2000 );
        anim.setDuration( 41, 2000 );
        zonAnim = new Rectangle( 644, 8, 150, 150 );

        man = new DummyPlayer( 400, 400 );
        mers = new BlockMers( -10, 550 );

        imn.loop();
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        anim.start();
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        anim.stop();
        man.reset( 400, 400 );
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        anim.update( delta );
        man.update( gc, sbg, delta );

        if ( start.clikOn( gc ) ||start.getZon().intersects( man.getPoly() ) )
            sbg.enterState( Start.GAMEPLAYSTATE );
        if ( options.clikOn( gc ) ||options.getZon().intersects( man.getPoly() ) )
            sbg.enterState( Start.OPTIONSTATE );
        if ( instr.clikOn( gc ) ||instr.getZon().intersects( man.getPoly() ) )
            sbg.enterState( Start.INSTRUCTIUNISTATE );
        if ( man.getPoly().intersects( zonAnim ) )
            sbg.enterState( Start.CREDITSTATE );

        // start.setLocation( 280, 120 );
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground( Color.black );
        img.draw();

        anim.draw( 644, 8, 150, 150 );

        start.render( gc, sbg, g );
        options.render( gc, sbg, g );
        instr.render( gc, sbg, g );

        man.render( gc, sbg, g );
    }

    public MenuState() {
        ID = Start.MENUSTATE;
    }

    @Override
    public int getID() {
        return ID;
    }

}
