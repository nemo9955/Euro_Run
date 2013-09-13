package game.Imagini;

import java.util.Random;

import game.World.WorldMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class Imagine {

    protected Image         img;
    protected int           x, y;
    protected Color         color;
    protected static Random zar = new Random();

    public Imagine(int x, int y, String link) {
        super();
        makeImagine( link );
        this.x = x;
        this.y = y;
        color = new Color( zar.nextInt( 225 ), zar.nextInt( 225 ), zar.nextInt( 225 ) );
    }

    protected void makeImagine(String link) {
        img = null;
    }

    public void update(GameContainer gc, StateBasedGame sbg) {
        modX( -WorldMap.getMove() );
        if ( x <=WorldMap.endGen ) {
            WorldMap.getImagini().remove( this );
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        g.setColor( color );
        g.setLineWidth( 5 );
        img.draw( x, y );
    }

    protected void modX(int x) {
        this.x += x;
    }

    protected void modY(int y) {
        this.y += y;
    }

    public void renderAfter(GameContainer gc, StateBasedGame sbg, Graphics g) {

    }
}
