package game.Imagini;

import game.World.WorldMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class ZonaMers extends Imagine {

    private static final byte imgNo = 2;

    public ZonaMers(int x, int y) {
        super(x, y, "res/landscape");
    }

    protected void makeImagine(String link) {
        
        try {
            img = new Image(String.format("%s/mers_%d.png", link, 1 + zar.nextInt(imgNo)));
            WorldMap.modPozSol((short) img.getWidth());
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        img.draw(x, y);
    }
}
