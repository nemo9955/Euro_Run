package game.World;

import game.GamePlay.GameplayState;
import game.Player.Physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Item extends Physics {

    private Image img;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
        makeImage();
        poly=new Rectangle (x,y,img.getWidth(),img.getHeight());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        modX(-WorldMap.getMove());

        if( poly.getX() <= WorldMap.getEndgen() ) {
            WorldMap.getItem().remove(this);
        }

        if( poly.intersects(GameplayState.getPlayer().getPoly()) ) {
 //           System.out.println("colid");
            GameplayState.setTaken(true);
            GameplayState.genScroll();
            WorldMap.getItem().remove(this);
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        img.draw(x, y);
    }

    private void makeImage() {
        try {
            img = new Image("res/item/item.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void modX(float x) {
        this.x += x;
        poly.setX(this.x);
    }
}
