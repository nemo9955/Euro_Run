package game.Imagini;

import java.util.Random;

import game.World.WorldMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Imagine {

    protected Image img;
    protected int x, y;

    protected Rectangle poly;
    protected Color color;
    protected Random zar = new Random();

    public Imagine(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        makePoly(x, y, 100, 100);
        color = new Color(zar.nextInt(225), zar.nextInt(225), zar.nextInt(225));
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        
        modX(-WorldMap.getMove());

        if( poly.getX() <= WorldMap.getEndgen() ) {
            WorldMap.getBlocks().remove(this);
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        g.setColor(color);
        g.setLineWidth(5);
        g.draw(poly);
    }

    protected void makePoly(int x, int y, int w, int h) {
        poly = new Rectangle(x, y, w, h);
    }

    protected void modX(int x) {
        this.x += x;
        poly.setX(this.x);
    }

    protected void modY(int y) {
        this.y += y;
        poly.setY(this.y);
    }

}
