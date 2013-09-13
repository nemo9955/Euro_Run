package game.World;


import game.States.GameplayState;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;


public class Block {

    protected float   x, y;
    protected boolean solid;
    protected Shape   zon;
    protected Color   color;
    protected Random  zar = new Random();

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        Solid();
        Zon();
        color = new Color( zar.nextInt( 225 ), zar.nextInt( 225 ), zar.nextInt( 225 ) );
    }

    public Block(Shape zon) {
        this.zon = zon;
        x = zon.getX();
        y = zon.getY();
        Solid();
        color = new Color( zar.nextInt( 225 ), zar.nextInt( 225 ), zar.nextInt( 225 ) );
    }

    public void update(GameContainer gc, StateBasedGame sbg) {
        modX( -WorldMap.getMove() );
        if ( zon.getX() <=WorldMap.endGen ) {
            WorldMap.getBlocks().remove( this );
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        g.setColor( color );
        g.setLineWidth( 3 );
        g.fill( zon );
    }

    protected boolean colid() {
        for (int i = 0 ; i <WorldMap.getBlocks().size() ; i ++ ) {
            if ( this.getZon() !=GameplayState.getWorldMap().getBlock( i ) )
                if ( zon.intersects( GameplayState.getWorldMap().getBlock( i ) ) ) {
                    return true;
                }
        }
        return false;
    }

    // getters & setters
    protected void Zon() {
        zon = null;
    }

    protected void Solid() {
        solid = true;
    }

    public void modX(float x) {
        this.x += x;
        zon.setX( this.x );
    }

    public void modY(float y) {
        this.y += y;
        zon.setY( this.y );
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isSolid() {
        return solid;
    }

    public Shape getZon() {
        return zon;
    }

    public void setX(float x) {
        this.x = x;
        zon.setX( this.x );
    }

    public void setY(float y) {
        this.y = y;
        zon.setY( this.y );
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public void setZon(Rectangle zon) {
        this.zon = zon;
    }
}
