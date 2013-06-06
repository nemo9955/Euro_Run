package game.Imagini;

import game.World.WorldMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Background extends Imagine {

    public Background(int x, int y) {
        super(x, y, "res/landscape");
    }

    protected void makeImagine(String link) {
        try {
            img = new Image(String.format("%s/%d.png", link, 1 + zar.nextInt(2)));
            img.setAlpha(0.3f);
            WorldMap.modPozBG(img.getWidth());
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        modX(-WorldMap.getMove());

        if( x <= WorldMap.getEndgen() ) {
            WorldMap.getBlocks().remove(this);
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        img.draw(x, y);
    }

}
