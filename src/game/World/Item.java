package game.World;

import game.Player.Physics;
import game.States.GameplayState;
import game.States.GameplayState.STATES;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public class Item extends Physics {

    protected Image img;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
        if ( ! ( this instanceof ItemTara ) ) {
            makeImage();
            poly = new Rectangle( x, y, img.getWidth(), img.getHeight() );
        }
    }

    protected void update(GameContainer gc, StateBasedGame sbg) {
        modY( 4 );

        if ( colid() ) {
            modY( -4 );
        }

        modX( -WorldMap.getMove() );
        if ( poly.getX() <=WorldMap.endGen ) {
            WorldMap.getItem().remove( this );
        }

        if ( poly.intersects( GameplayState.getPlayer().getPoly() ) ) {
            GameplayState.setToUpd( STATES.SCROL );
            makeSpecifScroll();
            WorldMap.getItem().remove( this );
        }
    }

    protected void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        img.draw( x, y );
    }

    protected void makeSpecifScroll() {
        GameplayState.getPlayer().modRezist( (short) 1400 );
        GameplayState.makeFactScroll();
        GameplayState.modIteme( 1 );
    }

    protected boolean colid() {

        for (int i = 0 ; i <WorldMap.getBlocks().size() ; i ++ ) {
            if ( GameplayState.getWorldMap().is_solid( i ) ) {
                if ( poly.intersects( GameplayState.getWorldMap().getBlock( i ) ) )
                    return true;
            }
            else if ( poly.intersects( GameplayState.getWorldMap().getBlock( i ) ) ) {
                while ( poly.intersects( GameplayState.getWorldMap().getBlock( i ) ) )
                    modX( 5 );
                modX( 200 );
                return false;
            }

        }
        return false;
    }

    protected void makeImage() {
        try {
            img = new Image( "res/item/item.png" );
        }
        catch (SlickException e) {
            System.out.println( "eroare in item fact la incarcarea imaginii" );
        }
    }
}
