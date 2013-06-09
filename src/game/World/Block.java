package game.World;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Block {

    protected float x, y;
    protected boolean solid;
    protected Rectangle zon;
    
    protected Color color ;
    protected Random zar = new Random() ;
    
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        Solid();
        Zon();
        color=new Color(zar.nextInt(225),zar.nextInt(225),zar.nextInt(225));
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        
        modX(-WorldMap.getMove());

        if( zon.getX() <= WorldMap.getEndgen() ) {
            WorldMap.getBlocks().remove(this);
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        g.setColor(color);
        g.setLineWidth(3);
        g.fill(zon);

    }

    protected void Zon() {
        zon = null;
    }

    protected void Solid() {
        solid = false;
    }

    public void modX(float x) {
        this.x += x;
        zon.setX(this.x);
    }

    public void modY(float y) {
        this.y += y;
        zon.setY(this.y);
    }

    //                getters

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isSolid() {
        return solid;
    }

    public Rectangle getZon() {
        return zon;
    }

    public void setX(float x) {
        this.x = x;
        zon.setX(this.x);
    }

    public void setY(float y) {
        this.y = y;
        zon.setY(this.y);
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public void setZon(Rectangle zon) {
        this.zon = zon;
    }

}
