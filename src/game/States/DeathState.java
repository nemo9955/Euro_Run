package game.States;

import game.Start;
import game.Extra.Button;
import game.World.WorldMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class DeathState extends BasicGameState {

    private final byte ID;
    private Button     mMeniu;
    private Image      img;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mMeniu = new Button( 100, 500, "mainMenu.png" );
        img = new Image( "res/meniu/Death.png" );
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if ( mMeniu.clikOn( gc ) )
            sbg.enterState( Start.MENUSTATE );

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw();
        g.setColor( Color.cyan );
        mMeniu.render( gc, sbg, g );
        g.drawString( String.format( "Distanta parcursa: %d metri", GameplayState.distanta ), 50, 225 );
        g.drawString( String.format( "Tari vizitate: %d", WorldMap.harta.getTariVizitate() ), 50, 85 );
        g.drawString( String.format( "Culturi invatate: %d", GameplayState.tariCunosti ), 50, 110 );
        g.drawString( String.format( "Cunostinte dobandite: %d", GameplayState.iteme ), 50, 135 );
        g.drawString( String.format( "Scorul total: %d", (int) ( ( GameplayState.iteme *783 ) + ( GameplayState.distanta *0.9f ) + ( WorldMap.harta.getTariVizitate() *94 ) + ( GameplayState.tariCunosti *489 ) ) ), 50, 200 );
    }

    public DeathState() {
        ID = Start.DEATHSTATE;
    }

    @Override
    public int getID() {
        return ID;
    }

}
