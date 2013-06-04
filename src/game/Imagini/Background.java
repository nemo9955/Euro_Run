package game.Imagini;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background extends Imagine {

    private Random zar = new Random();

    public Background(int x, int y) {
        super(x, y , "res/landscape");
    }

    protected void makeImagine(String link) {
        try {
 //           System.out.println( zar.nextInt(2));
            img = new Image(String.format("%s/%d.png", link, 1));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
