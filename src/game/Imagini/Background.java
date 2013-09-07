package game.Imagini;

import game.World.WorldMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Background extends Imagine {

    private static final byte bgNo = 3;

    public Background(int x, int y) {
        super(x, y, "res/landscape");
        modY(-img.getHeight());
    }

    protected void makeImagine(String link) {
        try {
            img = new Image(String.format("%s/%d_land.png", link, 1 + zar.nextInt(bgNo)));
            img.setAlpha(0.3f);
            WorldMap.modPozBG(img.getWidth());
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        img.draw(x, y);
    }
}
